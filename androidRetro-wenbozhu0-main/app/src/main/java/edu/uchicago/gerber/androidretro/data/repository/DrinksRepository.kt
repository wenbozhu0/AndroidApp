package edu.uchicago.gerber.androidretro.data.repository

import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DrinksRepository(private val drinksApi: DrinksApi) {
    suspend fun getDrinks(
        query: String
    ): Response<DrinksResponse> {
        return withContext(Dispatchers.IO) {
            drinksApi.getDrinks(
                query = query
            )
        }
    }
}