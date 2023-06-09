package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonArtist (
    @Json(name = "Number")
    val id: Int = 0,

    @Json(name = "Name")
    val artistName: String,

    @Json(name = "FilterCategories")
    val artistCategory: String,

    @Json(name = "Address")
    val artistAddress: String,

    @Json(name = "Island")
    val artistIsland: String,

    @Json(name = "Lat")
    val artistLat: Double,

    @Json(name = "Long")
    val artistLng: Double,

    @Json(name = "Website")
    val artistWebsite: String?,

    @Json(name = "Instagram")
    val artistInstagram: String?,

    @Json(name = "Facebook")
    val artistFacebook: String?,

    @Json(name = "CategoryDescription")
    val artistDescription: String?,

    @Json(name = "Friday")
    val fridayExhibition: Boolean
)