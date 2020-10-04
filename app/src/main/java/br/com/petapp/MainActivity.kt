package br.com.petapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.petapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var petViewModel: PetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        petViewModel = ViewModelProviders.of(this).get(PetViewModel::class.java)
        binding.petViewModel = petViewModel

        petViewModel.display.observe(this, { binding.textViewDisplay.text = it })

        val pets = ArrayList<String>()
        pets.add("Rabbit 1")
        pets.add("Rabbit 2")
        pets.add("Rabbit 3")

        binding.petsList.adapter = PetAdapter(pets)
    }

}