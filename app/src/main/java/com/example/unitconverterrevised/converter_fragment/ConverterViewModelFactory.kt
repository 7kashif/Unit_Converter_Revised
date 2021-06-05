package com.example.unitconverterrevised.converter_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ConverterViewModelFactory(private val title:String):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConverterViewModel::class.java))
            return ConverterViewModel(title) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}