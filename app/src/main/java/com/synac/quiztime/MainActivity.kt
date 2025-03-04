package com.synac.quiztime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.synac.quiztime.presentation.navigation.NavGraph
import com.synac.quiztime.presentation.theme.QuizTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            QuizTimeTheme {
                val navController = rememberNavController()
                Scaffold { paddingValue ->
                    NavGraph(
                        navController = navController,
                        paddingValues = paddingValue
                    )
                }
            }
        }
    }
}