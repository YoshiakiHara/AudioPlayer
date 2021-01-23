package com.hara.audioplayer

import com.hara.audioplayer.MusicDataHolder.musicItems

object AlbumNumberHolder {
    var albumNumberList: MutableList<String> = mutableListOf()
    var albumNumberIdList: MutableList<Int> = mutableListOf()
    private const val TAG = "MusicDataHolder"
    fun setArtistMusicList(album: String){
        if(musicItems.isNotEmpty()) {
            musicItems.forEachIndexed { index, i ->
                if(i.album.equals(album)){
                    albumNumberList.add(i.title)
                    albumNumberIdList.add(index)
                }
            }
        }
    }
}