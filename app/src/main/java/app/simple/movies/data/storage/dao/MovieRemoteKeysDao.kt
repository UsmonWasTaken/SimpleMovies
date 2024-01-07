package app.simple.movies.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import app.simple.movies.data.storage.entity.MovieRemoteKeys


@Dao
interface MovieRemoteKeysDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(remoteKeys: List<MovieRemoteKeys>)

    @Query("SELECT * FROM movie_remote_keys WHERE movie_id = :movieId")
    suspend fun movieRemoteKeys(movieId: String): MovieRemoteKeys?

    @Query("DELETE FROM movie_remote_keys")
    suspend fun deleteAll()
}