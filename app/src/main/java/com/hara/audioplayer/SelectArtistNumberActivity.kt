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


class SelectArtistNumberActivity : AppCompatActivity(), RecyclerViewListener {

    companion object {
        private const val TAG = "SelectNumberActivity"
        private lateinit var _fab: FloatingActionButton
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_artistnumber)
        _fab = findViewById(R.id.fab)
        PlayingFABController.initializeFAB(this, _fab)
        val intent = getIntent()
        ArtistNumberHolder.setArtistMusicList(intent.getStringExtra("ARTIST"))
        val recyclerView: RecyclerView = findViewById(R.id.artistnumber_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val artistList: MutableList<String> = mutableListOf()
        ArtistNumberHolder.artistNumberList.forEach{ i ->
            artistList.add(i.title)
        }
        recyclerView.adapter = NumberAdapter(artistList,this, TransionFrom.ARTIST)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG,"Back Button Pressed");
        ArtistNumberHolder.artistNumberList.clear()
    }
    override fun onClickRecyclerViewButton(id: String) {
        val intent = Intent(this@SelectArtistNumberActivity, PlayerActivity::class.java)

        // todo Stringはどこかでまとめて定義
        intent.putExtra("ID", id);
        intent.putExtra("FROM", TransionFrom.ARTIST.ordinal);

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