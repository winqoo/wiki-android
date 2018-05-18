package com.winqoo.wikia.ui.common.base

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
interface ErrorView {

    fun showError(throwable: Throwable)

    fun hideError()

}