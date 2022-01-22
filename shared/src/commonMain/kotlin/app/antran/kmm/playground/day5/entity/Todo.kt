package app.antran.kmm.playground.day5.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName("id")
    val id: Int,
    @SerialName("userId")
    val userId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("completed")
    val completed: Boolean
)