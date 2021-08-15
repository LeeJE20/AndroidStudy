package com.example.up.ui.home.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.up.R


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tour, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(viewLifecycleOwner, Observer<String> {
            textView.text = it



        })

        // title에 따라 다르게 하기
        val carList = ArrayList<CarForList>()
        pageViewModel.title.observe(viewLifecycleOwner, Observer<String> {
            if (it == "Tab 1"){

                for (i in 0 until 10) {
                    carList.add(CarForList("" + i + " 번째 자동차", "" + i + "순위 엔진"))
                }
            }
            else{

                for (i in 0 until 50) {
                    carList.add(CarForList("" + i + " 번째 자동차", "" + i + "순위 엔진"))
                }
            }



        })

//        val carList = ArrayList<CarForList>()
//        for (i in 0 until 50) {
//            carList.add(CarForList("" + i + " 번째 자동차", "" + i + "순위 엔진"))
//        }

        // 리사이클러뷰 어댑터 붙이기
        val recycler_view = root.findViewById<RecyclerView>(R.id.tour_recycler_view)
        val adapter = RecyclerViewAdapter(carList, LayoutInflater.from(activity))
        recycler_view.adapter = adapter
        // 레이아웃 매니저 설정
        recycler_view.layoutManager = LinearLayoutManager(activity)



        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}



class CarForList(val name: String, val engine: String)

class RecyclerViewAdapter(
    val itemList: ArrayList<CarForList>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    // 뷰홀더 inner Class : 앞의 <>안에 뷰홀더가 있어야 함
    // 부모클래스인 RecyclerView.ViewHolder에 생성자에서 받은 itemView 넘겨줌
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carName: TextView
        val carEngine: TextView

        init {
            // itemView에서 findViewById하여 세팅
            carName = itemView.findViewById(R.id.car_name)
            carEngine = itemView.findViewById(R.id.car_engine)
        }
    }

    // 우클릭-> generate-> implement method로 오버라이드할 함수 가져오기
    // 호출 순서:
    //  onCreateViewHolder에서 뷰 만들기 -> ViewHolder 클래스에서 세팅 -> onBindViewHolder에서 글씨 넣기

    // 뷰를 그려준다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 뷰를 만든다
        val view = inflater.inflate(R.layout.item_view, parent, false)
        // 뷰홀더에 넣는다
        // 위의 뷰홀더 클래스의 init 실행됨
        return ViewHolder(view)
    }

    // 리스트뷰는 태그 달고 그 태그를 찾아써서 재활용
    // 그 역할을 해줌
    // bind: 묶어준다
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 세팅된 애들을 불러서 글씨 넣어줌
        holder.carName.setText(itemList.get(position).name)
        holder.carEngine.setText(itemList.get(position).engine)
    }

    // 아이템 사이즈
    override fun getItemCount(): Int {
        return itemList.size
    }
}