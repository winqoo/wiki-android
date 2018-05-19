package com.winqoo.wikia.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.winqoo.wikia.App
import com.winqoo.wikia.data.prefs.PrefKeys
import com.winqoo.wikia.ui.main.MainActivity
import com.winqoo.wikia.ui.onboarding.OnboardingActivity
import org.jetbrains.anko.startActivity

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!App.prefs.contains(PrefKeys.APP_FIRST_RUN_FLAG)) {
            startActivity<OnboardingActivity>()
        } else {
            startActivity<MainActivity>()
        }
        finish()
    }

}
