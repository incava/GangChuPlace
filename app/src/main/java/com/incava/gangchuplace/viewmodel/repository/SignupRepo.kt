package com.incava.gangchuplace.viewmodel.repository

import android.util.Log
import com.incava.gangchuplace.model.UserDTO
import com.incava.gangchuplace.util.Common.fireStore
import com.incava.gangchuplace.util.Regex.regexCheck
import com.incava.gangchuplace.util.SignupCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await

class SignupRepo {

    private val loginRoute = "home"

    suspend fun requestSignup(
        email: String,
        nickname: String,
        password: String,
        passwordConfirm: String
    ): SignupCode {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                // 정규식 체크 후 틀리면 false
                if (password != passwordConfirm) return@async SignupCode.PasswordConfirmFail
                else if (!regexCheck(email, "email")) return@async SignupCode.EmailRegexFail
                else if (!regexCheck(
                        nickname,
                        "nickname"
                    )
                ) return@async SignupCode.NicknameRegexFail
                else if (!regexCheck(
                        password,
                        "password"
                    )
                ) return@async SignupCode.PasswordRegexFail

                // 로그인경로가 home인 email이 이미 회원이 있는지 확인
                val homeId = "${loginRoute}+${email}"

                //이미 있는 email 확인 Document
                val emailDocument = fireStore.collection("User")
                    .document(homeId)
                    .get()
                    .await()

                //이미 있는 회원 이라면
                if (emailDocument.exists()) return@async SignupCode.ExistEmailFail
                // 닉네임 찾는 Query
                val nicknameQuery = fireStore.collection("User")
                    .whereEqualTo("nickname", nickname)
                    .get()
                    .await()

                // 이미 있는 닉네임 일 때
                if (!nicknameQuery.isEmpty) {
                    Log.i("nickname", nicknameQuery.documents[0].id)
                    return@async SignupCode.ExistNicknameFail
                }
                //회원 가입 절차
                val user = UserDTO(nickname, "", "home", password)
                fireStore.collection("User")
                    .document(homeId)
                    .set(user)
                    .await()
                //회원가입 완료 후 리턴
                SignupCode.SignupSuccess
            } catch (e: Exception) {
                SignupCode.NetworkFail
            }
        }.await()
    }



}