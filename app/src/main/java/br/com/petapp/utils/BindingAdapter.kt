package br.com.petapp.utils

import android.view.View
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import br.com.petapp.R
import br.com.petapp.database.entity.PetEntity
import br.com.petapp.model.GenderEnum
import br.com.petapp.ui.adapter.GenderAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["genders", "selectedGender"])
fun Spinner.genders(genders: List<Any?>, selectedGender: GenderEnum?) {
    adapter = GenderAdapter(context, R.layout.spinner_gender, genders as List<String>).also {
        arrayAdapter -> arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
    }
    if (selectedGender != null) this.setCurrentSelection(selectedGender)
}

@BindingAdapter("goneIfNotNull")
fun View.goneIfNotNull(pets: List<PetEntity>?) {
    visibility = if(pets != null && pets.isNotEmpty()) View.GONE else View.VISIBLE
}

private fun Spinner.setCurrentSelection(selectedGender: GenderEnum) {
    for (index in 0 until adapter.count) {
        if (getItemAtPosition(index) == selectedGender.description) {
            setSelection(index)
            break
        }
    }
}