package se.kulturforeningenkonstvagen.konstvagenockeroarna.screens

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.Artist
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.LinkType
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.UIEvent
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.ArtistViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun DetailScreen(
    artistViewModel: ArtistViewModel,
    navController: NavController
) {
    val currentArtist = artistViewModel.getCurrentArtist()

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(currentArtist.artistExhibitionImage)
                .crossfade(true)
                .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(currentArtist.artistPortraitImage)
                .crossfade(true)
                .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .offset(x = (-20).dp, y = 40.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomEnd),
                onError = {}
            )
        }

        Column(
            Modifier.padding(16.dp)
        ) {
            // NAME
            Text(
                text = currentArtist.artistName,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.15.sp
                ),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(end = 100.dp)
            )

            // CATEGORY
            Text(
                text = currentArtist.artistCategory,
                style = MaterialTheme.typography.overline,
                fontSize = 16.sp
            )

            // DESCRIPTION
            if (currentArtist.artistDescription != "") {
                currentArtist.artistDescription?.let {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        thickness = 1.dp
                    )

                    Text(
                        style = MaterialTheme.typography.body2,
                        text = it,
                        textAlign = TextAlign.Justify
                    )
                }
            }

            ContactDetails(currentArtist, context, artistViewModel, navController)
        }
    }
}

@Composable
fun ContactDetails(artist: Artist, context: Context, artistViewModel: ArtistViewModel, navController: NavController) {
    Row(
        Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // WEBSITE, FACEBOOK, INSTAGRAM
        artist.artistWebsite?.let {
            SocialLink(
                linkType = LinkType.WEBSITE,
                artistLink = it,
                context = context,
                isInstagram = false
            )
        }
        artist.artistFacebook?.let {
            SocialLink(
                linkType = LinkType.FACEBOOK,
                artistLink = it,
                context = context,
                isInstagram = false
            )
        }
        artist.artistInstagram?.let {
            SocialLink(
                linkType = LinkType.INSTAGRAM,
                artistLink = it,
                context = context,
                isInstagram = true
            )
        }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                artistViewModel.onEvent(
                    UIEvent.CurrentMapFocusChanged(
                        LatLng(
                            artist.artistLat,
                            artist.artistLng
                        )
                    )
                )
                artistViewModel.onEvent(UIEvent.CurrentMapZoomChanged(18f))
                navController.navigate("map")
            }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Filled.PinDrop,
            "Map pin icon",
            tint = MaterialTheme.colors.primary
        )

        // ADDRESS
        Text(
            text = "${artist.artistAddress}, ${artist.artistIsland}",
            Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun SocialLink(linkType: LinkType, artistLink: String, context: Context, isInstagram: Boolean) {
    if (!artistLink.isEmpty()) {
        val drawableName = "ic_${linkType}".lowercase()
        val uriHandler = LocalUriHandler.current

        Row(
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable {
                    val parsedLink = parseSocialLink(artistLink, isInstagram)
                    uriHandler.openUri(parsedLink)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val vectorResId = context.resources.getIdentifier(
                drawableName,
                "drawable",
                context.packageName
            )
            Icon(
                painter = painterResource(id = vectorResId),
                contentDescription = "${linkType.value} Icon",
                Modifier
                    .size(32.dp)
                    .padding(end = 8.dp),
                tint = MaterialTheme.colors.primary
            )

            Text(
                text = linkType.value,
                Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun parseSocialLink(artistLink: String, isInstagram: Boolean): String {
    var substring = ""
    var https = ""

    if (isInstagram) {
        substring = artistLink
        https = "https://www.instagram.com/"
    } else {
        substring = artistLink.substringAfter("www.")
        https = "https://www."
    }
    return https + substring
}
