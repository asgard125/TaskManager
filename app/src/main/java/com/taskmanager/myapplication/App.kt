package com.taskmanager.myapplication

import android.app.Application
import com.taskmanager.myapplication.di.Dependencies

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Dependencies.init(applicationContext)
    }
}