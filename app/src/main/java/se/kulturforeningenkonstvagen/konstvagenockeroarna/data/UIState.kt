package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

import com.google.android.gms.maps.model.LatLng

data class UIState(
    val currentArtist: Artist = Artist(
        0,
        "",
        "",
        "",
        "",
        0.00,
        0.00,
        "",
        "",
        "",
        "",
        "000",
        "000"
    ),
    val allArtists: List<Artist> = emptyList(),
    val currentArtists: List<Artist> = emptyList(),
    val allCategories: List<CategorySelection> = emptyList(),
    val currentCategories: List<CategorySelection> = emptyList(),
    val startMapFocus: LatLng = LatLng(57.72, 11.6485441),
    val startMapZoom: Float = 11.8f,
    val currentMapFocus: LatLng = startMapFocus,
    val currentMapZoom: Float = startMapZoom
)