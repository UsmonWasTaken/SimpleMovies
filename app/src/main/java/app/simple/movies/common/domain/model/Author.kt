package app.simple.movies.common.domain.model

data class Author(
    val name: String,
    val username: String,
    val avatarPath: String?,
    val rating: Int?
)