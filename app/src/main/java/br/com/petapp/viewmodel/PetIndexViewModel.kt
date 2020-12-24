package br.com.petapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.petapp.model.Pet

class PetIndexViewModel : ViewModel() {

    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> get() = _pets

    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String> get() = _navigateToDetail

    init {
        val petsData = ArrayList<Pet>()
        petsData.add(Pet("Kevin", "Cat"))
        petsData.add(Pet("Quick", "Rabit"))
        petsData.add(Pet("Lassie", "Dog"))
        petsData.add(Pet("Jerry", "Mouse"))
        petsData.add(Pet("Meggie","Cat"))
        petsData.add(Pet("Mel", "Dog"))
        petsData.add(Pet("Mimi", "Cat"))
        petsData.add(Pet("Bobby", "Dog"))
        petsData.add(Pet("Jimmy", "Dog"))
        _pets.value = petsData
    }

    fun displayToDetail(petName: String) {
        _navigateToDetail.value = petName
    }

    fun displayToDetailComplete() {
        _navigateToDetail.value = null
    }
}