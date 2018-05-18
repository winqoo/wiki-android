package com.winqoo.wikia.ui.wikis.details

import android.os.Bundle
import android.view.View
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.winqoo.wikia.R
import com.winqoo.wikia.data.models.ExpandedWikiaItem
import com.winqoo.wikia.ui.common.base.BaseFragment
import com.winqoo.wikia.ui.common.base.BasePresenter
import kotlinx.android.synthetic.main.fragment_wiki_details.*
import javax.inject.Inject

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class WikiDetailsFragment : BaseFragment(), WikiDetailsView {

    @Inject
    lateinit var detailsPresenter: WikiDetailsPresenter

    var wikiItem: ExpandedWikiaItem? = null

    override fun layoutId() = R.layout.fragment_wiki_details

    companion object {
        val EXTRA_WIKI_ITEM = "extra_wiki_item"

        fun newInstance(wikiItem: ExpandedWikiaItem): WikiDetailsFragment {
            val fragment = WikiDetailsFragment()
            fragment.arguments = Bundle()
            fragment.arguments?.putSerializable(EXTRA_WIKI_ITEM, wikiItem)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (arguments?.containsKey(EXTRA_WIKI_ITEM) == true) {
            wikiItem = arguments?.getSerializable(EXTRA_WIKI_ITEM) as ExpandedWikiaItem
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        toolbar.title = wikiItem?.title

        bindWikiaItem()
    }

    private fun bindWikiaItem() {
        tvDesc.text = wikiItem?.desc ?: ""

        val url: String? = wikiItem?.image?: ""
        if (!url.isNullOrEmpty()) {
            Picasso.with(context)
                    .load(url)
                    .fit()
                    .into(ivImage, object : Callback {
                        override fun onSuccess() {
                        }
                        override fun onError() {
                            ivImage.setImageResource(R.drawable.ic_photo)
                        }
                    })
        }else{
            ivImage.setImageResource(R.drawable.ic_photo)

        }
    }

    override fun attachPresenter(): BasePresenter<*>? {
        detailsPresenter.attachView(this)
        return detailsPresenter
    }

}