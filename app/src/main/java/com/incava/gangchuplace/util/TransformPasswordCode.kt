package com.incava.gangchuplace.util

enum class TransformPasswordCode(val comment:String) {
    EqualCurrentAndNewPass("현재 비밀번호와 같습니다."),
    NotRegexNewPass("변경할 비밀번호의 형식이 잘못되었습니다."),
    NotMatchPassConfirm("변경할 비밀번호가 서로 다릅니다"),
    NotMatchCurrentPass("현재 비밀번호가 다릅니다."),
    TransformPassSuccess("변경에 성공하였습니다!"),
    TransformPassFail("변경에 실패하였습니다. 네트워크를 확인해 주세요."),
}