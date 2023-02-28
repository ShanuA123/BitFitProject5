package com.example.bitfitproject5

import android.app.Application


class FoodApplication : Application() {
    val db by lazy { ItemDatabase.getInstance(this) }
}