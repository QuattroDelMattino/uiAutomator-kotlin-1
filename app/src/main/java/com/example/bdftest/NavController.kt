package com.example.bdftest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "FirstPage") {
        composable("FirstPage") {
            FirstPage(onNavigateToSecondPage = { name: String ->
                navController.navigate(
                    "SecondPage/$name"
                )

            })
        }
        composable("SecondPage/{name}") { navBackStackEntry ->
            val userName = navBackStackEntry.arguments?.getString("name")
            SecondPage(userName ?: "") }
    }
}