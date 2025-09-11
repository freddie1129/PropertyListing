package com.example.propertylisting

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "list",
        modifier = modifier) {
        composable("list") {
          //  SampleScreen()
            PropertyListScreen(navController)
        }
        composable("detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            PropertyDetailScreen(itemId)
        }
    }
}
