package app.simple.movies.common.domain.model

data class Credit(
    val id: Int,
    val name: String,
    val character: String,
    val department: String,
    val profilePath: String?,
    val order: Int
)