package br.com.petapp.database.repository

import br.com.petapp.database.dao.PetDao
import br.com.petapp.database.entity.PetEntity
import br.com.petapp.model.GenderEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PetRepository(private val petDao: PetDao) {

    fun findById(petId: Long) = petDao.findById(petId)

    fun findAll() = petDao.findAll()

    suspend fun insert(pet: PetEntity) = withContext(Dispatchers.IO) {
        petDao.insert(pet)
    }

    suspend fun update(pet: PetEntity) {
        return withContext(Dispatchers.IO) {
            petDao.update(pet)
        }
    }

    suspend fun delete(petId: Long) {
        return withContext(Dispatchers.IO) {
            val pet = petDao.getById(petId) ?: return@withContext
            petDao.delete(pet)
        }
    }

}