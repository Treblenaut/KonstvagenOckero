package se.kulturforeningenkonstvagen.konstvagenockeroarna.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.Artist
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.UIEvent
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.ArtistViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.SettingsViewModel


@Composable
fun ListScreen(
    artistViewModel: ArtistViewModel,
    navController: NavController,
    settingsViewModel: SettingsViewModel,
    onFilterStateChange: (Boolean) -> Unit
) {
    var allArtists = artistViewModel.getAllArtists()

    if (artistViewModel.getCurrentArtists().isNotEmpty()) {
        allArtists = artistViewModel.getCurrentArtists()
    }

    LazyColumn(
        contentPadding = PaddingValues(12.dp)
    ) {
        items(allArtists) { artist ->
            CustomItem(
                artist = artist,
                navController = navController,
                artistViewModel = artistViewModel
            )
            Divider()
        }
    }

    FilterDialog(settingsViewModel, artistViewModel)
}

@Composable
fun CustomItem(artist: Artist, navController: NavController, artistViewModel: ArtistViewModel) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .clickable {
                artistViewModel.onEvent(UIEvent.CurrentArtistChanged(artist = artist))
                navController.navigate("detail/" + artist.artistName)
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(artist.artistExhibitionImage)
            .crossfade(true)
            .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(48.dp)
                .aspectRatio(1f)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 4.dp)
        ) {
            // NAME
            Text(
                text = artist.artistName,
                color = MaterialTheme.colors.onBackground,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.1.sp
                )
            )

            // CATEGORY
            Text(
                text = artist.artistCategory,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.caption
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {

            // NUMBER
            Text(
                text = artist.id.toString(),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.button
            )

            // ISLAND
            Text(
                text = artist.artistIsland,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.overline
            )
        }

    }


}