package com.winqoo.wikia.ui.wikis.details.container

import android.support.v7.app.AppCompatActivity
import com.winqoo.wikia.di.modules.ActivityModule
import com.winqoo.wikia.di.scope.ActivityScope
import com.winqoo.wikia.di.scope.FragmentScope
import com.winqoo.wikia.ui.wikis.details.WikiDetailsFragment
import com.winqoo.wikia.ui.wikis.details.WikiDetailsModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
@Module(includes = [ActivityModule::class])
abstract class WikiDetailsActivityModule {

    @Binds
    @ActivityScope
    abstract fun activity(activity: WikiDetailsActivity): AppCompatActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(WikiDetailsModule::class))
    abstract fun wikiDetailsFragmentInjector(): WikiDetailsFragment

}