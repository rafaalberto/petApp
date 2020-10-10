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
        val petsList = ArrayList<String>()
        petsList.add("Rabbit 1")
        petsList.add("Rabbit 2")
        petsList.add("Rabbit 3")
        petsList.add("Rabbit 4")
        petsList.add("Rabbit 5")
        petsList.add("Rabbit 6")
        _pets.value = petsList
    }

    fun displayText() {
        _display.value = "Hello With View Model"
    }

}