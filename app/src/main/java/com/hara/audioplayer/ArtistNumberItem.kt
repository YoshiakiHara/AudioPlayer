package com.hara.audioplayer

data class ArtistNumberItem (
    val id: Long,
    val title: String,
    val album: String

    ):Comparable<Any?>{
    override fun compareTo(another: Any?): Int {
        if (another == null) {
            return 1
        }
        val item = another as ArtistNumberItem
        return title.compareTo(item.title)
    }
}