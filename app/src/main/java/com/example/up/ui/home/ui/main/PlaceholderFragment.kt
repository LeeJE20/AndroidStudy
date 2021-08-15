package com.example.up.ui.home.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.up.R
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    var data: ArticleModel? = null
    private lateinit var recycler_view: RecyclerView

    // 서버에서 데이터 요청하는 함수
    // category: 뉴스 카테고리
        // https://newsapi.org/docs/endpoints/top-headlines 에서 Request parameters 참고
    private fun load(category: String) {
        Log.d("load_data", "통신 시작")
        //Callback 등록하여 통신 요청
        // 쿼리스트링에 들어갈 것 작성
        val call: Call<ArticleModel> =
            GroupRetrofitServiceImpl.service_ct_tab.requestList(
                category = category,
                country = "kr",
                apiKey = "1da533ef761b4d4d98b3cbb5c51f0c27"
            )

        call.enqueue(object : Callback<ArticleModel> {
            override fun onFailure(call: Call<ArticleModel>, t: Throwable) {
                // 통신 실패 로직
                Log.d("load_data", "통신 실패")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ArticleModel>,
                response: Response<ArticleModel>
            ) {
                Log.d("load_data", "통신 성공")
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let { it ->
                        // do something
                        data = response.body()

//                        Log.d("load_data", data.toString())

                        //리사이클러뷰 adapter에 Member 데이터 넣기
                        Log.d("RecyclerView", "리사이클러뷰 adapter 세팅")
                        setAdapter(it.articles)

                    } ?: showError(response.errorBody())
            }
        })

    }

    private fun showError(error: ResponseBody?) {
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(context, ob.getString("message"), Toast.LENGTH_SHORT).show()
    }

    // 리사이클러뷰에 어댑터 붙이기
    private fun setAdapter(articleList: List<ArticleModel.Data>) {
        Log.d("RecyclerView", "어댑터 붙이기")
        val mAdapter = RecyclerViewAdapter(articleList, context)
        recycler_view.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
        recycler_view.setHasFixedSize(true)
    }



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
//        val textView: TextView = root.findViewById(R.id.section_label)
//        pageViewModel.text.observe(viewLifecycleOwner, Observer<String> {
//            textView.text = it
//
//
//        })



        // 리사이클러뷰 세팅
        recycler_view = root.findViewById<RecyclerView>(R.id.tour_recycler_view)
        recycler_view.adapter = emptyAdapter()

        // 레이아웃 매니저 설정
        recycler_view.layoutManager = LinearLayoutManager(activity)


        // title에 따라 다르게 하기
        pageViewModel.title.observe(viewLifecycleOwner, Observer<String> {
            if (it == "과학") {
                Log.d("E/RecyclerView", "로드")
                load("science")
                Log.d("E/RecyclerView", "로드 끝")

            } else {
                // Tab 2 (스포츠)
                load("sports")
            }
        })

//        val carList = ArrayList<CarForList>()
//        for (i in 0 until 50) {
//            carList.add(CarForList("" + i + " 번째 자동차", "" + i + "순위 엔진"))
//        }

        // 리사이클러뷰 어댑터 붙이기
//        val recycler_view = root.findViewById<RecyclerView>(R.id.tour_recycler_view)
//        val adapter = RecyclerViewAdapter(carList, LayoutInflater.from(activity))
//        recycler_view.adapter = adapter
        // 레이아웃 매니저 설정
//        recycler_view.layoutManager = LinearLayoutManager(activity)

        Log.d("E/RecyclerView", "리턴")
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


class emptyAdapter(


) : RecyclerView.Adapter<emptyAdapter.ViewHolder>() {
    // 뷰홀더 inner Class : 앞의 <>안에 뷰홀더가 있어야 함
    // 부모클래스인 RecyclerView.ViewHolder에 생성자에서 받은 itemView 넘겨줌
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return 0
    }
}

class RecyclerViewAdapter(
    val itemList: List<ArticleModel.Data>,
    val context: Context?
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    // 뷰홀더 inner Class : 앞의 <>안에 뷰홀더가 있어야 함
    // 부모클래스인 RecyclerView.ViewHolder에 생성자에서 받은 itemView 넘겨줌
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val description: TextView

        init {
            // itemView에서 findViewById하여 세팅
//            Log.d("E/RecyclerView", "findViewById하여 세팅")
            title = itemView.findViewById(R.id.article_title)
            description = itemView.findViewById(R.id.article_description)
        }
    }

    // 우클릭-> generate-> implement method로 오버라이드할 함수 가져오기
    // 호출 순서:
    //  onCreateViewHolder에서 뷰 만들기 -> ViewHolder 클래스에서 세팅 -> onBindViewHolder에서 글씨 넣기

    // 뷰를 그려준다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        Log.d("E/RecyclerView", "LayoutInflater")
        val inflater = LayoutInflater.from(parent.context)
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
//        Log.d("E/RecyclerView", "글씨 세팅 ")
        holder.title.setText(itemList.get(position).title)
        holder.description.setText(itemList.get(position).description)
    }

    // 아이템 사이즈
    override fun getItemCount(): Int {
        return itemList.size
    }
}