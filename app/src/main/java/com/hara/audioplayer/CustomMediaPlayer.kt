package com.hara.audioplayer

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.core.net.toUri

object CustomMediaPlayer {
    var mediaPlayer = MediaPlayer()
    lateinit var currentPlayingMusic: MusicItem
    fun setDataSource(context: Context){
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.release()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(context,currentPlayingMusic.path.toUri())
    }
    fun prepare(){
        mediaPlayer.prepare()
    }
    fun start(){
        mediaPlayer.start()
    }
    fun pause(){
        mediaPlayer.pause()
    }
}