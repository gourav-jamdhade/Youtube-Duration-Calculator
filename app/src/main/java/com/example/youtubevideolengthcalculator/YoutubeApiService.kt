package com.example.youtubevideolengthcalculator

import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {

    @GET("videos")
    suspend fun getVideoDetails(@Query("part") part: String = "ContentDetails", @Query("id") videoId:String, @Query("key") apiKey:String):YoutubeResponse
}