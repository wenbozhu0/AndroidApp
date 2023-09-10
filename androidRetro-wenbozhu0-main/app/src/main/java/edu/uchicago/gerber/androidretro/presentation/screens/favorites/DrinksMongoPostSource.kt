package edu.uchicago.gerber.androidretro.presentation.screens.favorites

import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksInfo
import edu.uchicago.gerber.androidretro.data.repository.MongoRepository

class DrinksMongoPostSource(private val mongoRepository: MongoRepository
) {
    suspend fun post(params: DrinksInfo): Result<DrinksInfo?> {
        return try {
            val response = mongoRepository.addFavorites(params)

            if (response.isSuccessful) {
                val body = response.body()
                Result.success(body)
            } else {
                print("failed")
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            print(e)
            Result.failure(e)
        }
    }
}