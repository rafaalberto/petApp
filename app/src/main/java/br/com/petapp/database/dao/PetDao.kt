package br.com.petapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.petapp.database.entity.PetEntity

@Dao
interface PetDao {

    @Insert
    fun insert(pet: PetEntity)

    @Update
    fun update(pet: PetEntity)

    @Delete
    fun delete(pet: PetEntity)

    @Query("SELECT * FROM pets ORDER BY name ASC")
    fun findAll(): LiveData<List<PetEntity>>

    @Query("SELECT * FROM pets WHERE id = :id")
    fun findById(id: Long): LiveData<PetEntity>

    @Query("SELECT * FROM pets WHERE id = :id")
    fun getById(id: Long): PetEntity?
}