package edu.uchicago.gerber.androidretro.data.repository

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailApi {
    @POST(value = "mail")
    suspend fun sendEmail(
        @Body body: RequestBody
    ): Response<String>
}