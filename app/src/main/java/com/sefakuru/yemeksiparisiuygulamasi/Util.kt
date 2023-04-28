package com.sefakuru.yemeksiparisiuygulamasi

import android.app.Activity
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

class Util {

    companion object{
        fun bottomNavigationVisible(activity: Activity) {
            val bottomNavigationView = activity.findViewById(R.id.bottomNav) as BottomNavigationView
            bottomNavigationView.visibility = View.VISIBLE
        }

        fun bottomNavigationGone(activity: Activity) {
            val bottomNavigationView = activity.findViewById(R.id.bottomNav) as BottomNavigationView
            bottomNavigationView.visibility = View.GONE
        }
    }
}