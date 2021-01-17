package com.hara.audioplayer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hara.audioplayer.ui.main.GeneralAdapter
import com.hara.audioplayer.ui.main.RecyclerViewListener


class SelectNumberActivity : AppCompatActivity(),RecyclerViewListener {

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

    override fun onClickCategoryButton(text: String) {
        Log.v(TAG, "Click title:" + "$text")
    }
}