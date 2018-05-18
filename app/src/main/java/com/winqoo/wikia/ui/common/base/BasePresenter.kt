package com.winqoo.wikia.ui.common.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
open class BasePresenter<V : BaseView> {

    var view: V? = null
    var compositeDisposable = CompositeDisposable()

    open fun attachView(view: V) {
        this.view = view
    }

    open fun detachView() {
        compositeDisposable.clear()
        view = null
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun isViewAttached() : Boolean = view != null

}