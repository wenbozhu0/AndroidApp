package edu.uchicago.gerber.androidretro.data.repository

import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksInfo
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MongoApi {
    @POST(value = "favorite")
    suspend fun addToFavorites(
        @Body body: RequestBody
    ): Response<DrinksInfo>

    @GET(value = "favorite")
    suspend fun listFavorites(
        @Query("s") query: String
    ): Response<List<DrinksInfo>>
}