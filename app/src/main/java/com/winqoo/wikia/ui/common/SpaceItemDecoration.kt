package com.winqoo.wikia.ui.common

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class SpaceItemDecoration(private val itemOffset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        val position = parent?.getChildViewHolder(view)?.adapterPosition ?: 0
        val itemCount = state?.itemCount ?: 0
        outRect?.left = itemOffset
        outRect?.bottom = if (position == itemCount -1) itemOffset else 0
        outRect?.right = itemOffset
        outRect?.top = itemOffset
    }

}