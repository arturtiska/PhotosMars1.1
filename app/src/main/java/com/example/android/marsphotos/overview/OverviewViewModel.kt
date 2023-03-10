/*
 * Copyright (C) 2021 The Android Open Source Project.
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
 */

package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.MarsPhotos
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    // O MutableLiveData interno que armazena o status da requisição mais recente
    private val _status = MutableLiveData<MarsApiStatus>()

    // O LiveData imutável externo para o status da solicitação
    val status: LiveData<MarsApiStatus> = _status

    private val _photos = MutableLiveData<List<MarsPhotos>>()
    val photos: LiveData<List<MarsPhotos>> = _photos
    init {
        getMarsPhotos()
    }

    /**
     * Obtém informações de fotos de Marte do serviço Mars API Retrofit e atualiza o
     *
     */
    private fun getMarsPhotos() {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception){
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }

        }
    }
}
