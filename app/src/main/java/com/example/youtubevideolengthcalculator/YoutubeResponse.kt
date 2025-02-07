package com.example.youtubevideolengthcalculator

import android.util.Log

data class YoutubeResponse(
    val items: List<VideoItem>
)

data class VideoItem(
    val contentDetails: ContentDetails
)

data class ContentDetails(
    val duration: String
)

fun parseDuration(duration: String): Long {
    Log.d("Duration", duration)
    val pattern = "P(\\d+D)?T(\\d+H)?(\\d+M)?(\\d+S)?".toRegex()

    Log.d("Pattern", "$pattern")

    val match = pattern.matchEntire(duration) ?: return 0

    Log.d("Match", "$match")
    val days = match.groups[1]?.value?.dropLast(1)?.toIntOrNull() ?: 0

    val hours = match.groups[2]?.value?.dropLast(1)?.toIntOrNull() ?: 0

    val minutes = match.groups[3]?.value?.dropLast(1)?.toIntOrNull() ?: 0

    val seconds = match.groups[4]?.value?.dropLast(1)?.toIntOrNull() ?: 0

    val totalSeconds = days * 86400L + hours * 3600L + minutes * 60L + seconds

    return totalSeconds
}