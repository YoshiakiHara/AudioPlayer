package com.hara.audioplayer

data class MusicItem (
    val id: Long,
    val artist: String,
    val title: String,
    val album: String,
    val truck: Int,
    val duration: Long,
    val path: String
    ):Comparable<Any?>{
    override fun compareTo(another: Any?): Int {
        if (another == null) {
            return 1
        }
        val item = another as MusicItem
        val result = artist.compareTo(item.artist)
        return if (result != 0) {
            result
        } else truck - item.truck
    }
}