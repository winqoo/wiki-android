package com.winqoo.wikia.ui.wikis.details

import com.winqoo.wikia.di.modules.FragmentModule
import com.winqoo.wikia.di.scope.FragmentScope
import com.winqoo.wikia.ui.common.base.BaseFragment
import dagger.Binds
import dagger.Module

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
@Module(includes = arrayOf(FragmentModule::class))
abstract class WikiDetailsModule {

    @Binds
    @FragmentScope
    abstract fun fragment(wikiDetailsFragment: WikiDetailsFragment): BaseFragment

}