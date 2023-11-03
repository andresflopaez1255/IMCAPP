package com.hefestsoft.imcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hefestsoft.imcapp.MainActivity.Companion.IMC_STRING
import com.hefestsoft.imcapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
      val result =  intent.extras?.getDouble(IMC_STRING) ?: -1.0
        val resultFormat = String.format("%.1f", result)
      binding.tvResult.text = resultFormat

        binding.btnGotoCalculate.setOnClickListener {
            finish()

        }

    }
}


