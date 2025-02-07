package com.example.youtubevideolengthcalculator

import android.util.Log
import com.google.gson.Gson


class YoutubeRepository(private val apiService: YoutubeApiService) {

    suspend fun getVideoDuration(videoId: String, apiKey: String): Long {
        return try {
            Log.d("YoutubeAPI", "Fetching video duration for videoId: $videoId")
            val response = apiService.getVideoDetails(videoId = videoId, apiKey = apiKey)
            Log.d("YoutubeAPI", "Response: ${response.items[0].contentDetails.duration}")

            if (response.items.isNotEmpty()) {
                val duration = parseDuration(response.items[0].contentDetails.duration)
                Log.d("YoutubeAPI", "Duration: $duration seconds")
                duration
            } else {
                Log.e("YoutubeAPI", "No items found in response")
                0
            }

        }catch(e:Exception){
            e.printStackTrace()
            0
        }
    }
}