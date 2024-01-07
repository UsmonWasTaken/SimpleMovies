package app.simple.movies.common.domain.model

import java.util.Date

data class Review(
    val id: String,
    val author: Author,
    val content: String,
    val createdAt: Date,
    val updatedAt: Date
)
