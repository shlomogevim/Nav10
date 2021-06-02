package com.sg.nav10

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listener:NavController.OnDestinationChangedListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.fragment)
        drawerLayout = findViewById(R.id.drawer_Layout)
        navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        listener=NavController.OnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id==R.id.firstFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.design_default_color_primary_dark)))
            }else if (destination.id==R.id.secondFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.design_default_color_secondary)))

            }
        }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)

        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}

