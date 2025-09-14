package com.demobnb.propertylisting.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demobnb.propertylisting.ui.screens.PropertyDetailScreen
import com.demobnb.propertylisting.ui.screens.PropertyListScreen

/**
 * Defines the navigation graph for the application.
 */
@Composable
fun AppNavHost(modifier: Modifier, paddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list",
        modifier = modifier
    ) {
        composable("list") {
            PropertyListScreen(navController, paddingValues)
        }
        composable(
            route = "detail/{itemId}/{userId}",
            arguments = listOf(
                navArgument("itemId") { type = NavType.LongType },
                navArgument("userId") { type = NavType.LongType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getLong("itemId")
            val userId = backStackEntry.arguments?.getLong("userId")
            requireNotNull(itemId)
            requireNotNull(userId)
            PropertyDetailScreen(paddingValues, navController)
        }
    }
}
