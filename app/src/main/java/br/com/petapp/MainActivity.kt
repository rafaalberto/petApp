package br.com.petapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.petapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.textViewDisplay.text = getString(R.string.text_one)
        binding.buttonText.setOnClickListener { displayText() }
    }

    private fun displayText() {
        binding.textViewDisplay.text = getString(R.string.text_two)
    }

}