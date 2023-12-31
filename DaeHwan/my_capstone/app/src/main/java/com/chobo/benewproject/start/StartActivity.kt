package com.chobo.benewproject.start

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.chobo.benewproject.R
import com.chobo.benewproject.register.RegisterFragment
import com.chobo.benewproject.register.RegisterInfoFirstFragment
import com.chobo.benewproject.register.RegisterInfoSecondFragment
import com.chobo.benewproject.register.RegisterViewModel

class StartActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2

    lateinit var registerViewModel : RegisterViewModel

    private val startFragments : List<Fragment> = listOf(
        StartFragment(),
        RegisterFragment(),
        RegisterInfoFirstFragment(),
        RegisterInfoSecondFragment()
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        viewPager = findViewById(R.id.vp_start_view)

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
        val viewPager = findViewById<ViewPager2>(R.id.vp_start_view)


        val currentItem = viewPager.currentItem

        if (currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.setCurrentItem(currentItem - 1, true)
        }
    }
}