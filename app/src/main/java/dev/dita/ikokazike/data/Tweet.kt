package dev.dita.ikokazike.data

import kotlinx.serialization.Serializable

@Serializable
data class Tweet(
    val text: String
)