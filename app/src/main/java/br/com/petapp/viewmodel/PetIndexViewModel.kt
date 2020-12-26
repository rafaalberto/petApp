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
        petsData.add(Pet(1,"Kevin", "Cat"))
        petsData.add(Pet(2,"Quick", "Rabit"))
        petsData.add(Pet(3,"Lassie", "Dog"))
        petsData.add(Pet(4,"Jerry", "Mouse"))
        petsData.add(Pet(5,"Meggie","Cat"))
        petsData.add(Pet(6,"Mel", "Dog"))
        petsData.add(Pet(7,"Mimi", "Cat"))
        petsData.add(Pet(8,"Bobby", "Dog"))
        petsData.add(Pet(9,"Jimmy", "Dog"))
        _pets.value = petsData
    }

    fun displayToDetail(petName: String) {
        _navigateToDetail.value = petName
    }

    fun displayToDetailComplete() {
        _navigateToDetail.value = null
    }
}