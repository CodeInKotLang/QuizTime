package com.synac.quiztime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.synac.quiztime.presentation.navigation.NavGraph
import com.synac.quiztime.presentation.theme.QuizTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizTimeTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController
                )
            }
        }
    }
}