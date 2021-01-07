package br.com.petapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.petapp.database.entity.Pet

@Dao
interface PetDao {

    @Insert
    fun insert(pet: Pet)

    @Update
    fun update(pet: Pet)

    @Delete
    fun delete(pet: Pet)

    @Query("SELECT * FROM pets ORDER BY name ASC")
    fun findAll(): LiveData<List<Pet>>

    @Query("SELECT * FROM pets WHERE id = :id")
    fun findById(id: Long): LiveData<Pet>

    @Query("SELECT * FROM pets WHERE id = :id")
    fun getById(id: Long): Pet?
}