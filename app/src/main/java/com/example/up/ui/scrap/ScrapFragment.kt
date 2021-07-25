package com.example.up.ui.scrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.up.R

class ScrapFragment : Fragment()
{

    private lateinit var scrapViewModel: ScrapViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?
    {
        scrapViewModel =
                ViewModelProvider(this).get(ScrapViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_scrap, container, false)
        val textView: TextView = root.findViewById(R.id.text_scrap)
        scrapViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}