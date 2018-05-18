package com.winqoo.wikia.ui.wikis.section

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.winqoo.wikia.R
import com.winqoo.wikia.data.models.ExpandedWikiaItem
import com.winqoo.wikia.extension.gone
import com.winqoo.wikia.extension.visible
import com.winqoo.wikia.ui.common.PaginationScrollListener
import com.winqoo.wikia.ui.common.base.BaseFragment
import com.winqoo.wikia.ui.common.base.BasePresenter
import com.winqoo.wikia.ui.wikis.details.WikiDetailsFragment
import com.winqoo.wikia.ui.wikis.details.container.WikiDetailsActivity
import kotlinx.android.synthetic.main.fragment_section.*
import kotlinx.android.synthetic.main.layout_progress.*
import org.jetbrains.anko.support.v4.startActivity
import java.io.Serializable
import javax.inject.Inject

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class SectionFragment : BaseFragment(), SectionView {

    @Inject
    lateinit var sectionPresenter: SectionPresenter
    private var sectionAdapter: SectionAdapter? = null
    private lateinit var paginationScrollListener: PaginationScrollListener
    private var mode = EXTRA_MODE_TITLES

    override fun layoutId() = R.layout.fragment_section

    companion object {
        val EXTRA_MODE = "mode"
        val EXTRA_MODE_TITLES = "mode_titles"
        val EXTRA_MODE_IMAGES = "mode_images"

        fun newInstance(mode: String?): SectionFragment {
            val fragment = SectionFragment()
            fragment.arguments = Bundle()
            fragment.arguments?.putString(EXTRA_MODE, mode)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if (arguments?.containsKey(EXTRA_MODE) == true) {
            mode = arguments?.getString(EXTRA_MODE) ?: EXTRA_MODE_TITLES
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager =
                when (mode) {
                    EXTRA_MODE_IMAGES -> {
                        GridLayoutManager(context, 2)
                    }
                    else -> {
                        LinearLayoutManager(context)
                    }
                }

        rvWikis.layoutManager = layoutManager

        errorView.setAction { fetchWikis() }

        if (sectionAdapter == null) {
            sectionAdapter = SectionAdapter(mutableListOf(), mode, {
                it.let {
                    openWikiDetails(it)
                }
            })
            paginationScrollListener = PaginationScrollListener(layoutManager, {
                fetchWikis()
            })
        } else {
            paginationScrollListener.layoutManager = layoutManager
        }

        rvWikis.adapter = sectionAdapter
        setDecoration()
        rvWikis.addOnScrollListener(paginationScrollListener)
        if (sectionAdapter?.itemCount == 0) {
            fetchWikis()
        }
    }


    override fun attachPresenter(): BasePresenter<*>? {
        sectionPresenter.attachView(this)
        return sectionPresenter
    }

    private fun fetchWikis() {
        sectionPresenter.fetchWikis()
    }

    private fun setDecoration() {
        when (mode) {
            EXTRA_MODE_IMAGES -> {
//                rv_wikis.addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.default_margin)))
            }
            else -> {
                val verticalDecoration = DividerItemDecoration(rvWikis.context, DividerItemDecoration.VERTICAL)
                val verticalDivider = ContextCompat.getDrawable(requireContext(), R.drawable.line_divider)
                verticalDivider?.let { verticalDecoration.setDrawable(it) }
                rvWikis.addItemDecoration(verticalDecoration)
            }
        }
    }

    private fun openWikiDetails(wikiItem: ExpandedWikiaItem) {
        startActivity<WikiDetailsActivity>(WikiDetailsFragment.EXTRA_WIKI_ITEM to wikiItem as Serializable)
    }

    override fun showProgress() {
        progress.visible()
    }

    override fun hideProgress() {
        progress.gone()
    }

    override fun showContent() {
        rvWikis.visible()
    }

    override fun hideContent() {
        rvWikis.gone()
    }

    override fun showEmptyContent() {
        emptyView.visible()
    }

    override fun hideEmptyContent() {
        emptyView.gone()
    }

    override fun showError(throwable: Throwable) {

        errorView.bindThrowable(throwable)
        errorView.visible()
    }

    override fun hideError() {
        errorView.gone()
    }

    override fun addWikis(wikis: List<ExpandedWikiaItem>) {
        sectionAdapter?.addWikis(wikis)
    }

    override fun setPaginationLoading(isLoading: Boolean) {
        paginationScrollListener.isLoading = isLoading
    }

    override fun setPaginationEnd(isEnded: Boolean) {
        paginationScrollListener.isLastPage = isEnded
    }


}