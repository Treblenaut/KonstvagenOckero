package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonArtist (
    @Json(name = "Number")
    val id: Int = 0,

    @Json(name = "Name")
    val artistName: String,

    @Json(name = "Category")
    val artistCategory: String,

    @Json(name = "Address")
    val artistAddress: String,

    @Json(name = "Island")
    val artistIsland: String,

    @Json(name = "Coordinates")
    val artistCoordinates: String,

    @Json(name = "Latitude")
    val artistLat: Double,

    @Json(name = "Longitude")
    val artistLng: Double,

    @Json(name = "Website")
    val artistWebsite: String?,

    @Json(name = "Instagram")
    val artistInstagram: String?,

    @Json(name = "Facebook")
    val artistFacebook: String?,

    @Json(name = "Description")
    val artistDescription: String?
)