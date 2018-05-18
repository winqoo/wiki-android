package com.winqoo.wikia.ui.common

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class PaginationScrollListener(var layoutManager: LinearLayoutManager, val onLoadMore: () -> Unit) : RecyclerView.OnScrollListener() {

    var isLoading: Boolean = false
    var isLastPage: Boolean = false

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                onLoadMore.invoke()
            }
        }
    }

}