package com.reift.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.reift.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.currentNumber.observe(this){
            binding.tvCurrentNumber.text = it
        }

        viewModel.currentOperator.observe(this){
            binding.tvOperator.text = it
        }

        viewModel.resultNumber.observe(this){
            binding.tvResult.text = it.toString()
        }

        viewModel.isPositives.observe(this){
            if(it){
                binding.btnPosOrNeg.text = "M"
            } else {
                binding.btnPosOrNeg.text = "P"
            }
        }


        setUpClickListener()

    }

    private fun setUpClickListener() {
        val listener = View.OnClickListener { view ->
            when (view.id) {
                R.id.btn_0 -> viewModel.numberClicked(0)
                R.id.btn_1 -> viewModel.numberClicked(1)
                R.id.btn_2 -> viewModel.numberClicked(2)
                R.id.btn_3 -> viewModel.numberClicked(3)
                R.id.btn_4 -> viewModel.numberClicked(4)
                R.id.btn_5 -> viewModel.numberClicked(5)
                R.id.btn_6 -> viewModel.numberClicked(6)
                R.id.btn_7 -> viewModel.numberClicked(7)
                R.id.btn_8 -> viewModel.numberClicked(8)
                R.id.btn_9 -> viewModel.numberClicked(9)
                R.id.btn_plus -> viewModel.operatorClicked("+")
                R.id.btn_minus -> viewModel.operatorClicked("-")
                R.id.btn_multiply -> viewModel.operatorClicked("*")
                R.id.btn_divide -> viewModel.operatorClicked("/")
                R.id.btn_modulus -> viewModel.operatorClicked("%")
                R.id.btn_equal -> viewModel.equalClicked()
                R.id.btn_clear -> viewModel.clearCalculation()
                R.id.btn_pos_or_neg -> viewModel.negativeOrPositive()
            }
        }

        binding.btn0.setOnClickListener(listener)
        binding.btn1.setOnClickListener(listener)
        binding.btn2.setOnClickListener(listener)
        binding.btn3.setOnClickListener(listener)
        binding.btn4.setOnClickListener(listener)
        binding.btn5.setOnClickListener(listener)
        binding.btn6.setOnClickListener(listener)
        binding.btn7.setOnClickListener(listener)
        binding.btn8.setOnClickListener(listener)
        binding.btn9.setOnClickListener(listener)
        binding.btnPlus.setOnClickListener(listener)
        binding.btnMinus.setOnClickListener(listener)
        binding.btnMultiply.setOnClickListener(listener)
        binding.btnDivide.setOnClickListener(listener)
        binding.btnEqual.setOnClickListener(listener)
        binding.btnClear.setOnClickListener(listener)
        binding.btnPosOrNeg.setOnClickListener(listener)
        binding.btnModulus.setOnClickListener(listener)
    }

}