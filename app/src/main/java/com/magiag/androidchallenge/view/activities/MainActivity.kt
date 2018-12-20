package com.magiag.androidchallenge.view.activities

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.databinding.ActMainBinding
import com.magiag.androidchallenge.view.base.BaseActivity

class MainActivity : BaseActivity<ActMainBinding>() {
    lateinit var bind: ActMainBinding

    override fun getContentLayoutId(): Int {
        return R.layout.act_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        bind = binding()

        setupBottomNavMenu(navController)
        setSupportActionBar(bind.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            resources.getResourceName(destination.id)
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bind.bottomNavView?.setupWithNavController(navController)
    }
}
