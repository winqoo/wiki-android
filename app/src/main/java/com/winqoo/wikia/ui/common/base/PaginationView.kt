package com.winqoo.wikia.ui.common.base

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
interface PaginationView : BaseView {

    fun setPaginationLoading(isLoading: Boolean)

    fun setPaginationEnd(isEnded: Boolean)

}
