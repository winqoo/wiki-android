package com.winqoo.wikia.ui.onboarding

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.View
import com.pixplicity.easyprefs.library.Prefs
import com.winqoo.wikia.R
import com.winqoo.wikia.data.prefs.PrefKeys
import com.winqoo.wikia.ui.common.base.BaseActivity
import com.winqoo.wikia.ui.common.base.BasePresenter
import com.winqoo.wikia.ui.main.MainActivity
import com.winqoo.wikia.ui.onboarding.slider.SlideAdapter
import kotlinx.android.synthetic.main.activity_onboarding.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class OnboardingActivity : BaseActivity(), OnboardingView {

    @Inject
    lateinit var onboardingPresenter: OnboardingPresenter

    lateinit var pagerAdapter : PagerAdapter

    override fun layoutId() = R.layout.activity_onboarding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pagerAdapter = SlideAdapter(supportFragmentManager)
        viewPagerBullet.adapter = pagerAdapter
        btnSkip.setOnClickListener({ _ ->
            Prefs.putBoolean(PrefKeys.APP_FIRST_RUN_FLAG, true)
            startActivity<MainActivity>()
            finish()
        })
        circleIndicator.setViewPager(viewPagerBullet)
        viewPagerBullet.setPageTransformer(false, { pageView, position ->
            val viewBackground: View = pageView.findViewById(R.id.ivLogo)
            viewBackground.translationX = pageView.width * -position
        })
    }

    override fun attachPresenter(): BasePresenter<*>? {
        onboardingPresenter.attachView(this)
        return onboardingPresenter
    }

}
