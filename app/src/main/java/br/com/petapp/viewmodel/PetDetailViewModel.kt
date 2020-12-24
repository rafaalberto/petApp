package br.com.petapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PetDetailViewModel(petName: String) : ViewModel() {

    private val _pet = MutableLiveData<String>()
    val pet: LiveData<String> get() = _pet

    init {
        _pet.value = petName
    }

}