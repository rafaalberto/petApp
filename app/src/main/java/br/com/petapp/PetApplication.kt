package br.com.petapp

import android.app.Application
import android.content.res.Resources

class PetApplication : Application() {

    companion object {
        lateinit var instance: PetApplication
        lateinit var resource: Resources
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        resource = resources
    }

}