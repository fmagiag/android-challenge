package com.magiag.androidchallenge.view.activities

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.magiag.androidchallenge.R
import com.magiag.androidchallenge.data.repository.ShowsDataRepository
import com.magiag.androidchallenge.data.repository.ShowsDataStore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    lateinit var mShowsDataRepository: ShowsDataRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        setupBottomNavMenu(navController)

        mShowsDataRepository = ShowsDataStore()
        testApi()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            resources.getResourceName(destination.id)
        }
    }



    private fun testApi() {

        mShowsDataRepository.getShows(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({show ->
                    Log.e("onNext error", show.size.toString())

                }, { e ->
                    Log.e("onError error", e?.message)
                },{
                    Log.e("onComplete success", "ok")
                })
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }
}
