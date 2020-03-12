package com.example.jetpacksubmission.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpacksubmission.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainSectionsPagerAdapter = MainSectionsPagerAdapter(
            applicationContext,supportFragmentManager
        )

        activitymain_viewpager.adapter = mainSectionsPagerAdapter
        activitymain_tabs.setupWithViewPager(activitymain_viewpager)

        supportActionBar?.elevation = 0f
    }
}
