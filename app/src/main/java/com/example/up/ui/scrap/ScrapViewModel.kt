package com.example.up.ui.scrap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScrapViewModel : ViewModel()
{

    private val _text = MutableLiveData<String>().apply {
        value = "This is scrap Fragment"
    }
    val text: LiveData<String> = _text
}