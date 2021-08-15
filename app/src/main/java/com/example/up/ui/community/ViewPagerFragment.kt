package com.example.up.ui.community

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.up.R
import com.example.up.ui.communityclass.PagerFragmentStateAdapter

class ViewPagerFragment : Fragment() {

//    lateinit var viewPager: ViewPager2
    private var viewPager: ViewPager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_community_view_pager, container, false)

        viewPager = view.findViewById(R.id.fragmentCommunityViewPager)

        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = PagerFragmentStateAdapter(requireActivity())
        // 3개의 Fragment Add
        pagerAdapter.addFragment(BestFragment())
        pagerAdapter.addFragment(AllFragment())
//        pagerAdapter.addFragment(ThirdFragment())

        // Adapter
        viewPager!!.adapter = pagerAdapter

        viewPager!!.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })
    }

}
