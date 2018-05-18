package com.winqoo.wikia.di.modules

import android.content.Context
import com.winqoo.wikia.App
import com.winqoo.wikia.di.scope.ActivityScope
import com.winqoo.wikia.ui.main.MainActivity
import com.winqoo.wikia.ui.main.MainActivityModule
import com.winqoo.wikia.ui.onboarding.OnboardingActivity
import com.winqoo.wikia.ui.onboarding.OnboardingActivityModule
import com.winqoo.wikia.ui.wikis.details.container.WikiDetailsActivity
import com.winqoo.wikia.ui.wikis.details.container.WikiDetailsActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
@Module(includes = arrayOf(AndroidSupportInjectionModule::class, NetworkModule::class))
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun app(app: App): Context

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun mainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(OnboardingActivityModule::class)])
    abstract fun onboardingActivityInjector(): OnboardingActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(WikiDetailsActivityModule::class)])
    abstract fun wikiDetailsActivityInjector(): WikiDetailsActivity

}