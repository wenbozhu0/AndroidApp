package edu.uchicago.gerber.androidretro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import edu.uchicago.gerber.androidretro.ui.theme.FavoriteBooksTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import edu.uchicago.gerber.androidretro.presentation.navigation.Navigation
import androidx.compose.animation.ExperimentalAnimationApi

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavoriteBooksTheme {
                val navController = rememberAnimatedNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}