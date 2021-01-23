package com.hara.audioplayer

import android.util.Log
import com.hara.audioplayer.MusicDataHolder.musicItems
import java.util.*

object ArtistNumberHolder {
    var artistNumberList: MutableList<ArtistNumberItem> = mutableListOf()
    private const val TAG = "MusicDataHolder"
    fun setArtistMusicList(artist: String){
        if(musicItems.isNotEmpty()) {
            musicItems.forEachIndexed { index,i ->
                if(i.artist.equals(artist)){
                    artistNumberList.add(ArtistNumberItem(i.id,i.title,i.album))
                }
                Log.i(TAG, "artist:" + i.artist + " album:" + i.album)
            }
            Collections.sort(artistNumberList)
        }
    }
}