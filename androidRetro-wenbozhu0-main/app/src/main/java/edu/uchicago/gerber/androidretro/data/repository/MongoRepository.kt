package edu.uchicago.gerber.androidretro.data.repository

import com.google.gson.Gson
import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class MongoRepository(private val mongoApi: MongoApi) {
    suspend fun addFavorites(
        drinksInfo: DrinksInfo
    ): Response<DrinksInfo> {
        val json = Gson().toJson(drinksInfo)
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        return mongoApi.addToFavorites(json)
    }

    suspend fun listFavorites(
        query: String
    ): Response<List<DrinksInfo>>{
        return withContext(Dispatchers.IO) {
            mongoApi.listFavorites(
                query = query
            )
        }
    }
}