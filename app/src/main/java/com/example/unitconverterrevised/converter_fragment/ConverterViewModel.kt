package com.example.unitconverterrevised.converter_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel(name:String):ViewModel() {
    private val timeUnits= mutableListOf("Secs","Min","Hrs","Days","Weeks","Months","Years")
    private val tempUnits= mutableListOf("Kelvin","Fahrenheit","Celsius")
    private val lengthUnits= mutableListOf("mm","cm","inch","foot","meter","km","mile")
    private val weightUnits= mutableListOf("mg","ounce","gram","pound","kg","tonne")

    private val _labelText=MutableLiveData<String>()
    val labelText:LiveData<String> get() = _labelText
    private val _convertedValue=MutableLiveData<String>()
    val convertedValue:LiveData<String> get() = _convertedValue
    private val _spinnerList=MutableLiveData<MutableList<String>>()
    val spinnerList:LiveData<MutableList<String>> get() = _spinnerList

    init {
        _labelText.value=name
        when(name){
            "Time"->{
                _spinnerList.value=timeUnits
            }
            "Length"-> {
                _spinnerList.value=lengthUnits
            }
            "Temperature"-> {
                _spinnerList.value=tempUnits
            }
            else -> {
                _spinnerList.value=weightUnits
            }
        }
    }

    fun convertToMinimumValue (value:String, baseUnit:String,convertingUnit:String){
        var minimumValue = value.toFloat()
        when(baseUnit) {
            "cm" -> minimumValue *= 10
            "inch" -> minimumValue = ((minimumValue * 10) * 2.54).toFloat()
            "foot" -> minimumValue = (((minimumValue * 10) * 2.54) * 12).toFloat()
            "meter" -> minimumValue *= 1000
            "km" -> minimumValue *= 1000000
            "mile" -> minimumValue = ((minimumValue * 1000000) * 1.6).toFloat()
        }
        convertedValue(minimumValue,convertingUnit)
    }

    private fun convertedValue(number:Float,unit:String){
        var convertedValue=number
        when(unit){
            "cm"->  convertedValue/=10
            "inch"->  convertedValue= ((convertedValue/10)/2.54).toFloat()
            "foot"->  convertedValue= (((convertedValue/10)/2.54)/12).toFloat()
            "meter"->  convertedValue= ((convertedValue/10)/100)
            "km"->  convertedValue= ((convertedValue/10)/100)/1000
            "mile"->  convertedValue= ((((convertedValue/10)/100)/1000)/1.6).toFloat()
        }
        _convertedValue.value=convertedValue.toString()
    }
}