package edu.uchicago.gerber.androidretro.data.repository

import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DrinksApi {
    @Headers(
        "X-RapidAPI-Host: the-cocktail-db.p.rapidapi.com",
        "X-RapidAPI-Key: c699aeb2d8mshae0f31b288cf0f4p116dcfjsn8742c609fe7d"
    )
    @GET(value = "search.php")
    suspend fun getDrinks(
        @Query("s") query: String
    ): Response<DrinksResponse>
}