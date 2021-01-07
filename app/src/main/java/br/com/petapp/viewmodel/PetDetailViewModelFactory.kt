package br.com.petapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PetDetailViewModelFactory(
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetDetailViewModel::class.java))
            return PetDetailViewModel(application) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}