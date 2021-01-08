package br.com.petapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.petapp.database.PetDatabase
import br.com.petapp.database.repository.PetRepository

class PetIndexViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigateToDetail = MutableLiveData<Long>()
    val navigateToDetail: LiveData<Long> get() = _navigateToDetail

    private val petRepository = PetRepository(PetDatabase.getInstance(application).petDao)

    val pets = petRepository.findAll()

    fun displayToDetail(id: Long) {
        _navigateToDetail.value = id
    }

    fun displayToDetailComplete() {
        _navigateToDetail.value = null
    }
}