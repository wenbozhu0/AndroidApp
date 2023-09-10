package edu.uchicago.gerber.androidretro.presentation.screens.contact

import edu.uchicago.gerber.androidretro.data.models.EmailDTO
import edu.uchicago.gerber.androidretro.data.repository.EmailRepository
import java.lang.Exception

class EmailSource(private val emailRepository: EmailRepository) {
    suspend fun sendEmail(subject: String, message: String): Result<String?> {
        val to = "wz1305@uchicago.edu"
        val emailDto = EmailDTO()
        emailDto.subject = subject
        emailDto.body = message
        emailDto.email = to

        return try {
            val response = emailRepository.send(emailDto)

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