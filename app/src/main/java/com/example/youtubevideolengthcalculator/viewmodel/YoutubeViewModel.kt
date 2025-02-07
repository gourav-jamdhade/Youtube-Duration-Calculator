package com.example.youtubevideolengthcalculator.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubevideolengthcalculator.YoutubeRepository
import kotlinx.coroutines.launch

class YoutubeViewModel(private val repository: YoutubeRepository) : ViewModel() {

    private val _videoDuration = mutableLongStateOf(0L)

    val videoDuration: State<Long> = _videoDuration

    fun fetchVideoDuration(videoId: String, apiKey: String) {
        viewModelScope.launch {
            Log.d("YoutubeAPI", "Fetching video duration for videoId: $videoId")
            _videoDuration.longValue = repository.getVideoDuration(videoId, apiKey)
            Log.d("YoutubeAPI", "Video duration fetched: ${_videoDuration.value} seconds")
        }

    }

}