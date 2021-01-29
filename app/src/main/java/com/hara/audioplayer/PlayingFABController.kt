package com.hara.audioplayer

import android.content.Context
import android.content.Intent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hara.audioplayer.ui.main.TransionFrom

object PlayingFABController {

    fun initializeFAB(context:Context,fab:FloatingActionButton){
        val data = context.getSharedPreferences("Data", Context.MODE_PRIVATE)
        val dataString: String? = data.getString("Playing", null)
        if(dataString == null){
            fab.hide()
        }else{
            fab.show()
        }
        fab.setOnClickListener { view ->
            val intent = Intent(context, PlayerActivity::class.java)

            MusicDataHolder.musicItems.forEach { item ->
                if(item.path.equals(dataString)){
                    intent.putExtra("ID", item.id.toString());
                }
            }
            // todo Stringはどこかでまとめて定義
            intent.putExtra("FROM", TransionFrom.FLOATEING_ACTION_BUTTON);
            context.startActivity(intent)
        }
    }
}