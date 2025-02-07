package com.example.youtubevideolengthcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.youtubevideolengthcalculator.ui.theme.YoutubeVideoLengthCalculatorTheme
import com.example.youtubevideolengthcalculator.viewmodel.YoutubeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = YoutubeRepository(RetrofitClient.api)
        val viewModel = YoutubeViewModel(repository)
        setContent {

            YoutubeScreen(viewModel)
        }
    }
}

