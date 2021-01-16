package com.hara.audioplayer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hara.audioplayer.R

class GeneralAdapter(private val myDataset: MutableList<String>) :
    RecyclerView.Adapter<GeneralAdapter.ViewHolder>() {

    // RecyclerViewの一要素となるXML要素の型を引数に指定する
    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralAdapter.ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.general_button, parent, false) as Button
        return ViewHolder(textView)
    }

    // 第１引数のViewHolderはこのファイルの上のほうで作成した`class ViewHolder`です。
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size
}