package com.hara.audioplayer

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.hara.audioplayer.ui.main.TransionFrom
import java.io.FileDescriptor


class PlayerActivity : AppCompatActivity(){
    companion object {
        private const val TAG = "PlayerActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)

        val pauseButton = findViewById(R.id.pause_button) as Button
        pauseButton.setOnClickListener {
            if(pauseButton.text.equals("pause")){
                pauseButton.text = "play"
                CustomMediaPlayer.pause(this)
            }else{
                pauseButton.text = "pause"
                CustomMediaPlayer.start(this)
            }
        }

        val intent = getIntent()
        CustomMediaPlayer.transition =TransionFrom.values()[intent.getIntExtra("FROM",0)]
        val numId = intent.getStringExtra("ID")
        when(CustomMediaPlayer.transition){
            TransionFrom.ARTIST -> {
                MusicDataHolder.musicItems.forEach loop@{ item ->
                    if(numId.equals(item.id.toString())){
                        CustomMediaPlayer.currentPlayingMusic = item
                        CustomMediaPlayer.setDataSource(this)
                        CustomMediaPlayer.prepare()
                        CustomMediaPlayer.start(this)
                        return@loop
                    }
                }
            }
            TransionFrom.ALBUM -> {
                MusicDataHolder.musicItems.forEach loop@{ item ->
                    if(numId.equals(item.id.toString())){
                        CustomMediaPlayer.currentPlayingMusic = item
                        CustomMediaPlayer.setDataSource(this)
                        CustomMediaPlayer.prepare()
                        CustomMediaPlayer.start(this)
                        return@loop
                    }
                }
            }
            TransionFrom.FLOATEING_ACTION_BUTTON -> {
                // todo 今回の起動中に一度でも再生したか
                if(CustomMediaPlayer.playedFlag) {
                    if (CustomMediaPlayer.mediaPlayer.isPlaying) {
                        // 再生中は何もしない
                    }else{
                        pauseButton.text = "play"
                    }
                }else{
                    val data = getSharedPreferences("Data", Context.MODE_PRIVATE)
                    val seekTime: Int = data.getInt("SEEK_TIME", 0)
                    MusicDataHolder.musicItems.forEach loop@{ item ->
                        if(numId.equals(item.id.toString())){
                            CustomMediaPlayer.currentPlayingMusic = item
                            CustomMediaPlayer.setDataSource(this)
                            CustomMediaPlayer.prepare()
                            CustomMediaPlayer.seekTo(seekTime)
                            pauseButton.text = "play"
                            return@loop
                        }
                    }
                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG,"Back Button Pressed");

    }

    override fun onDestroy() {
        super.onDestroy()
        val dataStore: SharedPreferences = getSharedPreferences("Data",MODE_PRIVATE);
        val editor: SharedPreferences.Editor = dataStore.edit()
        // todo String
        editor.putInt("SEEK_TIME", CustomMediaPlayer.getCurrentTime())
        editor.apply();
    }
}