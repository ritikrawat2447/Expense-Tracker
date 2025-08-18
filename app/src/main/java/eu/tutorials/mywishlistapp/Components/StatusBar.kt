package eu.tutorials.mywishlistapp.Components

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun statusBarColor() {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = MaterialTheme.colorScheme.primary

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = false // set true if you want dark icons on light background
        )
    }
}

@Composable
fun FullscreenContent() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.isStatusBarVisible = false
    }
}
