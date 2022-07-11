package se.kulturforeningenkonstvagen.konstvagenockeroarna

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.ArtistViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.SettingsViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.screens.*

@Composable
fun BottomNavGraph(
    artistViewModel: ArtistViewModel,
    navController: NavHostController,
    settingsViewModel: SettingsViewModel
) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Info.route

    ) {
        composable(route = BottomNavItem.Map.route) {
            MapScreen(artistViewModel, navController, settingsViewModel, onFilterStateChange = { settingsViewModel.onFilterStateChange(it) })
        }
        composable(route = BottomNavItem.List.route) {
            ListScreen(artistViewModel, navController, settingsViewModel, onFilterStateChange = { settingsViewModel.onFilterStateChange(it) })
        }
        composable(route = BottomNavItem.Info.route) {
            InfoScreen()
        }
        composable(route = "detail/{artistName}", arguments = listOf(navArgument("artistName") {type = NavType.StringType})
        ) {
            it.arguments?.getString("artistName")?.let { DetailScreen(artistViewModel, navController) }
        }
    }
}