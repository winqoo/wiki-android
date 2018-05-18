package com.winqoo.wikia.ui.common.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
abstract class BaseActivity: DaggerAppCompatActivity(), BaseView {

    private var presenter: BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        presenter = attachPresenter()
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }

    abstract fun attachPresenter(): BasePresenter<*>?

    @LayoutRes
    abstract fun layoutId(): Int

}