package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

import com.google.android.gms.maps.model.LatLng

sealed class UIEvent {
    data class CurrentArtistChanged(val artist: Artist): UIEvent()
    data class GenerateArtists(val artists: List<Artist>): UIEvent()
    data class CurrentArtistsChanged(val artists: List<Artist>): UIEvent()
    data class GenerateCategories(val categories: List<CategorySelection>): UIEvent()
    data class CurrentCategoriesChanged(val categories: List<CategorySelection>): UIEvent()
    data class CurrentMapFocusChanged(val coordinates: LatLng): UIEvent()
    data class CurrentMapZoomChanged(val zoom: Float): UIEvent()
    data class CurrentMapZoomReset(val zoom: Float): UIEvent()
    data class CurrentMapFocusReset(val coordinates: LatLng): UIEvent()
}