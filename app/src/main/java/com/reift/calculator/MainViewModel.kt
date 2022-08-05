package com.reift.calculator

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var currentNumber: MutableLiveData<String> = MutableLiveData("0")
    var currentOperator: MutableLiveData<String> = MutableLiveData("")

    var isPositives: MutableLiveData<Boolean> = MutableLiveData(true)

//    var idleNumber: MutableLiveData<Int> = MutableLiveData(0)
    var resultNumber: MutableLiveData<Int> = MutableLiveData(0)

    fun numberClicked(number: Int) {
        if(currentNumber.value?.get(0).toString() == "0"){
            currentNumber.value = number.toString()
        } else {
            currentNumber.value = currentNumber.value + number.toString()
        }

    }

    fun operatorClicked(operator: String){
        if(currentNumber.value == "0") return
        currentOperator.value = operator
        if(resultNumber.value == 0){
            Log.i("clearCalculation", "going to if condition")
            resultNumber.value = currentNumber.value?.toInt()
            resetCurrentNumber()
        } else if (currentNumber.value != "0"){
            Log.i("clearCalculation", "going to else condition")
            doCalculation()
        }
    }

    private fun resetCurrentNumber(){
        currentNumber.value = "0"
    }

    private fun resetCurrentOperator(){
        currentOperator.value = ""
    }

    fun equalClicked() {
        if(currentNumber.value == "0") return
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
            "/" -> {
                resultNumber.value = resultNumber.value?.div(currentNumber.value!!.toInt())
            }
            "%" -> {
                resultNumber.value = resultNumber.value?.mod(currentNumber.value!!.toInt())
            }
        }
        resetCurrentNumber()
    }

    fun clearCalculation() {
        currentNumber.value = "0"
        currentOperator.value = ""
        resultNumber.value = 0
    }

    fun negativeOrPositive() {
        if (currentNumber.value == "") return
        isPositives.value = !isPositives.value!!
        Log.i("negativeOrPositive", "negativeOrPositive: ${isPositives.value}")
        currentNumber.value = currentNumber.value?.toInt()?.times(-1).toString()
    }

}