package com.hara.audioplayer

import android.util.Log
import com.hara.audioplayer.MusicDataHolder.musicItems

object ArtistNumberHolder {
    var artistNumberList: MutableList<String> = mutableListOf()
    var artistalbamList: MutableList<String> = mutableListOf()
    private const val TAG = "MusicDataHolder"
    fun setArtistMusicList(artist: String){
        if(musicItems.isNotEmpty()) {
            musicItems.forEach { i ->
                if(i.artist.equals(artist)){
                    artistNumberList.add(i.title)
                    if(!artistalbamList.contains(i.album)){
                        artistalbamList.add(i.album)
                    }
                }
                Log.i(TAG, "artist:" + i.artist + " album:" + i.album)
            }
        }
    }
}