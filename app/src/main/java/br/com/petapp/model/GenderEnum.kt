package br.com.petapp.model

import br.com.petapp.PetApplication.Companion.resource
import br.com.petapp.R

enum class GenderEnum(val id: Int, val description: String) {
    GENDER(0, resource.getString(R.string.gender_label)),
    UNKNOWN (1, resource.getString(R.string.unknown_option)),
    MALE (2, resource.getString(R.string.male_option)),
    FEMALE (3, resource.getString(R.string.female_option))
}

