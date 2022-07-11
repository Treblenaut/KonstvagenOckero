package se.kulturforeningenkonstvagen.konstvagenockeroarna.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Map : BottomNavItem(
        title = "Karta",
        icon = Icons.Filled.Map,
        route = "map"
    )

    object List : BottomNavItem(
        title = "Lista",
        icon = Icons.Filled.List,
        route = "list"
    )

    object Info : BottomNavItem(
        title = "Info",
        icon = Icons.Filled.Info,
        route = "info"
    )
}
