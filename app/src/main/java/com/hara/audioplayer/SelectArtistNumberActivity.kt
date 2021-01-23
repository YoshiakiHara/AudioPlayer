package com.hara.audioplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hara.audioplayer.ui.main.GeneralAdapter
import com.hara.audioplayer.ui.main.RecyclerViewListener


class SelectArtistNumberActivity : AppCompatActivity(), RecyclerViewListener {

    companion object {
        private const val TAG = "SelectNumberActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_artistnumber)

        val intent = getIntent()
        ArtistNumberHolder.setArtistMusicList(intent.getStringExtra("ARTIST"))
        val recyclerView: RecyclerView = findViewById(R.id.artistnumber_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GeneralAdapter(ArtistNumberHolder.artistNumberList,this)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG,"Back Button Pressed");
        ArtistNumberHolder.artistNumberIdList.clear()
        ArtistNumberHolder.artistNumberList.clear()
    }
    override fun onClickRecyclerViewButton(number: String) {
        val intent = Intent(this@SelectArtistNumberActivity, PlayerActivity::class.java)

        // todo Stringはどこかでまとめて定義
        intent.putExtra("NUMBER", number);
        intent.putExtra("FROM", "ARTIST");

        startActivity(intent)
    }
}