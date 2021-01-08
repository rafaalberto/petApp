package br.com.petapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class PetEntity (

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    val name: String,

    val breed: String
)