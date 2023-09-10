package edu.uchicago.gerber.androidretro.presentation.screens.search.drinks

import androidx.paging.PagingData
import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksInfo
import kotlinx.coroutines.flow.Flow

data class DrinksSearchState(
    val searchOperation: DrinksSearchOperation = DrinksSearchOperation.INITIAL,
    val data: Flow<PagingData<DrinksInfo>>? = null
)

enum class DrinksSearchOperation { LOADING, INITIAL, DONE }