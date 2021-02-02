package br.com.petapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.petapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(this, findNavController())
    }

    override fun onSupportNavigateUp() = findNavController().navigateUp()

    private fun findNavController() = this.findNavController(R.id.my_nav_host_fragment)

}