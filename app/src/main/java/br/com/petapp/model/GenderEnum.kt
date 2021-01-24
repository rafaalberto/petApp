package br.com.petapp.model

enum class GenderEnum(val id: Long, val description: String) {
    UNKNOWN(1, "Unknown"),
    MALE(2, "Male"),
    FEMALE(3, "Female")
}