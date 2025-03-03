package com.example.mealzapp.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.ui.details.MealDetailsScreen
import com.example.mealzapp.ui.details.MealDetailsViewModel
import com.example.mealzapp.ui.theme.MealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealzAppTheme {
                MealzApp()
            }
        }
    }
}

@Composable
private fun MealzApp() {
    val navController = rememberNavController()
    NavHost(navController, "meals_list") {
        composable("meals_list") {
            MealsCategoriesScreen() { navigationMealId ->
                navController.navigate("meal_details/$navigationMealId")
            }
        }
        composable(
            route = "meal_details/{meal_id}",
            arguments = listOf(navArgument("meal_id") {
                type = NavType.StringType
            })
        ) {
            val viewModel: MealDetailsViewModel = viewModel()
            MealDetailsScreen(viewModel.mealState.value)
        }
    }
}