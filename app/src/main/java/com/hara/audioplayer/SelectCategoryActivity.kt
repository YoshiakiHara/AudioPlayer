package com.hara.audioplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.hara.audioplayer.ui.main.RecyclerViewListener
import com.hara.audioplayer.ui.main.SectionsPagerAdapter
import com.hara.audioplayer.ui.main.TabName


class SelectCategoryActivity : AppCompatActivity(), RecyclerViewListener {
    companion object{
        private const val TAG = "SelectCategoryActivity"
        private var _currentTab = TabName.ARTIST
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_category)

        // 音楽データの取得
        MusicDataHolder.setMusicInfoList(MusicItemManager.getItems(applicationContext))

        val sectionsPagerAdapter = SectionsPagerAdapter(this,this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            // タブが選択された際に呼ばれる
            override fun onTabSelected(tab: TabLayout.Tab) {
                _currentTab = TabName.values()[tab.position]
            }

            // タブが未選択になった際に呼ばれる
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            // 同じタブが選択された際に呼ばれる
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onClickCategoryButton(artist: String) {
        Log.v(TAG, "Click category:" + "$artist")
        when(_currentTab) {
            TabName.ARTIST -> {
                val intent = Intent(this@SelectCategoryActivity, SelectNumberActivity::class.java)

                // todo Stringはどこかでまとめて定義
                intent.putExtra("ARTIST", artist);
                startActivity(intent)
            }
            TabName.ALBUM -> {

            }
            else -> {

            }
        }
    }
}