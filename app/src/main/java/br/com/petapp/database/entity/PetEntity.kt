package br.com.petapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.petapp.model.GenderEnum

@Entity(tableName = "pets")
data class PetEntity (

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    val name: String,

    val breed: String,

    @ColumnInfo(name = "gender_id")
    val gender: GenderEnum
)