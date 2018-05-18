package com.winqoo.wikia.ui.wikis.details.container

import android.os.Bundle
import com.winqoo.wikia.R
import com.winqoo.wikia.data.models.ExpandedWikiaItem
import com.winqoo.wikia.ui.common.base.BaseActivity
import com.winqoo.wikia.ui.common.base.BasePresenter
import com.winqoo.wikia.ui.wikis.details.WikiDetailsFragment
import com.winqoo.wikia.ui.wikis.details.WikiDetailsFragment.Companion.EXTRA_WIKI_ITEM
import javax.inject.Inject

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class WikiDetailsActivity : BaseActivity(), WikiDetailsContainerView{

    @Inject
    lateinit var presenter: WikiDetailsContainerPresenter

    override fun layoutId() = R.layout.activity_wiki_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra(EXTRA_WIKI_ITEM)) {
            val wikiItem = intent.getSerializableExtra(EXTRA_WIKI_ITEM) as ExpandedWikiaItem
            supportFragmentManager.beginTransaction().replace(R.id.flContainer, WikiDetailsFragment.newInstance(wikiItem)).commit()
        } else {
            onBackPressed()
            finish()
        }
    }

    override fun attachPresenter(): BasePresenter<*>? {
        presenter.attachView(this)
        return presenter
    }

}