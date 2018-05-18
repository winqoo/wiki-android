package com.winqoo.wikia

import android.content.Context
import android.content.ContextWrapper
import android.support.multidex.MultiDex
import com.pixplicity.easyprefs.library.Prefs
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
        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()
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
    }

}