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
            //units for length
            "cm" -> minimumValue *= 10
            "inch" -> minimumValue = ((minimumValue * 10) * 2.54).toFloat()
            "foot" -> minimumValue = (((minimumValue * 10) * 2.54) * 12).toFloat()
            "meter" -> minimumValue *= 1000
            "km" -> minimumValue *= 1000000
            "mile" -> minimumValue = ((minimumValue * 1000000) * 1.6).toFloat()
            //units for time
            "Min"-> minimumValue *= 60
            "Hrs"-> minimumValue*=3600
            "Days"-> minimumValue=((minimumValue*24)*3600)
            "Weeks"-> minimumValue=(((minimumValue*7)*24)*3600)
            "Months"-> minimumValue=(((minimumValue*30)*24)*3600)
            "Years"-> minimumValue=(((minimumValue*365)*24)*3600)
            //units for temperature (it will be converted in to Celsius)
            "Kelvin"-> minimumValue=(minimumValue-273.15).toFloat()
            "Fahrenheit"->minimumValue=(minimumValue-32)*(5/9)
            //for weight
            "ounce"-> minimumValue=(minimumValue*28349.5).toFloat()
            "gram"-> minimumValue*=1000
            "pound"-> minimumValue*=453592
            "kg"-> minimumValue*=1000000
            "tonne"-> minimumValue=(minimumValue*1000000)*1000
        }
        convertedValue(minimumValue,convertingUnit)
    }

    private fun convertedValue(number:Float,unit:String){
        var convertedValue=number
        when(unit){
            //units for length
            "cm"->  convertedValue/=10
            "inch"->  convertedValue= ((convertedValue/10)/2.54).toFloat()
            "foot"->  convertedValue= (((convertedValue/10)/2.54)/12).toFloat()
            "meter"->  convertedValue= ((convertedValue/10)/100)
            "km"->  convertedValue= ((convertedValue/10)/100)/1000
            "mile"->  convertedValue= ((((convertedValue/10)/100)/1000)/1.6).toFloat()
            //units for time
            "Min"-> convertedValue/=60
            "Hrs"-> convertedValue/=3600
            "Days"-> convertedValue=(convertedValue/3600)/24
            "Weeks"-> convertedValue=(((convertedValue/3600)/24)/7)
            "Months"->convertedValue=(((convertedValue/3600)/24)/30)
            "Years"->convertedValue=(((convertedValue/3600)/24)/365)
            //units for temperature
            "Kelvin"-> convertedValue=(convertedValue+ 273.15).toFloat()
            "Fahrenheit"->convertedValue=(convertedValue*(9/5))+32
            //for weights
            "ounce"-> convertedValue=(convertedValue/28349.5).toFloat()
            "gram"-> convertedValue/=1000
            "pound"-> convertedValue/=453592
            "kg"-> convertedValue/=1000000
            "tonne"-> convertedValue=(convertedValue/1000000)/1000
        }
        _convertedValue.value=convertedValue.toString()
    }
}