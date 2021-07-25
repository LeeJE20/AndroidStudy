package com.example.up.ui.community

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.up.R


class CommunityFragment : Fragment()
{

    private lateinit var communityViewModel: CommunityViewModel
    lateinit var getEt: TextView
    var defaultText: String = "불러온 것이 없습니다"


    override fun onCreate(savedInstanceState: Bundle?)
    {
        Log.d("save", "불러옵니다")
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            Log.d("save", "불러오기 진행중")
            defaultText = savedInstanceState.getString("KEY_DATA").toString()
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?
    {
        if (savedInstanceState != null) {

            defaultText = savedInstanceState.getString("KEY_DATA").toString()
        }

        communityViewModel =
                ViewModelProvider(this).get(CommunityViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_community, container, false)
        val textView: TextView = root.findViewById(R.id.text_community)
        communityViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val textView2: TextView = root.findViewById(R.id.text_community2)
        communityViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        getEt = root.findViewById(R.id.community_getText)
        getEt.setText(defaultText)
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            // Do something with the result
            Log.d("send", "받은 메시지: " + result)
            getEt.setText(result)


            val bundle = Bundle()
            onSaveInstanceState(bundle)

        }


        return root
    }


    override fun onSaveInstanceState(outState: Bundle)
    {

        super.onSaveInstanceState(outState)
        val data: String = getEt.getText().toString()
        Log.d("save", "저장합니다: "+ data)
        outState.putString("KEY_DATA", data)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy()
    {
        Log.d("save", "디스트로이")
        var bundle = Bundle()
        onSaveInstanceState(bundle)
        super.onDestroy()
    }
}