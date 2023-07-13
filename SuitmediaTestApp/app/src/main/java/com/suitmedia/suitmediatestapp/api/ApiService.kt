package com.suitmedia.suitmediatestapp.api

import com.suitmedia.suitmediatestapp.response.RegresInResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // get users
    @GET("users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<RegresInResponse>

}