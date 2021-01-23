package com.hara.audioplayer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
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
        private const val PERMISSION_READ_EX_STR = 1
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

    override fun onClickRecyclerViewButton(category: String) {
        Log.v(TAG, "Click category:" + "$category")
        when(_currentTab) {
            TabName.ARTIST -> {
                val intent = Intent(this@SelectCategoryActivity, SelectArtistNumberActivity::class.java)

                // todo Stringはどこかでまとめて定義
                intent.putExtra("ARTIST", category);
                startActivity(intent)
            }
            TabName.ALBUM -> {
                val intent = Intent(this@SelectCategoryActivity, SelectAlbumNuberActibity::class.java)

                // todo Stringはどこかでまとめて定義
                intent.putExtra("ALBUM", category);
                startActivity(intent)
            }
            else -> {

            }
        }
    }
}