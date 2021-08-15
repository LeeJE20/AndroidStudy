package com.example.up.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.up.R
import com.example.up.ui.community.CommunityFragment

class HomeFragment : Fragment()
{

    private lateinit var homeViewModel: HomeViewModel

    lateinit var et: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?
    {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        et = root.findViewById(R.id.home_edit)

        et.addTextChangedListener(){
            Log.d("send", "텍스트 바뀜")

            val result = et.getText().toString()
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment,CommunityFragment())
                    .commit()
        }


        val intentButton : Button= root.findViewById(R.id.intent_button)

        intentButton.setOnClickListener {
            val intent = Intent(activity, Tour::class.java)
            startActivity(intent)
        }

        return root
    }



}