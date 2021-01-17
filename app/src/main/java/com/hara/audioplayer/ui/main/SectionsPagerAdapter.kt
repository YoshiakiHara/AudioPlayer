package com.hara.audioplayer.ui.main

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hara.audioplayer.R
import com.hara.audioplayer.SelectCategoryActivity

private val TAB_TITLES = arrayOf(
        R.string.tab_artist,
        R.string.tab_album
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context:Context, private val listener: RecyclerViewListener, fm: FragmentManager)
    : FragmentPagerAdapter(fm),RecyclerViewListener {
    companion object{
        private const val TAG = "SectionsPagerAdapter"
        private var _tabIndex: TabName = TabName.ALBUM
    }
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Log.i(TAG,"position:" + position)
        return PlaceholderFragment.newInstance(TabName.values()[position],this)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }

    override fun onClickCategoryButton(text: String) {
        Log.v("クリック", "$text")
        listener.onClickCategoryButton(text)
    }
}