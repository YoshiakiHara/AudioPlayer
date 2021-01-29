package com.hara.audioplayer

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hara.audioplayer.ui.main.NumberAdapter
import com.hara.audioplayer.ui.main.RecyclerViewListener
import com.hara.audioplayer.ui.main.TransionFrom

class SelectAlbumNumberActibity: AppCompatActivity(), RecyclerViewListener {

    companion object {
        private const val TAG = "SelectAlbumNuberActibity"
        private lateinit var _fab: FloatingActionButton
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_albumnumber)
        _fab = findViewById(R.id.fab)
        PlayingFABController.initializeFAB(this, _fab)
        val intent = getIntent()
        AlbumNumberHolder.setArtistMusicList(intent.getStringExtra("ALBUM"))
        val recyclerView: RecyclerView = findViewById(R.id.albumnumber_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val albumList: MutableList<String> = mutableListOf()
        AlbumNumberHolder.albumNumberList.forEach{ i ->
            albumList.add(i.title)
        }
        recyclerView.adapter = NumberAdapter(albumList,this, TransionFrom.ALBUM)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG,"Back Button Pressed");
        AlbumNumberHolder.albumNumberList.clear()
    }

    override fun onClickRecyclerViewButton(id: String) {
        val intent = Intent(this@SelectAlbumNumberActibity, PlayerActivity::class.java)

        // todo Stringはどこかでまとめて定義
        intent.putExtra("ID", id);
        intent.putExtra("FROM", TransionFrom.ALBUM.ordinal);

        startActivity(intent)
    }
    override fun onRestart() {
        super.onRestart()
        PlayingFABController.initializeFAB(this, _fab)
    }
    override fun onDestroy() {
        super.onDestroy()
        val dataStore: SharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
        val editor: SharedPreferences.Editor = dataStore.edit()
        // todo String
        editor.putInt("SEEK_TIME", CustomMediaPlayer.getCurrentTime())
        editor.apply();
    }
}