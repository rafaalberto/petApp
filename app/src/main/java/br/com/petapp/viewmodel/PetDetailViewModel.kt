package br.com.petapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.petapp.database.PetDatabase
import br.com.petapp.database.entity.Pet
import kotlinx.coroutines.*

class PetDetailViewModel(application: Application, private val petId: Long) :
    AndroidViewModel(application) {

    private val _navigateToIndex = MutableLiveData<Boolean>()
    val navigateToIndex: LiveData<Boolean> get() = _navigateToIndex

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val petDao = PetDatabase.getInstance(application).petDao

    val pet = MediatorLiveData<Pet>()

    init {
        pet.addSource(petDao.findById(petId), pet::setValue)
        _navigateToIndex.value = false
    }

    fun save(pet: Pet) {
        uiScope.launch {
            if (petId == 0L) insert(pet) else update(pet)
        }
        _navigateToIndex.value = true
    }

    fun delete() {
        uiScope.launch {
            delete(petId)
        }
        _navigateToIndex.value = true
    }

    private suspend fun insert(pet: Pet) {
        return withContext(Dispatchers.IO) {
            petDao.insert(pet)
        }
    }

    private suspend fun update(pet: Pet) {
        return withContext(Dispatchers.IO) {
            pet.id = petId
            petDao.update(pet)
        }
    }

    private suspend fun delete(petId: Long) {
        return withContext(Dispatchers.IO) {
            val pet = petDao.getById(petId) ?: return@withContext
            petDao.delete(pet)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}