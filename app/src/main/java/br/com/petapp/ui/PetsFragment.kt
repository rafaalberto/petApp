package br.com.petapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.petapp.R
import br.com.petapp.databinding.FragmentPetsBinding
import br.com.petapp.viewmodel.PetViewModel

class PetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPetsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pets, container, false)
        binding.lifecycleOwner = this

        val petViewModel: PetViewModel = ViewModelProviders.of(this).get(PetViewModel::class.java)
        binding.petViewModel = petViewModel

        val adapter = PetAdapter()
        binding.petsList.adapter = adapter

        petViewModel.pets.observe(this, { it?.let { adapter.submitList(it) } })

        return binding.root
    }
}