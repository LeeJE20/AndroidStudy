package com.example.up.ui.home.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    val title: LiveData<String> = Transformations.map(_index) {
//        "Tab $it"
        when(it)
        {
            1-> "과학"
            2-> "스포츠"
            else->"에러"
        }

    }


    fun setIndex(index: Int) {
        _index.value = index
    }
}