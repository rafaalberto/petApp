package br.com.petapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.petapp.database.dao.PetDao
import br.com.petapp.database.entity.PetEntity
import br.com.petapp.model.GenderConverters

@Database(entities = [PetEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenderConverters::class)
abstract class PetDatabase : RoomDatabase() {

    abstract val petDao: PetDao

    companion object {

        @Volatile
        private var INSTANCE: PetDatabase? = null

        fun getInstance(context: Context): PetDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PetDatabase::class.java,
                        "pet_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}