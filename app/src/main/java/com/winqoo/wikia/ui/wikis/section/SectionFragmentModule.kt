package com.winqoo.wikia.ui.wikis.section

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
abstract class SectionFragmentModule {

    @Binds
    @FragmentScope
    abstract fun fragment(sectionFragment: SectionFragment): BaseFragment

}