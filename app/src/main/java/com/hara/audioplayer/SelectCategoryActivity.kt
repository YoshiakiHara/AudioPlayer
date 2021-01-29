package com.hara.audioplayer

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.hara.audioplayer.ui.main.RecyclerViewListener
import com.hara.audioplayer.ui.main.SectionsPagerAdapter
import com.hara.audioplayer.ui.main.TransionFrom


class SelectCategoryActivity : AppCompatActivity(), RecyclerViewListener {
    companion object{
        private const val TAG = "SelectCategoryActivity"
        private var _currentTab = TransionFrom.ARTIST
        private const val PERMISSION_READ_EX_STR = 1
        private lateinit var _fab: FloatingActionButton
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_category)

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
            }
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_READ_EX_STR);
        }

        // 音楽データの取得
        if(MusicDataHolder.musicItems.isEmpty()) {
            MusicDataHolder.setMusicInfoList(MusicItemManager.getItems(applicationContext))
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this,this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
        _fab = findViewById(R.id.fab)
        PlayingFABController.initializeFAB(this, _fab)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            // タブが選択された際に呼ばれる
            override fun onTabSelected(tab: TabLayout.Tab) {
                _currentTab = TransionFrom.values()[tab.position]
            }

            // タブが未選択になった際に呼ばれる
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            // 同じタブが選択された際に呼ばれる
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onClickRecyclerViewButton(category: String) {
        Log.v(TAG, "Click category:" + category)
        when(_currentTab) {
            TransionFrom.ARTIST -> {
                val intent = Intent(this@SelectCategoryActivity, SelectArtistNumberActivity::class.java)

                // todo Stringはどこかでまとめて定義
                intent.putExtra("ARTIST", category);
                startActivity(intent)
            }
            TransionFrom.ALBUM -> {
                val intent = Intent(this@SelectCategoryActivity, SelectAlbumNumberActibity::class.java)

                // todo Stringはどこかでまとめて定義
                intent.putExtra("ALBUM", category);
                startActivity(intent)
            }
            else -> {

            }
        }
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