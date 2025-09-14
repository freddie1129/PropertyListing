package com.demobnb.propertylisting

import android.os.Build
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demobnb.propertylisting.model.PropertySummary

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
        composable(route = "detail/{itemId}/{userId}",
            arguments = listOf(navArgument("itemId") { type = NavType.LongType },
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
