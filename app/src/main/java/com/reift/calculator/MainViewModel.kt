package com.reift.calculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var currentNumber: MutableLiveData<String> = MutableLiveData("")
    var currentOperator: MutableLiveData<String> = MutableLiveData("")

    var idleNumber: MutableLiveData<Int>? = MutableLiveData()
    var resultNumber: MutableLiveData<Int>? = MutableLiveData()

    fun numberClicked(number: Int) {
        currentNumber.value = currentNumber.value + number.toString()
    }

    fun operatorClicked(operator: String){

        currentOperator.value = operator

        idleNumber?.value = currentNumber.value?.toInt()

        resetCurrentNumber()
    }

    private fun resetCurrentNumber(){
        currentNumber.value = ""
    }


}