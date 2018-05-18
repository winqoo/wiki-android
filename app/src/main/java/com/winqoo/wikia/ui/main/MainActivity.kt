package com.winqoo.wikia.ui.main

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.winqoo.wikia.R
import com.winqoo.wikia.ui.common.base.BaseActivity
import com.winqoo.wikia.ui.common.base.BaseFragment
import com.winqoo.wikia.ui.common.base.BasePresenter
import com.winqoo.wikia.ui.wikis.WikisFragment
import javax.inject.Inject


/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun layoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(WikisFragment.newInstance())
    }

    override fun attachPresenter(): BasePresenter<*>? {
        mainPresenter.attachView(this)
        return mainPresenter
    }

     fun replaceFragment(fragment: BaseFragment) {
        replaceFragment(fragment, false)
    }

     fun replaceFragment(fragment: BaseFragment, addToStack: Boolean) {
        try {
            val fragmentTransaction = supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainContainer, fragment)

            if (addToStack) {
                fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
            }
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentTransaction.commitAllowingStateLoss()
        } catch (ex: Exception) {
        }
    }

    fun clearAndReplace(fragment: BaseFragment) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        replaceFragment(fragment, false)
    }
}
