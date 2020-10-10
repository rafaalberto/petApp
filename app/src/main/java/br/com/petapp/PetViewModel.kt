package br.com.petapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PetViewModel : ViewModel() {

    private val _display = MutableLiveData<String>()
    val display: LiveData<String> get() = _display

    private val _pets = MutableLiveData<List<String>>()
    val pets: LiveData<List<String>> get() = _pets

    init {
        val petsName = ArrayList<String>()
        petsName.add("Quick")
        petsName.add("Tom")
        petsName.add("Lassie")
        petsName.add("Jerry")
        petsName.add("Meggie")
        petsName.add("Mel")
        petsName.add("Mimi")
        petsName.add("Bobby")
        petsName.add("Jimmy")
        _pets.value = petsName
    }

    fun displayText() {
        _display.value = "Hello With View Model"
    }

}