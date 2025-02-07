package com.example.youtubevideolengthcalculator


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.youtubevideolengthcalculator.viewmodel.YoutubeViewModel


@Composable

fun YoutubeScreen(viewModel: YoutubeViewModel) {

    var videoId by remember {
        mutableStateOf("")
    }

    val videoDuration by viewModel.videoDuration

    val apiKey = BuildConfig.apiKey

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //App Title
        Text(
            text = "Youtube Video Length Calculator",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //Input Text Field
        OutlinedTextField(
            value = videoId,
            onValueChange = { videoId = it },
            label = { Text("Enter Youtube URL") },
            placeholder = { Text("Paste Youtube link here") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val videoIdExtracted = extractVideoId(videoId)
                if (!videoIdExtracted.isNullOrEmpty()) {
                    viewModel.fetchVideoDuration(videoIdExtracted, apiKey)
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Get Duration")
        }
        Spacer(modifier = Modifier.height(20.dp))



        if (videoDuration > 0) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Total Duration",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                   Text(
                       text = formatDuration(videoDuration),
                       fontSize = 18.sp,
                       fontWeight = FontWeight.Medium,
                   )

                }

            }
        }

    }


}

private fun extractVideoId(url: String): String? {
    val regex = Regex("(?:v=|youtu\\.be/|embed/|shorts/|watch\\?v=)([a-zA-Z0-9_-]{11})")

    val match = regex.find(url)
    return match?.groupValues?.get(1)
}

fun formatDuration(seconds: Long): String {
    val days = seconds / 86400
    val hours = (seconds % 86400) / 3600
    val minutes = (seconds % 3600) / 60
    val sec = seconds % 60

    return buildString {
        if (days > 0) append("$days days ")
        if (hours > 0) append("$hours hrs ")
        if (minutes > 0) append("$minutes mins ")
        if (sec > 0 || (days == 0L && hours == 0L && minutes == 0L)) append("$sec secs")

    }.trim()
}