package com.winqoo.wikia.ui.wikis.section

import com.winqoo.wikia.data.models.ExpandedWikiaItem
import com.winqoo.wikia.ui.common.base.ErrorView
import com.winqoo.wikia.ui.common.base.PaginationView
import com.winqoo.wikia.ui.common.base.ProgressView

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
interface SectionView : ProgressView, ErrorView, PaginationView {

    fun addWikis(wikis: List<ExpandedWikiaItem>)

    fun showContent()

    fun hideContent()

    fun showEmptyContent()

    fun hideEmptyContent()
}