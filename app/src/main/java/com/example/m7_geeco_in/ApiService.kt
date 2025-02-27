package com.example.m7_geeco_in

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("incomes")
    fun createUsuari(
        @Field("title") title: String?,
        @Field("description") description: String?,
        @Field("amount") amount: Int?,
        @Field("date") date: String?
    ): Call<Ingressos>
}