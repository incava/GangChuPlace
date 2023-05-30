package com.incava.gangchuplace.viewmodel.repository

import com.incava.gangchuplace.model.GangChuPreview


/**
 * 강추 메뉴를 정렬 할 메서드를 모아 놓은 Repository
 */
class GangChuSortRepo {

    //평점 순으로 정렬 하는 메서드
    fun gangChuSort(gangChuList: MutableList<GangChuPreview>, query: String): MutableList<GangChuPreview> {
        return when (query) {
            "평점순" -> gangChuList.sortedByDescending { it.rank }.toMutableList()
            "이름순" -> gangChuList.sortedBy { it.storePlace.title }.toMutableList()
            "거리순" -> gangChuList.sortedBy { it.distance }.toMutableList()
            "친구 평점순" -> gangChuList.sortedByDescending { it.friendRank }.toMutableList()
            else -> throw Exception()
        }

    }

}