package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Artist::class], version = 5, exportSchema = false)
abstract class ArtistRoomDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao

    companion object {
        @Volatile
        private var INSTANCE: ArtistRoomDatabase? = null
        fun getDatabase(context: Context): ArtistRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArtistRoomDatabase::class.java,
                    "artist_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}