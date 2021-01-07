package br.com.petapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.petapp.model.PetModel

class PetIndexViewModel : ViewModel() {

    private val _pets = MutableLiveData<List<PetModel>>()
    val pets: LiveData<List<PetModel>> get() = _pets

    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String> get() = _navigateToDetail

    init {
        val petsData = ArrayList<PetModel>()
        petsData.add(PetModel(1,"Kevin", "Cat"))
        petsData.add(PetModel(2,"Quick", "Rabit"))
        petsData.add(PetModel(3,"Lassie", "Dog"))
        petsData.add(PetModel(4,"Jerry", "Mouse"))
        petsData.add(PetModel(5,"Meggie","Cat"))
        petsData.add(PetModel(6,"Mel", "Dog"))
        petsData.add(PetModel(7,"Mimi", "Cat"))
        petsData.add(PetModel(8,"Bobby", "Dog"))
        petsData.add(PetModel(9,"Jimmy", "Dog"))
        _pets.value = petsData
    }

    fun displayToDetail(petName: String) {
        _navigateToDetail.value = petName
    }

    fun displayToDetailComplete() {
        _navigateToDetail.value = null
    }
}