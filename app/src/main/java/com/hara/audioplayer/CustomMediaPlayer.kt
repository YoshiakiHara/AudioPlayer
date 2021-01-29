package com.hara.audioplayer

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.hara.audioplayer.ui.main.TransionFrom

object CustomMediaPlayer {
    var mediaPlayer = MediaPlayer()
    lateinit var currentPlayingMusic: MusicItem
    lateinit var transition: TransionFrom
    var playedFlag: Boolean = false
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
    fun start(context:Context){
        mediaPlayer.start()
        val dataStore: SharedPreferences = context.getSharedPreferences("Data",
            AppCompatActivity.MODE_PRIVATE
        );
        val editor: SharedPreferences.Editor = dataStore.edit()
        // todo String
        editor.putString("Playing", currentPlayingMusic.path)
        editor.putInt("FROM", transition.ordinal)
        editor.apply();
        playedFlag = true
    }
    fun pause(context: Context){
        mediaPlayer.pause()
        val dataStore: SharedPreferences = context.getSharedPreferences("Data", AppCompatActivity.MODE_PRIVATE);
        val editor: SharedPreferences.Editor = dataStore.edit()
        // todo String
        editor.putInt("SEEK_TIME", CustomMediaPlayer.getCurrentTime())
        editor.apply();
    }

    fun getCurrentTime(): Int{
        return mediaPlayer.currentPosition
    }

    fun seekTo(seekTime: Int) {
        mediaPlayer.seekTo(seekTime)
    }
}