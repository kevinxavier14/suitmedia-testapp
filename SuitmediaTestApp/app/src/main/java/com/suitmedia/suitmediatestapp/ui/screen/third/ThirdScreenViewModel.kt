package com.suitmedia.suitmediatestapp.ui.screen.third

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suitmedia.suitmediatestapp.api.ApiConfig
import com.suitmedia.suitmediatestapp.response.RegresInItem
import com.suitmedia.suitmediatestapp.response.RegresInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreenViewModel: ViewModel() {

    private val _result = MutableLiveData<List<RegresInItem?>>()
    val result: LiveData<List<RegresInItem?>> = _result

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "FirstScreenViewModel"
    }

    fun getUserCollection(page: Int, perPage: Int) {
        _isLoading.value = true
        val client = ApiConfig.regresInApi().getUsers(page, perPage)

        client.enqueue(object : Callback<RegresInResponse> {
            override fun onResponse(
                call: Call<RegresInResponse>,
                response: Response<RegresInResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _result.value = response.body()?.data

                    Log.e(TAG, "isSuccessful: ${response.message()}")
                    Log.e(TAG, "${response.body()}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegresInResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

}