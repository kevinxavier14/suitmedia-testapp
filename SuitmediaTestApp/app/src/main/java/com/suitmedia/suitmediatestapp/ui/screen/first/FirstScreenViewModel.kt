package com.suitmedia.suitmediatestapp.ui.screen.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suitmedia.suitmediatestapp.ui.session.UserSessionViewModel
import java.util.*

class FirstScreenViewModel: ViewModel() {

    fun checkPalindrome(inputString: String): Boolean {
        // Remove non-alphanumeric characters and convert to lowercase
        val cleanedString = inputString.replace(Regex("[^A-Za-z0-9]"), "").lowercase(Locale.ROOT)

        // Check if the cleaned string is equal to its reverse
        return cleanedString == cleanedString.reversed()
    }

}