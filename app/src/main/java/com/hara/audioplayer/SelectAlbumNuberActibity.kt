package com.hara.audioplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hara.audioplayer.ui.main.GeneralAdapter
import com.hara.audioplayer.ui.main.RecyclerViewListener

class SelectAlbumNuberActibity: AppCompatActivity(), RecyclerViewListener {

    companion object {
        private const val TAG = "SelectAlbumNuberActibity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_albumnumber)

        val intent = getIntent()
        AlbumNumberHolder.setArtistMusicList(intent.getStringExtra("ALBUM"))
        val recyclerView: RecyclerView = findViewById(R.id.albumnumber_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GeneralAdapter(AlbumNumberHolder.albumNumberList,this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG,"Back Button Pressed");
        AlbumNumberHolder.albumNumberIdList.clear()
        AlbumNumberHolder.albumNumberList.clear()
    }

    override fun onClickRecyclerViewButton(number: String) {
        val intent = Intent(this@SelectAlbumNuberActibity, PlayerActivity::class.java)

        // todo Stringはどこかでまとめて定義
        intent.putExtra("NUMBER", number);
        intent.putExtra("FROM", "ALBUM");

        startActivity(intent)
    }
}