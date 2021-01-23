package com.hara.audioplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import java.io.FileDescriptor


class PlayerActivity : AppCompatActivity(){
    companion object {
        private const val TAG = "PlayerActivity"
        private lateinit var currentNumber: MusicItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)

        val intent = getIntent()
        when(intent.getStringExtra("FROM")){
            "ARTIST" -> {
                ArtistNumberHolder.artistNumberList.forEachIndexed{ index,item ->
                    if(item.equals(intent.getStringExtra("NUMBER"))){
                        currentNumber = MusicDataHolder.musicItems.get(ArtistNumberHolder.artistNumberIdList.get(index))
                    }
                }
            }
            "ALBUM" -> {
                AlbumNumberHolder.albumNumberList.forEachIndexed{ index,item ->
                    if(item.equals(intent.getStringExtra("NUMBER"))){
                        currentNumber = MusicDataHolder.musicItems.get(AlbumNumberHolder.albumNumberIdList.get(index))
                    }
                }
            }
        }

        val pauseButton = findViewById(R.id.pause_button) as Button
        // 音楽ファイルをmediaplayerに設定
        CustomMediaPlayer.setDataSource(this,currentNumber.path.toUri())
        // 再生準備、再生可能状態になるまでブロック
        CustomMediaPlayer.prepare()
        // 再生開始
        CustomMediaPlayer.start()
        pauseButton.setOnClickListener {
            if(pauseButton.text.equals("pause")){
                pauseButton.text = "play"
                CustomMediaPlayer.pause()
            }else{
                pauseButton.text = "pause"
                CustomMediaPlayer.start()
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG,"Back Button Pressed");

    }
}