package com.example.android.marsrealestate.network

import com.squareup.moshi.Json

data class MarsProperty(
    val id: String,
    @Json(name = "img_src") val imgSrc: String,
    val type: String,
    val price: Double
)