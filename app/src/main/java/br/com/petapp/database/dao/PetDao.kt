package br.com.petapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.petapp.database.entity.Pet

@Dao
interface PetDao {

    @Insert
    fun insert(pet: Pet)

}