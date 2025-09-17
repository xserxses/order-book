package com.github.xserxses.orderbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.xserxses.orderbook.persistance.getDatabaseBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            App(getDatabaseBuilder(this))
        }
    }
}

// @PreviewLightDark
// @Composable
// private fun AppAndroidPreview() {
//    App(Room.da)
// }
