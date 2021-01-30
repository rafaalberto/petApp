package br.com.petapp.model

enum class GenderEnum (val id: Int, val description: String) {
    GENDER(0, "Gender"),
    UNKNOWN (1, "Unknown"),
    MALE (2, "Male"),
    FEMALE (3, "Female")
}