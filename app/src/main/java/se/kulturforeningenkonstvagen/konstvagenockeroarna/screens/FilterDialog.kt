package se.kulturforeningenkonstvagen.konstvagenockeroarna.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.CategorySelection
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.ArtistViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.SettingsViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.ui.theme.KonstvågenÖckeröarnaTheme
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterDialog(
    settingsViewModel: SettingsViewModel,
    artistViewModel: ArtistViewModel
) {
    val isFilterDialog by settingsViewModel.isFilter.observeAsState(false)

    KonstvågenÖckeröarnaTheme {
        Column {
            if (isFilterDialog) {
                Dialog(
                    onDismissRequest = {
                        settingsViewModel.onFilterStateChange(false)
                        DialogProperties(usePlatformDefaultWidth = false)
                    }
                ) {
                    CustomFilterDialog(artistViewModel, settingsViewModel)
                }
            }
        }
    }
}

@Composable
fun CustomFilterDialog(
    artistViewModel: ArtistViewModel,
    settingsViewModel: SettingsViewModel
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Filtrera efter kategorier",
                Modifier.padding(8.dp)
            )

            // FILTER CATEGORY OPTIONS
            ChipGroup(artistViewModel = artistViewModel)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // CLEAR FILTER
                Button(
                    onClick = {
                        artistViewModel.clearFilter()
                        artistViewModel.generateArtists()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = "Rensa",
                        color = Color.DarkGray
                    )
                }

                // APPLY FILTER
                Button(
                    onClick = {
                        artistViewModel.applyFilter()
                        settingsViewModel.onFilterStateChange(false)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(text = "Filtrera")
                }
            }
        }
    }
}

@Composable
fun ChipGroup(
    artistViewModel: ArtistViewModel,
    categories: List<CategorySelection> = artistViewModel.uiState.value.allCategories
) {
    KonstvågenÖckeröarnaTheme {
        Column {
            FlowRow(
                mainAxisSpacing = 4.dp,
                crossAxisSpacing = 4.dp,
                mainAxisAlignment = FlowMainAxisAlignment.Start,
                crossAxisAlignment = FlowCrossAxisAlignment.Center
            ) {
                for (category in categories) {
                    CustomChip(category = category)
                }
            }
        }
    }
}


@Composable
fun CustomChip(
    category: CategorySelection
) {
    val isSelected by category.isSelected.observeAsState(false)

    Surface(
        modifier = Modifier
            .border(
                border = BorderStroke(
                    ButtonDefaults.OutlinedBorderSize,
                    if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground.copy(
                        alpha = ButtonDefaults.OutlinedBorderOpacity
                    )
                ),
                shape = CircleShape
            )
            .clip(
                shape = CircleShape
            )
            .clickable(onClick = {
                if (isSelected) {
                    category.onSelectionChanged(false)
                } else {
                    category.onSelectionChanged(true)
                }
            })
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(MaterialTheme.colors.background)
        ) {
            Text(
                text = category.artistCategory.value,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp
                ),
                color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSecondary,
                modifier = Modifier
                    .padding(8.dp)
            )
            AnimatedVisibility(visible = isSelected) {
                Icon(
                    Icons.Filled.Check, contentDescription = "Check mark",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            AnimatedVisibility(visible = !isSelected) {
                Icon(
                    Icons.Filled.Check, contentDescription = "Check mark",
                    tint = MaterialTheme.colors.background,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }


}