package com.winqoo.wikia.ui.wikis.section

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.winqoo.wikia.R
import com.winqoo.wikia.data.models.ExpandedWikiaItem
import com.winqoo.wikia.data.models.ListItem
import com.winqoo.wikia.extension.gone
import com.winqoo.wikia.extension.inflate
import com.winqoo.wikia.extension.visible
import com.winqoo.wikia.ui.common.LoadItemAdapterDelegate
import com.winqoo.wikia.ui.wikis.section.SectionFragment.Companion.EXTRA_MODE_IMAGES
import com.winqoo.wikia.ui.wikis.section.SectionFragment.Companion.EXTRA_MODE_TITLES
import kotlinx.android.synthetic.main.item_wikia_grid.view.*
import kotlinx.android.synthetic.main.item_wikia_list.view.*
import kotlinx.android.synthetic.main.layout_progress.view.*

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class SectionAdapter(private val wikiasList: MutableList<ExpandedWikiaItem>, mode: String = EXTRA_MODE_TITLES, clickAction: (ExpandedWikiaItem) -> Unit) : ListDelegationAdapter<List<ListItem>>() {

    init {
        delegatesManager.addDelegate(SectionTitlesAdapterDelegate(mode, clickAction))
        delegatesManager.addDelegate(LoadItemAdapterDelegate(R.layout.item_list_loading))
        setItems(wikiasList)
    }

    fun addWikis(wikis: List<ExpandedWikiaItem>) {
        this.wikiasList.addAll(wikis)
        notifyItemRangeInserted(wikiasList.size - wikis.size, wikis.size)
    }

    class SectionTitlesAdapterDelegate(private val mode: String, private val clickAction: (ExpandedWikiaItem) -> Unit) : AbsListItemAdapterDelegate<ExpandedWikiaItem, ListItem, WikiaItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup): WikiaItemViewHolder {

            return when (mode) {
                EXTRA_MODE_IMAGES -> {
                    WikiaItemViewHolder(parent.inflate(R.layout.item_wikia_grid))
                }
                else -> {
                    WikiaItemViewHolder(parent.inflate(R.layout.item_wikia_list))
                }
            }

        }

        override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int): Boolean {
            return item is ExpandedWikiaItem
        }

        override fun onBindViewHolder(item: ExpandedWikiaItem, viewHolder: WikiaItemViewHolder, payloads: MutableList<Any>) {
            viewHolder.bind(item, mode)
            if (mode == EXTRA_MODE_TITLES)
                viewHolder.itemView.setOnClickListener({ clickAction.invoke(item) })
        }
    }

    class WikiaItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(expandedWikiaItem: ExpandedWikiaItem, mode: String?) {
            when (mode) {
                EXTRA_MODE_IMAGES -> {
                    val url: String? = expandedWikiaItem.image
                    if (!url.isNullOrEmpty()) {
                        itemView.progress.visible()
                        Picasso.with(itemView.context)
                                .load(url)
                                .fit()
                                .into(itemView.ivWikiaImage, object : Callback {
                                    override fun onSuccess() {
                                        itemView.progress.gone()
                                    }

                                    override fun onError() {
                                        itemView.progress.gone()
                                        itemView.ivWikiaImage.setImageResource(R.drawable.ic_photo)
                                    }
                                })
                    } else {
                        itemView.progress.gone()
                        itemView.ivWikiaImage.setImageResource(R.drawable.ic_photo)
                    }
                }
                else -> {
                    itemView.tvTitle.text = expandedWikiaItem.title
                    itemView.tvNumber.text = String.format("%d", expandedWikiaItem.stats?.articles)
                }
            }

        }
    }

}