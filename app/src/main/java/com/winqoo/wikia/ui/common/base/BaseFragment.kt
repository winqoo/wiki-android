package com.winqoo.wikia.ui.common.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
abstract class BaseFragment : DaggerFragment() {

    private var presenter: BasePresenter<*>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = attachPresenter()
    }

    override fun onDestroyView() {
        presenter?.detachView()
        super.onDestroyView()
    }

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun attachPresenter(): BasePresenter<*>?

}