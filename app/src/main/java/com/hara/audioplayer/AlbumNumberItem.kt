package com.hara.audioplayer

data class AlbumNumberItem (
    val id: Long,
    val title: String,
    val truck: Int

    ):Comparable<Any?>{
    override fun compareTo(another: Any?): Int {
        if (another == null) {
            return 1
        }
        val item = another as AlbumNumberItem
        return truck.compareTo(item.truck)
    }
}