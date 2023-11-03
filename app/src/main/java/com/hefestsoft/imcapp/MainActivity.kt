package com.hefestsoft.imcapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.hefestsoft.imcapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var weight = 0
    private var height = 0
    private var age = 0

    enum class CardName {
        IS_MALE, IS_FEMALE
    }
    companion object {
        const val IMC_STRING = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewMale.setOnClickListener { setColorCard(CardName.IS_MALE) }
        binding.viewFemale.setOnClickListener { setColorCard(CardName.IS_FEMALE) }



        binding.rsHeight.addOnChangeListener { _, value, _ ->
            setHeight(value)
        }


        binding.edtWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }


            override fun onTextChanged(
                text: CharSequence, start: Int,
                before: Int, count: Int
            ) {

                weight = if (text.isNotEmpty()) text.toString().toInt() else 0
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.edtAge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }


            override fun onTextChanged(
                text: CharSequence, start: Int,
                before: Int, count: Int
            ) {

                 age = if (text.isNotBlank()) text.toString().toInt() else 0

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.btnIMCCalculate.setOnClickListener {
           val result = calculateIMC()
            navigateToResult(result)
        }

    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this,ResultActivity::class.java)
        intent.putExtra(IMC_STRING,result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val heightMetros = height.toDouble()/100
        return weight/(heightMetros*heightMetros)
    }

    @SuppressLint("SetTextI18n")
    private fun setHeight(value: Float) {
        binding.txtHeight.text = "${value.toInt()} cm"
        height = value.toInt()
    }


    private fun setColorCard(isViewSelected: CardName) {

        when (isViewSelected) {
            CardName.IS_MALE -> {
                binding.viewMale.setCardBackgroundColor(resources.getColor(R.color.background_component_selected))
                binding.viewFemale.setCardBackgroundColor(resources.getColor(R.color.background_component))
            }
            CardName.IS_FEMALE -> {
                binding.viewFemale.setCardBackgroundColor(resources.getColor(R.color.background_component_selected))
                binding.viewMale.setCardBackgroundColor(resources.getColor(R.color.background_component))
            }
        }

    }
}