package br.com.petapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PetViewModel : ViewModel() {

    private val _display = MutableLiveData<String>()
    val display: LiveData<String> get() = _display

    fun displayText() {
        _display.value = "Hello With View Model"
    }

}