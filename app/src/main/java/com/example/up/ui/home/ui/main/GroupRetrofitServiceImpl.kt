package com.example.up.ui.home.ui.main

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GroupRetrofitServiceImpl {
    private const val BASE_URL = "https://newsapi.org/v2/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
            //JSON 형식을 Data Class 형식으로 자동변환
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //인증탭 - 메인
    val service_ct_tab: Retrofit2Interface = retrofit.create(Retrofit2Interface::class.java)
}
