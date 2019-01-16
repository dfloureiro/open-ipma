package com.dfl.openipma

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
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

    //TODO inject the sharedPreferences to ensure it only opens on the first launch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.on_boarding_activity)

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
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

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })

        intro_button_skip.setOnClickListener { startMainActivity() }
        intro_button_finish.setOnClickListener { startMainActivity() }
        intro_button_next.setOnClickListener { container.currentItem = container.currentItem + 1 }
    }


    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun updateIndicators(position: Int) {
        val indicators = arrayOf(intro_indicator_0, intro_indicator_1, intro_indicator_2)
        for (i in 0 until indicators.size) {
            val indicator = when (i) {
                position -> R.drawable.indicator_selected
                else -> R.drawable.indicator_unselected
            }
            indicators[i].setBackgroundResource(indicator)
        }
    }

    inner class SectionsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        private val numberOfOnBoardingPages = 3

        override fun getItem(position: Int): Fragment {
            return OnBoardingLocationFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            return numberOfOnBoardingPages
        }
    }
}
