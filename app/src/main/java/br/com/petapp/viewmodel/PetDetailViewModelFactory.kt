package br.com.petapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PetDetailViewModelFactory(private val petName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetDetailViewModel::class.java)) return PetDetailViewModel(petName) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}