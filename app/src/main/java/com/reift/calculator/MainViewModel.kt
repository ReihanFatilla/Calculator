package com.reift.calculator

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var currentNumber: MutableLiveData<String> = MutableLiveData("")
    var currentOperator: MutableLiveData<String> = MutableLiveData("")

    var isPositives: MutableLiveData<Boolean> = MutableLiveData(true)

//    var idleNumber: MutableLiveData<Int> = MutableLiveData(0)
    var resultNumber: MutableLiveData<Int> = MutableLiveData(0)

    fun numberClicked(number: Int) {
        currentNumber.value = currentNumber.value + number.toString()
    }

    fun operatorClicked(operator: String){
        currentOperator.value = operator
        if(resultNumber.value == 0){
            Log.i("clearCalculation", "going to if condition")
            resultNumber.value = currentNumber.value?.toInt()
            resetCurrentNumber()
        } else if (currentNumber.value != ""){
            Log.i("clearCalculation", "going to else condition")
            doCalculation()
        }
    }

    private fun resetCurrentNumber(){
        currentNumber.value = ""
    }

    private fun resetCurrentOperator(){
        currentOperator.value = ""
    }

    fun equalClicked() {
        if(currentNumber.value == "") return
        doCalculation()
        resetCurrentOperator()
    }

    private fun doCalculation(){
        when(currentOperator.value){
            "+" -> {
//                Log.i("clearCalculation", "plus operator runned! ")
//                Log.i("clearCalculation", "idleNumber : ${resultNumber.value} ")
                resultNumber.value = resultNumber.value?.plus(currentNumber.value!!.toInt())
//                Log.i("clearCalculation", "idleNumber after plus : ${resultNumber.value} ")
            }
            "-" -> {
                resultNumber.value = resultNumber.value?.minus(currentNumber.value!!.toInt())
            }
            "*" -> {
                resultNumber.value = resultNumber.value?.times(currentNumber.value!!.toInt())
            }
        }
        resetCurrentNumber()
    }

    fun clearCalculation() {
        currentNumber.value = ""
        currentOperator.value = ""
        resultNumber.value = 0
    }

    fun negativeOrPositive(status: String) {
        Log.i("negativeOrPositive", "negativeOrPositive: ${isPositives.value}")
        when(status){
            "P" -> isPositives.value = true
            "M" -> isPositives.value = false
        }
    }

}