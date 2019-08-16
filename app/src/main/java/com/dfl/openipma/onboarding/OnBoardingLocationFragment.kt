package com.dfl.openipma.onboarding

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dfl.openipma.R
import com.dfl.openipma.base.BaseFragment
import kotlinx.android.synthetic.main.on_boarding_location_fragment.*

class OnBoardingLocationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.on_boarding_location_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        on_boarding_allow_location_button.setOnClickListener { requestLocationPermission() }
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            ACCESS_FINE_LOCATION_ID
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ACCESS_FINE_LOCATION_ID && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            on_boarding_allow_location_button.text = getString(R.string.on_boarding_location_button_disabled)
            on_boarding_allow_location_button.isEnabled = false
            (activity as OnBoardingActivity).goToNextPage()
        }
    }

    companion object {

        private const val ACCESS_FINE_LOCATION_ID = 1

        fun newInstance(): OnBoardingLocationFragment {
            return OnBoardingLocationFragment()
        }
    }
}