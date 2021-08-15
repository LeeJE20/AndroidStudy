package com.example.up.ui.home.ui.main
import retrofit2.http.Url

data class ArticleModel(
    var totalResults: Int,
    var articles: ArrayList<Data>,
    var status: String,
)
{

    data class Data(
        val title: String,
        val description: String,
        val source: Source,
        val author: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val content: String
    ){
        data class Source(
            val id: String,
            val name: String
        )
    }

}