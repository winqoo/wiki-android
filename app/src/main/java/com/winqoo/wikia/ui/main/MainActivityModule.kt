package com.winqoo.wikia.ui.main

import android.support.v7.app.AppCompatActivity
import com.winqoo.wikia.di.modules.ActivityModule
import com.winqoo.wikia.di.scope.ActivityScope
import com.winqoo.wikia.di.scope.FragmentScope
import com.winqoo.wikia.ui.wikis.WikisFragment
import com.winqoo.wikia.ui.wikis.WikisFragmentModule
import com.winqoo.wikia.ui.wikis.section.SectionFragment
import com.winqoo.wikia.ui.wikis.section.SectionFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
@Module(includes = [ActivityModule::class])
abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract fun activity(activity: MainActivity): AppCompatActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(WikisFragmentModule::class))
    abstract fun wikisFragmentInjector(): WikisFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(SectionFragmentModule::class))
    abstract fun sectionTitlesFragmentInjector(): SectionFragment

}