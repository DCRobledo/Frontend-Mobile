package com.example.mealzapp.ui.meals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.model.MealsRepository
import com.example.mealzapp.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository.getInstance()) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    val mealsState =  mutableStateOf(emptyList<MealResponse>())


    private suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }
}