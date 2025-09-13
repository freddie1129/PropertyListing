package com.demobnb.propertylisting

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavHost(modifier: Modifier, paddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "list",
        modifier = modifier) {
        composable("list") {
          //  SampleScreen()
            PropertyListScreen(navController, paddingValues)
        }
        composable(route = "detail/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getLong("itemId")
            requireNotNull(itemId)
            PropertyDetailScreen(itemId, paddingValues, navController)
        }
    }
}
