package se.kulturforeningenkonstvagen.konstvagenockeroarna

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapsSdkInitializedCallback
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.ArtistRoomDatabase
import se.kulturforeningenkonstvagen.konstvagenockeroarna.model.ArtistViewModel
import se.kulturforeningenkonstvagen.konstvagenockeroarna.screens.MainScreen
import se.kulturforeningenkonstvagen.konstvagenockeroarna.ui.theme.KonstvågenÖckeröarnaTheme

class MainActivity : ComponentActivity(), OnMapsSdkInitializedCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database: ArtistRoomDatabase by lazy { ArtistRoomDatabase.getDatabase(this) }

        val factory = ArtistViewModel.ArtistViewModelFactory(database)
        val artistViewModel = ViewModelProvider(this, factory)[ArtistViewModel::class.java]

        // Google Map
        MapsInitializer.initialize(applicationContext, MapsInitializer.Renderer.LATEST, this)

        setContent {
            KonstvågenÖckeröarnaTheme {
                MainScreen(artistViewModel)
            }
        }
    }

    override fun onMapsSdkInitialized(renderer: MapsInitializer.Renderer) {
        when (renderer) {
            MapsInitializer.Renderer.LATEST -> Log.d("MapsDemo", "The latest version of the renderer is used.")
            MapsInitializer.Renderer.LEGACY -> Log.d("MapsDemo", "The legacy version of the renderer is used.")
        }
    }
}