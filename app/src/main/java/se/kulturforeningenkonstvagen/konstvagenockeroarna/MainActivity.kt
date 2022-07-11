package se.kulturforeningenkonstvagen.konstvagenockeroarna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.ArtistRoomDatabase
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.ArtistViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.screens.MainScreen
import se.kulturforeningenkonstvagen.konstvagenockeroarna.ui.theme.KonstvågenÖckeröarnaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database: ArtistRoomDatabase by lazy { ArtistRoomDatabase.getDatabase(this) }

        val factory = ArtistViewModel.ArtistViewModelFactory(database)
        val artistViewModel = ViewModelProvider(this, factory).get(ArtistViewModel::class.java)

        setContent {
            KonstvågenÖckeröarnaTheme {
                MainScreen(artistViewModel)
            }
        }
    }
}