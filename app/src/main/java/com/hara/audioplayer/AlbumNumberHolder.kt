package com.hara.audioplayer

import com.hara.audioplayer.MusicDataHolder.musicItems
import java.util.*

object AlbumNumberHolder {
    var albumNumberList: MutableList<AlbumNumberItem> = mutableListOf()
    private const val TAG = "AlbumNumberHolder"
    fun setArtistMusicList(album: String){
        if(musicItems.isNotEmpty()) {
            musicItems.forEachIndexed { index, i ->
                if(i.album.equals(album)){
                    albumNumberList.add(AlbumNumberItem(i.id,i.title,i.truck))
                }
            }
            Collections.sort(albumNumberList)
        }
    }
}