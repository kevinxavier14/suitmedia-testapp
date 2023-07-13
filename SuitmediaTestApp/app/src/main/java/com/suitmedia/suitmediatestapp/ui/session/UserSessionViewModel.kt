package com.suitmedia.suitmediatestapp.ui.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserSessionViewModel : ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _apiName = MutableLiveData<String>()
    val apiName: LiveData<String> = _apiName

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setApiName(apiName: String) {
        _apiName.value = apiName
    }

    fun clearUserData() {
        _userName.value = ""
        _apiName.value = ""
    }
}