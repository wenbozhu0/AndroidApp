package edu.uchicago.gerber.androidretro.presentation.screens.favorites

import androidx.paging.PagingData
import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksInfo
import kotlinx.coroutines.flow.Flow

data class DrinksFavoritesState(
    val favoritesOperation: DrinksFavoritesOperation = DrinksFavoritesOperation.INITIAL,
    val data: Flow<PagingData<DrinksInfo>>? = null
)

enum class DrinksFavoritesOperation { LOADING, INITIAL, DONE }