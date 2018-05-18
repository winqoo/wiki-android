package com.winqoo.wikia.ui.wikis

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.winqoo.wikia.R
import com.winqoo.wikia.ui.common.base.BaseFragment
import com.winqoo.wikia.ui.common.base.BasePresenter
import com.winqoo.wikia.ui.wikis.section.SectionFragment
import kotlinx.android.synthetic.main.fragment_wikis.*
import javax.inject.Inject

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class WikisFragment : BaseFragment(), WikisView {

    @Inject
    lateinit var wikisPresenter: WikisPresenter
    private val fragments: MutableList<BaseFragment> = ArrayList()
    private var position = 0
    private var refresh = false

    init {
        fragments.add(SectionFragment.newInstance(SectionFragment.EXTRA_MODE_TITLES))
        fragments.add(SectionFragment.newInstance(SectionFragment.EXTRA_MODE_IMAGES))
    }

    companion object {

        fun newInstance(): WikisFragment {
            return WikisFragment()
        }
    }

    override fun layoutId() = R.layout.fragment_wikis

    override fun attachPresenter(): BasePresenter<*>? {
        wikisPresenter.attachView(this)
        return wikisPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (savedInstanceState != null) {
            childFragmentManager.fragments.forEach {
                childFragmentManager
                        .beginTransaction()
                        .remove(it)
                        .commit()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = getString(R.string.wikis_header)
        viewPager.adapter = ViewPagerAdapter(childFragmentManager)
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    position = viewPager.currentItem
                }
            }
        })
        tabs.setupWithViewPager(viewPager)

        viewPager.currentItem = position
        if (this.refresh) {
            this.refresh = false
        }

    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val tabTitles = arrayOf(getString(R.string.wikis_titles), getString(R.string.wikis_images))

        override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

        override fun getItem(position: Int): BaseFragment? = fragments[position]

        override fun getCount(): Int = fragments.size

    }

}