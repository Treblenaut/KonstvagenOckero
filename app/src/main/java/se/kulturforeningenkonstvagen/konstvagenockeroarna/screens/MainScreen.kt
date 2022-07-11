package se.kulturforeningenkonstvagen.konstvagenockeroarna.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import se.kulturforeningenkonstvagen.konstvagenockeroarna.BottomNavGraph
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.ArtistViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.SettingsViewModel

@Composable
fun MainScreen(
    artistViewModel: ArtistViewModel,
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isFilter by settingsViewModel.isFilter.observeAsState(false)

    val detailText = if (currentDestination?.route?.startsWith("detail") == true) {
        val currentArtist = artistViewModel.getCurrentArtist()
        val currentArtistId = currentArtist.id
        val currentArtistName = currentArtist.artistName

            "${currentArtistId}. $currentArtistName"
    } else {
        null
    }

    val topText =
        if (currentDestination?.route == BottomNavItem.List.route) {
            "Utställare"
        } else if (currentDestination?.route == BottomNavItem.Map.route) {
            "Konstvågen Öckerö"
        } else {
            detailText ?: "Konstvågen Öckerö"
        }

    Scaffold(
        topBar = {
            if (currentDestination?.route?.contains("detail") == true) {
                TopAppBar(
                    title = {
                        Text(
                            text = topText,
                            style = MaterialTheme.typography.subtitle1
                        )
                    },
                    backgroundColor = MaterialTheme.colors.background,

                    // BACK BUTTON
                    navigationIcon = if (navController.previousBackStackEntry != null) {
                        {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }

                    } else {
                        null
                    }
                )
            }
        },

        floatingActionButton = {
            if (currentDestination?.route == BottomNavItem.Map.route || currentDestination?.route == BottomNavItem.List.route) {
                FloatingActionButton(
                    onClick = {
                        settingsViewModel.onFilterStateChange(!isFilter)
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(Icons.Filled.FilterAlt, contentDescription = "filterButton")
                }
            }
        },

        // BOTTOM NAVIGATION
        bottomBar = {
            BottomBar(
                modifier = Modifier
                    .background(MaterialTheme.colors.background),
                navController = navController
            )
        }
    ) { innerPadding ->
        val mModifier = Modifier

        if (currentDestination?.route != "map") {
            mModifier.then(
                Modifier.padding(
                    PaddingValues(0.dp)
                )
            )
        } else {
            mModifier.then(
                Modifier.padding(
                    PaddingValues(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())
                )
            )
        }

        Box(
            modifier = Modifier
                .padding(
                    PaddingValues(start = 0.dp, top = 0.dp, end = 0.dp)
                )
                .then(
                    if (currentDestination?.route?.contains("detail") == true || currentDestination?.route == "list")
                        Modifier.padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))
                    else
                        Modifier.padding(
                            PaddingValues(bottom = 0.dp)
                        )
                )
        ) {
            BottomNavGraph(artistViewModel, navController = navController, settingsViewModel)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier) {
    val screens = listOf(
        BottomNavItem.Map,
        BottomNavItem.List,
        BottomNavItem.Info
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "Navigation icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        selectedContentColor = MaterialTheme.colors.primary,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}