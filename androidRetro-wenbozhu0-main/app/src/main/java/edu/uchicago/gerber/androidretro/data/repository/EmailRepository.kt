package edu.uchicago.gerber.androidretro.data.repository

import com.google.gson.Gson
import edu.uchicago.gerber.androidretro.data.models.EmailDTO
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class EmailRepository(private val emailApi: EmailApi) {
    suspend fun send(
        emailDTO: EmailDTO
    ): Response<String> {
        val json = Gson().toJson(emailDTO)
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        return emailApi.sendEmail(json)
    }
}