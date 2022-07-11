package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(artist: Artist)

    @Update
    suspend fun update(artist: Artist)

    @Delete
    suspend fun delete(artist: Artist)

    @Query("SELECT * FROM artist WHERE id = :id")
    fun getArtist(id: Int): Flow<Artist>

    @Query("SELECT * FROM artist WHERE name = :name")
    fun getArtist(name: String): Flow<Artist>

    @Query("SELECT * FROM artist ORDER BY name ASC")
    fun getAllByName(): Flow<List<Artist>>

    @Query("SELECT * FROM artist ORDER BY island ASC")
    fun getAllByIsland(): Flow<List<Artist>>

    @Query("SELECT * FROM artist ORDER BY id ASC")
    fun getAllByNumber(): Flow<List<Artist>>

    @Query("SELECT * FROM artist WHERE category LIKE '%' || :category || '%' ORDER BY id ASC")
    fun getArtistsByCategory(category: String): Flow<List<Artist>>
}