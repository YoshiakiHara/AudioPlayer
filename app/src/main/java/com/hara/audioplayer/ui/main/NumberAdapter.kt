package com.hara.audioplayer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.hara.audioplayer.AlbumNumberHolder
import com.hara.audioplayer.ArtistNumberHolder
import com.hara.audioplayer.R

class NumberAdapter(private val myDataset: MutableList<String>, private val listener: RecyclerViewListener, private val transition:TransionFrom) :
    RecyclerView.Adapter<NumberAdapter.ViewHolder>() {

    // RecyclerViewの一要素となるXML要素の型を引数に指定する
//    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    class ViewHolder(val button: Button): RecyclerView.ViewHolder(button)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberAdapter.ViewHolder {
        val button = LayoutInflater.from(parent.context)
            .inflate(R.layout.general_button, parent, false) as Button
        return ViewHolder(button)
    }

    // 第１引数のViewHolderはこのファイルの上のほうで作成した`class ViewHolder`です。
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.button.apply {
            holder.button.text = myDataset[position]
            holder.button.setOnClickListener {
                when(transition){
                    TransionFrom.ARTIST -> {
                        listener.onClickRecyclerViewButton(ArtistNumberHolder.artistNumberList[position].id.toString())
                    }
                    TransionFrom.ALBUM -> {
                        listener.onClickRecyclerViewButton(AlbumNumberHolder.albumNumberList[position].id.toString())
                    }
                }
            }
        }
    }

    override fun getItemCount() = myDataset.size
}