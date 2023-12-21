package com.thondigital.sampleapp.presentation.ui.home

import Kmule
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    Kmule.accelerometerData.observe {
        println("Accelerometer data: $it")
    }
}
