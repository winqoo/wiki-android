package com.winqoo.wikia.ui.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.winqoo.wikia.data.models.ListItem

class LoadItemAdapterDelegate : AdapterDelegate<List<ListItem>> {

    private var viewHeight: Int = 0
    private var layoutRes: Int = 0

    constructor(@LayoutRes layoutRes: Int) {
        this.layoutRes = layoutRes
    }

    constructor(@LayoutRes layoutRes: Int, viewHeight: Int) {
        this.viewHeight = viewHeight
        this.layoutRes = layoutRes
    }

    override fun isForViewType(listItems: List<ListItem>, position: Int): Boolean {
        return listItems[position] is LoadItem
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(layoutRes, viewGroup, false)
        return LoadItemViewHolder(view)
    }

    override fun onBindViewHolder(listItems: List<ListItem>, i: Int, viewHolder: RecyclerView.ViewHolder, list: List<Any>) {
        val params = viewHolder.itemView.layoutParams
        if (viewHeight > 0) {
            params.height = viewHeight
        }
        viewHolder.itemView.layoutParams = params
    }

    internal class LoadItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}
