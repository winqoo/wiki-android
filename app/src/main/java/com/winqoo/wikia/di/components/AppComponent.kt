package com.winqoo.wikia.di.components

import com.winqoo.wikia.App
import com.winqoo.wikia.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class AppBuilder : AndroidInjector.Builder<App>()

}