package com.winqoo.wikia.ui.onboarding

import android.support.v7.app.AppCompatActivity
import com.winqoo.wikia.di.modules.ActivityModule
import com.winqoo.wikia.di.scope.ActivityScope
import com.winqoo.wikia.di.scope.FragmentScope
import com.winqoo.wikia.ui.onboarding.slider.SlideFragment
import com.winqoo.wikia.ui.onboarding.slider.SlideFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
@Module(includes = [ActivityModule::class])
abstract class OnboardingActivityModule {

    @Binds
    @ActivityScope
    abstract fun activity(activity: OnboardingActivity): AppCompatActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(SlideFragmentModule::class))
    abstract fun slideFragmentInjector(): SlideFragment

}