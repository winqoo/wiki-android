package com.winqoo.wikia.ui.onboarding.slider

import android.os.Bundle
import android.view.View
import com.winqoo.wikia.R
import com.winqoo.wikia.ui.common.base.BaseFragment
import com.winqoo.wikia.ui.common.base.BasePresenter
import kotlinx.android.synthetic.main.fragment_slide.*
import javax.inject.Inject

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class SlideFragment : BaseFragment(), SlideView {

    @Inject
    lateinit var slidePresenter: SlidePresenter

    override fun layoutId() = R.layout.fragment_slide

    override fun attachPresenter(): BasePresenter<*>? {
        slidePresenter.attachView(this)
        return slidePresenter
    }

    companion object {
        val STEP_KEY = "STEP_KEY"

        fun newInstance(position: Int): SlideFragment {
            val args = Bundle()
            args.putInt(STEP_KEY, position)
            val fragment = SlideFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val res = resources
        val position = arguments?.getInt(STEP_KEY) ?: 0
        run {

            val mainMessageSteps = res.getStringArray(R.array.main_steps_text)
            tvMain.text = mainMessageSteps.get(position)
        }

    }

}