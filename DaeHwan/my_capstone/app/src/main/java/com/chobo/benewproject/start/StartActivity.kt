package com.chobo.benewproject.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.chobo.benewproject.R

class StartActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2

    lateinit var registerData : RegisterUserData

    private val startFragments : List<Fragment> = listOf(
        StartFragment(),
        RegisterFragment(),
        RegisterInfoFirstFragment(),
        RegisterInfoSecondFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        viewPager = findViewById(R.id.vp_start_start)

        viewPager.adapter = OnboardingPagerAdapter(this@StartActivity)

        viewPager.isUserInputEnabled = false
    }

    inner class OnboardingPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return startFragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return startFragments[position]
        }
    }

    override fun onBackPressed() {
        val viewPager = findViewById<ViewPager2>(R.id.vp_start_start)


        val currentItem = viewPager.currentItem

        if (currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.setCurrentItem(currentItem - 1, true)
        }
    }
}