package com.winqoo.wikia.di.modules

import android.content.Context
import android.support.v4.app.Fragment
import com.winqoo.wikia.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
@Module
class FragmentModule{

    @Provides
    @FragmentScope
    fun provideContext(fragment: Fragment) : Context? = fragment.context

}