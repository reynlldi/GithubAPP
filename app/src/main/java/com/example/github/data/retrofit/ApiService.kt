package com.example.github.data.retrofit

import com.example.github.data.response.DetailUserResponse
import com.example.github.data.response.GithubResponse
import com.example.github.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun getListUser(
        @Query("q") query: String
    ): Call<GithubResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
}