package com.incava.gangchuplace.util

enum class FriendCode(val comment : String) {
    NotExistFail("등록된 닉네임이 없습니다.\n" + "다시 한번 확인해 주세요."),
    AlreadyRequestFail("이미 친구 요청한 상태입니다.\n" + "다시 한번 확인해 주세요."),
    NetworkFail("네트워크로 인해 실패 하였습니다."),
    RequestSuccess("친구 추가 요청 완료!"),
}