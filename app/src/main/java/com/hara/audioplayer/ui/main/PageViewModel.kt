package com.hara.audioplayer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    public val _index = MutableLiveData<TabName>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: TabName) {
        _index.value = index
    }
}

enum class TabName() {
    ARTIST,
    ALBUM,
    OTHER
}