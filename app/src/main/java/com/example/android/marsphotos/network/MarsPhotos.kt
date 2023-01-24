package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhotos(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
)