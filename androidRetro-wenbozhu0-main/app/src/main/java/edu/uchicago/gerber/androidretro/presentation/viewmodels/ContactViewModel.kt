package edu.uchicago.gerber.androidretro.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uchicago.gerber.androidretro.data.repository.ApiProvider
import edu.uchicago.gerber.androidretro.data.repository.EmailRepository
import edu.uchicago.gerber.androidretro.data.repository.MongoRepository
import edu.uchicago.gerber.androidretro.presentation.screens.contact.EmailSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ContactViewModel: ViewModel() {
    private val emailRepository: EmailRepository = EmailRepository(ApiProvider.emailApi());
    fun sendMessage(subject: String, message: String) {
        // mongoRepository.addFavorites(drinks);
        viewModelScope.launch {
            EmailSource(emailRepository).sendEmail(subject, message)
        }
    }
}