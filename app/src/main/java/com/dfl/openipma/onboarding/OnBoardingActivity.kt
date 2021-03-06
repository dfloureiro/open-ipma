package com.dfl.openipma.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dfl.domainanalytics.usecase.HandleOnBoardingEvents
import com.dfl.domainpersistence.usecase.HandleFirstLaunchUseCase
import com.dfl.openipma.IpmaApplication
import com.dfl.openipma.R
import com.dfl.openipma.home.HomeActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.on_boarding_activity.*

class OnBoardingActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */

    @Inject
    lateinit var handleFirstLaunchUseCase: HandleFirstLaunchUseCase
    @Inject
    lateinit var handleOnBoardingEvents: HandleOnBoardingEvents

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as IpmaApplication).injector.inject(this)

        if (handleFirstLaunchUseCase.isFirstLaunch().not()) {
            startMainActivity()
        } else {
            handleOnBoardingEvents.logOnBoardingBegin()
        }

        setContentView(R.layout.on_boarding_activity)

        val sectionsPagerAdapter = SectionsPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        container.adapter = sectionsPagerAdapter
        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {

                updateIndicators(position)

                val lastPagePosition = sectionsPagerAdapter.count - 1
                intro_button_next.visibility = when (position) {
                    lastPagePosition -> View.GONE
                    else -> View.VISIBLE
                }

                intro_button_finish.visibility = when (position) {
                    lastPagePosition -> View.VISIBLE
                    else -> View.GONE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
        })

        val goToMainActivity: View.OnClickListener = View.OnClickListener {
            handleFirstLaunchUseCase.setHasNotFirstLaunch()
            handleOnBoardingEvents.logOnBoardingComplete()
            startMainActivity()
        }

        intro_button_skip.setOnClickListener(goToMainActivity)
        intro_button_finish.setOnClickListener(goToMainActivity)
        intro_button_next.setOnClickListener { goToNextPage() }
    }

    fun goToNextPage() {
        val nextPagePosition = container.currentItem + 1
        container.currentItem = nextPagePosition
        if (nextPagePosition == NUMBER_OF_ON_BOARDING_PAGES) {
            handleFirstLaunchUseCase.setHasNotFirstLaunch()
            handleOnBoardingEvents.logOnBoardingComplete()
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    fun updateIndicators(position: Int) {
        val indicators = arrayOf(intro_indicator_0, intro_indicator_1)
        for (indicatorPosition in indicators.indices) {
            val indicator = when (indicatorPosition) {
                position -> R.drawable.indicator_selected
                else -> R.drawable.indicator_unselected
            }
            indicators[indicatorPosition].setBackgroundResource(indicator)
        }
    }

    inner class SectionsPagerAdapter(fragmentManager: FragmentManager, behavior: Int) :
        FragmentPagerAdapter(fragmentManager, behavior) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> OnBoardingWelcomeFragment.newInstance()
                1 -> OnBoardingLocationFragment.newInstance()
                else -> {
                    throw IllegalArgumentException("oOn boarding page $position is not implemented")
                }
            }
        }

        override fun getCount(): Int {
            return NUMBER_OF_ON_BOARDING_PAGES
        }
    }

    companion object {
        private const val NUMBER_OF_ON_BOARDING_PAGES = 2
    }
}
