/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MarsApiService
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.network.MarsaApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {
        MarsaApi.retrofitService.getProperties().enqueue(object: Callback<List<MarsProperty>> {
            override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
                //TODO
                _response.value = "Success: ${response.body()?.size} Mars properties retrieved"
            }

            override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
                //TODO
                _response.value = "Failure: ${t.message}"
            }

        })
        _response.value = "Set the Mars API Response here!"
    }
}
