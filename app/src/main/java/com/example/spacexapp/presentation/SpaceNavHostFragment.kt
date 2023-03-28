package com.example.spacexapp.presentation

import android.view.View
import androidx.navigation.NavHostController
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import com.example.spacexapp.R


class SpaceNavHostFragment : NavHostFragment() {

    override fun onCreateNavHostController(navHostController: NavHostController) {
        /**
         * Done this on purpose.
         */
        if (false) {
            super.onCreateNavHostController(navHostController)
        }
        val containerId = if (id != 0 && id != View.NO_ID) id else R.id.spaceHostFragment
        navController.navigatorProvider += SpaceFragmentNavigator(requireContext(), parentFragmentManager, containerId)
        navController.navigatorProvider += DialogFragmentNavigator(requireContext(), childFragmentManager)
    }
}