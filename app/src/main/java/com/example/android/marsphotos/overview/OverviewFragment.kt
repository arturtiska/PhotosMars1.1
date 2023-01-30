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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.marsphotos.databinding.FragmentOverviewBinding

/**
 * Este fragmento mostra o status da transação de serviços da Web de fotos da Mars.
 */
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    /**
     * Infla o layout com Data Binding, define seu proprietário do ciclo de vida para o OverviewFragment
     * para permitir que o Data Binding observe o LiveData e configure o RecyclerView com um adaptador.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val binding = FragmentOverviewBinding.inflate(inflater)

        // Permite que Data Binding observe LiveData com o ciclo de vida deste fragmento
        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this


        // Dando o acesso de ligação ao OverviewViewModel
        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root
    }
}
