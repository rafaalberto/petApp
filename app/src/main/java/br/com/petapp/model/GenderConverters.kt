package br.com.petapp.model

import androidx.room.TypeConverter

class GenderConverters {

    @TypeConverter
    fun toGender(value: Int) = enumValues<GenderEnum>()[value]

    @TypeConverter
    fun fromGender(value: GenderEnum) = value.id

}