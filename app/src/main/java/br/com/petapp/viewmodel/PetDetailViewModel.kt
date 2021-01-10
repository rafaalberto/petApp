package br.com.petapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.petapp.R
import br.com.petapp.database.PetDatabase
import br.com.petapp.database.entity.PetEntity
import br.com.petapp.database.repository.PetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PetDetailViewModel(application: Application, private val petId: Long) :
    AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val petRepository = PetRepository(PetDatabase.getInstance(application).petDao)

    val pet = MediatorLiveData<PetEntity>()

    private val _navigateToIndex = MutableLiveData<Boolean>()
    val navigateToIndex: LiveData<Boolean> get() = _navigateToIndex

    private val _showSnackBar = MutableLiveData<String?>()
    val showSnackBar: LiveData<String?> get() = _showSnackBar

    init {
        pet.addSource(petRepository.findById(petId), pet::setValue)
    }

    fun save(pet: PetEntity) {
        if(isValid(pet)) {
            uiScope.launch {
                if (petId == 0L) {
                    petRepository.insert(pet)
                } else {
                    pet.id = petId
                    petRepository.update(pet)
                }
            }
            _navigateToIndex.value = true
            _showSnackBar.value = getApplication<Application>().resources.getString(R.string.pet_saved_successfully)
        }
    }

    private fun isValid(pet: PetEntity) : Boolean {
        if(pet.name == "") {
            _showSnackBar.value = getApplication<Application>().resources.getString(R.string.name_must_be_typed)
            return false
        }
        if(pet.breed == "") {
            _showSnackBar.value = getApplication<Application>().resources.getString(R.string.breed_must_be_typed)
            return false
        }
        return true
    }

    fun delete() {
        uiScope.launch {
            petRepository.delete(petId)
        }
        _navigateToIndex.value = true
        _showSnackBar.value = getApplication<Application>().resources.getString(R.string.pet_deleted_successfully)
    }

    fun doneNavigatingToIndex() {
        _navigateToIndex.value = false
    }

    fun doneShowingSnackBar() {
        _showSnackBar.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}