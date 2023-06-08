package se.kulturforeningenkonstvagen.konstvagenockeroarna.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.Artist

@Composable
fun PreviewCard(artist: Artist) {

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(132.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.background)
            .shadow(elevation = 8.dp)
    ) {
        Row {
            // Fetch image from Internet
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .allowHardware(false)
                .data(artist.artistExhibitionImage)
                .crossfade(true)
                .build(),
                contentDescription = "Image belonging to ${artist.artistName}",
                modifier = Modifier.size(132.dp)
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    Text(
                        text = artist.artistName,
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            letterSpacing = 0.sp
                        ),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = artist.artistCategory,
                        style = MaterialTheme.typography.caption
                    )
                }
                Text(
                    text = artist.artistAddress,
                    style = MaterialTheme.typography.overline,
                    fontSize = MaterialTheme.typography.caption.fontSize
                )
            }

            Text(
                text = artist.id.toString(),
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(top = 8.dp, end = 16.dp),
                color = MaterialTheme.colors.primary
            )
        }
    }
}