package se.kulturforeningenkonstvagen.konstvagenockeroarna.network

import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.JsonArtist
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://treblenaut.github.io/KonstvagenOckeroarna/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ArtistApiService{
    @GET("artists.json")
    suspend fun getJson(): List<JsonArtist>
}

object ArtistApi {
    val retrofitService: ArtistApiService by lazy {
        retrofit.create(ArtistApiService::class.java)
    }
}