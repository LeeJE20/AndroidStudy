package com.example.up.ui.home.ui.main


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// 기본 baseUrl+top-headlines 에 http get 방식으로 우리가 만든 ArticleModel로 서버에 call 합니다.
interface Retrofit2Interface {
    // URI
    @GET("top-headlines")
    fun requestList(
        // 쿼리스트링
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String
    ) : Call<ArticleModel>
}

