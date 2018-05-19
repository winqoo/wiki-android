package com.winqoo.wikia

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.support.multidex.MultiDex
import com.winqoo.wikia.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class App : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
        prefs = applicationContext.getSharedPreferences(packageName, ContextWrapper.MODE_PRIVATE)

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)

    companion object {
        operator fun get(context: Context): App {
            return context.applicationContext as App
        }

        lateinit var prefs: SharedPreferences
    }

}