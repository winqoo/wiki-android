package com.winqoo.wikia.di.modules

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.winqoo.wikia.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideContext(activity: AppCompatActivity): Context = activity

}