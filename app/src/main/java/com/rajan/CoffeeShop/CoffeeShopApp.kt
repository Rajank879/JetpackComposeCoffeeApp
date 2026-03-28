package com.rajan.CoffeeShop

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoffeeShopApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Places.initialize(applicationContext,"hjsjhdsa763hjdfndmmn")
    }
}
