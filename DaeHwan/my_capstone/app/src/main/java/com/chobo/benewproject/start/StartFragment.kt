package com.chobo.benewproject.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.chobo.benewproject.R

class StartFragment : Fragment() {

    lateinit var btn_next : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        btn_next = view.findViewById(R.id.btn_startFragment_next)

        btn_next.setOnClickListener {
            (requireActivity() as? StartActivity)?.viewPager?.let { viewPager ->
                val currentItem = viewPager.currentItem
                if (currentItem < viewPager.adapter?.itemCount ?: 0 - 1) {
                    viewPager.setCurrentItem(currentItem + 1, true)
                }
            }
        }

        return view
    }
}