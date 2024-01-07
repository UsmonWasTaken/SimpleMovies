package app.simple.movies.data.storage.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import app.simple.movies.data.storage.entity.StoredReview


@Dao
interface ReviewsDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(movies: List<StoredReview>)

    @Query("SELECT * FROM reviews WHERE movie_id = :movieId ORDER BY created_at ASC")
    fun getReviews(movieId: Int): PagingSource<Int, StoredReview>

    @Query("DELETE FROM reviews")
    suspend fun deleteAll()
}