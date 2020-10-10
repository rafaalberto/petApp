package br.com.petapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PetViewModel : ViewModel() {

    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> get() = _pets

    init {
        val petsData = ArrayList<Pet>()
        petsData.add(Pet("Quick", "Rabit"))
        petsData.add(Pet("Tom", "Cat"))
        petsData.add(Pet("Lassie", "Dog"))
        petsData.add(Pet("Jerry", "Mouse"))
        petsData.add(Pet("Meggie","Cat"))
        petsData.add(Pet("Mel", "Dog"))
        petsData.add(Pet("Mimi", "Cat"))
        petsData.add(Pet("Bobby", "Dog"))
        petsData.add(Pet("Jimmy", "Dog"))
        _pets.value = petsData
    }

}