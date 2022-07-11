package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
data class Artist(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val artistName: String,

    @ColumnInfo(name = "category")
    val artistCategory: String,

    @ColumnInfo(name = "address")
    val artistAddress: String,

    @ColumnInfo(name = "island")
    val artistIsland: String,

    @ColumnInfo(name = "latitude")
    val artistLat: Double,

    @ColumnInfo(name = "longitude")
    val artistLng: Double,

    @ColumnInfo(name = "website")
    val artistWebsite: String?,

    @ColumnInfo(name = "instagram")
    val artistInstagram: String?,

    @ColumnInfo(name = "facebook")
    val artistFacebook: String?,

    @ColumnInfo(name = "description")
    val artistDescription: String?,

    @ColumnInfo(name = "exhibition")
    val artistExhibitionImage: String,

    @ColumnInfo(name = "portrait")
    val artistPortraitImage: String
)