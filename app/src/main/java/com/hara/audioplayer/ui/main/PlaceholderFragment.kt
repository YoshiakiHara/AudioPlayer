package com.hara.audioplayer.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hara.audioplayer.MusicDataHolder
import com.hara.audioplayer.R
import com.hara.audioplayer.SelectCategoryActivity

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    companion object {
        private const val TAG = "PlaceholderFragment"
        private var _context: Context? = null

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber : TabName): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber.ordinal)
                }
            }
        }
    }

    private lateinit var pageViewModel: PageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            val num:Int? = arguments?.getInt(ARG_SECTION_NUMBER)

            if( num != null ) {
                Log.i(TAG,"tab number is null!!!" + num.toString());
                setIndex(TabName.values()[num] ?: TabName.OTHER)
            }else{
                Log.i(TAG,"tab number is null!!!");
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.item_list)
        recyclerView.layoutManager = LinearLayoutManager(_context)

        when (pageViewModel._index.value) {
            TabName.ARTIST -> {
                recyclerView.adapter = GeneralAdapter(MusicDataHolder.artistList)
            }
            TabName.ALBUM -> {
                recyclerView.adapter = GeneralAdapter(MusicDataHolder.albamList)
            }
            else -> {
                recyclerView.adapter = GeneralAdapter(MusicDataHolder.artistList)
            }
        }

        pageViewModel.text.observe(this, Observer<String> {
        })
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context = context
    }
}