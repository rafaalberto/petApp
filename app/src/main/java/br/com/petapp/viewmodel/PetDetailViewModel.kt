package br.com.petapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.petapp.database.PetDatabase
import br.com.petapp.database.entity.Pet
import kotlinx.coroutines.*

class PetDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigateToIndex = MutableLiveData<Boolean>()
    val navigateToIndex: LiveData<Boolean> get() = _navigateToIndex

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val petDao = PetDatabase.getInstance(application).petDao

    init {
        _navigateToIndex.value = false
    }

    fun save(pet: Pet) {
        uiScope.launch {
            insert(pet)
        }
        _navigateToIndex.value = true
    }

    private suspend fun insert(pet: Pet) {
        return withContext(Dispatchers.IO) {
            petDao.insert(pet)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}