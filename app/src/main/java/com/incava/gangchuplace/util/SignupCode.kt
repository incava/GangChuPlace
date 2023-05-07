package com.incava.gangchuplace.util

enum class SignupCode(val comment : String){
    EmailRegexFail("이메일 형식이 맞지 않습니다."),
    NicknameRegexFail("닉네임 형식이 맞지 않습니다."),
    PasswordRegexFail("비밀번호 형식이 맞지 않습니다,"),
    PasswordConfirmFail("비밀번호가 서로 다릅니다. 다시 확인해 주세요"),
    ExistEmailFail("이미 가입한 이메일입니다."),
    ExistNicknameFail("중복 된 닉네임이 있습니다."),
    NetworkFail("네트워크 실패"),
    SignupSuccess("회원가입 성공")
}