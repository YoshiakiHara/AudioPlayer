package com.hara.audioplayer

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

object CustomMediaPlayer {
    var mediaPlayer = MediaPlayer()
    fun setDataSource(context: Context, uri: Uri){
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.release()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(context,uri)
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