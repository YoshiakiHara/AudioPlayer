package com.hara.audioplayer

import android.util.Log

object MusicDataHolder {
    var musicItems: MutableList<MusicItem> = mutableListOf()
    var artistList: MutableList<String> = mutableListOf()
    var albamList: MutableList<String> = mutableListOf()
    private const val TAG = "MusicDataHolder"
    fun setMusicInfoList(musicinfList:MutableList<MusicItem> ){
        musicItems = musicinfList
        if(musicItems.isNotEmpty()) {
            musicItems.forEach { i ->
                if(!artistList.contains(i.artist)){
                    artistList.add(i.artist)
                }
                if(!albamList.contains(i.album)){
                    albamList.add(i.album)
                }
//                Log.i(TAG, "artist:" + i.artist + " album:" + i.album)
            }
        }
    }
}