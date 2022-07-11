package se.kulturforeningenkonstvagen.konstvagenockeroarna.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.UIEvent
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.getMarkerNumber
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.ArtistViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.SettingsViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.ui.theme.bitmapDescriptorFromVector
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun MapScreen(
    artistViewModel: ArtistViewModel,
    navController: NavController,
    settingsViewModel: SettingsViewModel,
    onFilterStateChange: (Boolean) -> Unit
) {
    val context = LocalContext.current

    var allArtists = artistViewModel.getAllArtists()

    if (artistViewModel.getCurrentArtists().isNotEmpty()) {
        allArtists = artistViewModel.getCurrentArtists()
    }

    val startPosition = artistViewModel.getCurrentMapFocus()
    val mapZoom = artistViewModel.getCurrentMapZoom()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startPosition, mapZoom)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            compassEnabled = true,
            myLocationButtonEnabled = false,
            zoomControlsEnabled = false
        )
    ) {        
        allArtists.forEach { artist ->
            val resourceString = "ic_marker" + getMarkerNumber(artist.id)
            val resourceId = context.resources.getIdentifier(resourceString, "drawable", context.packageName)
            val scope = rememberCoroutineScope()

            MarkerInfoWindow(
                state = MarkerState(LatLng(artist.artistLat, artist.artistLng)),
                icon = bitmapDescriptorFromVector(context, resourceId),
                title = artist.artistName,
                onInfoWindowClick = {
                    scope.launch {
                        artistViewModel.onEvent(UIEvent.CurrentArtistChanged(artist = artist))
                        navController.navigate("detail/${it.title}")
                    }
                },
                infoWindowAnchor = Offset(0.5f, -0.1f)
            ) {
                PreviewCard(artist)
            }
        }


    }

    FilterDialog(settingsViewModel = settingsViewModel, artistViewModel = artistViewModel)
}
