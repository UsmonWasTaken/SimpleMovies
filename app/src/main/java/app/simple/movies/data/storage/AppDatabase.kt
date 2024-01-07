package app.simple.movies.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.simple.movies.data.storage.dao.MovieRemoteKeysDao
import app.simple.movies.data.storage.dao.MoviesDao
import app.simple.movies.data.storage.dao.ReviewRemoteKeysDao
import app.simple.movies.data.storage.dao.ReviewsDao
import app.simple.movies.data.storage.entity.MovieRemoteKeys
import app.simple.movies.data.storage.entity.ReviewRemoteKeys
import app.simple.movies.data.storage.entity.StoredMovie
import app.simple.movies.data.storage.entity.StoredReview


@Database(
    entities = [StoredMovie::class, MovieRemoteKeys::class, StoredReview::class, ReviewRemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDao

    abstract val movieRemoteKeysDao: MovieRemoteKeysDao

    abstract val reviewsDao: ReviewsDao

    abstract val reviewRemoteKeysDao: ReviewRemoteKeysDao

    companion object {
        private const val DATABASE_NAME = "app_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context)
                    .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}