package br.com.petapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.petapp.database.PetDatabase
import br.com.petapp.database.entity.PetEntity
import br.com.petapp.database.repository.PetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PetDetailViewModel(application: Application, private val petId: Long) :
    AndroidViewModel(application) {

    private val _navigateToIndex = MutableLiveData<Boolean>()
    val navigateToIndex: LiveData<Boolean> get() = _navigateToIndex

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val petRepository = PetRepository(PetDatabase.getInstance(application).petDao)

    val pet = MediatorLiveData<PetEntity>()

    init {
        pet.addSource(petRepository.findById(petId), pet::setValue)
        _navigateToIndex.value = false
    }

    fun save(pet: PetEntity) {
        uiScope.launch {
            if (petId == 0L) {
                petRepository.insert(pet)
            } else {
                pet.id = petId
                petRepository.update(pet)
            }
        }
        _navigateToIndex.value = true
    }

    fun delete() {
        uiScope.launch {
            petRepository.delete(petId)
        }
        _navigateToIndex.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}