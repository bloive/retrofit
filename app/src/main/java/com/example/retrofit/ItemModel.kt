package com.example.retrofit

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ItemModel(
    val name: String? = null,
    val capital: String? = null,
    val borders: List<String>? = null,
    val translations: Translation? = null,
    val languages: List<Language>? = null,
    @Json(name = "flag")
    val flag:String? = null
)

data class Translation(
    val es: String? = null,
    val ja: String? = null,
    val fa: String? = null
)

data class Language(
    @SerializedName("iso6391", alternate = ["iso639_1"])
    val iso6391: String? = null
)