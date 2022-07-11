package se.kulturforeningenkonstvagen.konstvagenockeroarna.model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import se.kulturforeningenkonstvagen.konstvagenockeroarna.data.*
import se.kulturforeningenkonstvagen.konstvagenockeroarna.network.ArtistApi
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ArtistViewModel(private val database: ArtistRoomDatabase) : ViewModel() {
    private var _uiState = mutableStateOf(UIState())
    val uiState: State<UIState> = _uiState



    init {
        getJsonData()
        generateArtists()
        createCategories()
    }

    private fun parseJsonData(jsonList: List<JsonArtist>) {
        for (artist in jsonList) {
            insertArtist(newArtist(artist))
        }
    }

    private fun getJsonData() {
        viewModelScope.launch {
            try {
                val listResult = ArtistApi.retrofitService.getJson()
                parseJsonData(listResult)
            } catch (e: Exception) {
                Log.d("failureError", e.message.toString())
            }
        }
    }

    private fun newArtist(artist: JsonArtist): Artist {
        return Artist(
            id = artist.id,
            artistName = artist.artistName,
            artistCategory = artist.artistCategory,
            artistIsland = artist.artistIsland,
            artistAddress = artist.artistAddress,
            artistLat = artist.artistLat,
            artistLng = artist.artistLng,
            artistWebsite = artist.artistWebsite,
            artistInstagram = artist.artistInstagram,
            artistFacebook = artist.artistFacebook,
            artistDescription = artist.artistDescription,
            artistExhibitionImage = generateImgSrc(artist.id),
            artistPortraitImage = generatePortraitSrc(artist.id)
        )
    }

    private fun insertArtist(artist: Artist) {
        viewModelScope.launch {
            database.artistDao().insert(artist)
        }
    }

    fun generateArtists() {
        viewModelScope.launch {
            database.artistDao().getAllByNumber().collect { response ->
                onEvent(UIEvent.GenerateArtists(response))
            }
        }
    }

    fun getAllArtists(): List<Artist> {
        return uiState.value.allArtists
    }

    fun clearFilter() {
        for (category in uiState.value.allCategories) {
            category.clearSelection()
        }

        onEvent(UIEvent.CurrentArtistsChanged(uiState.value.allArtists))
    }

    fun applyFilter() {
        checkCurrentCategories()
        val filteredList = setupFilter()
        onEvent(UIEvent.CurrentArtistsChanged(filteredList))
    }

    private fun setupFilter(): List<Artist>{
        val filteredList = arrayListOf<Artist>()

        viewModelScope.launch {
            for (person in uiState.value.allArtists) {
                for (category in getCurrentCategories()) {
                    if (person.artistCategory.contains(category.artistCategory.value, ignoreCase = true)) {
                        filteredList.add(person)
                        break
                    }
                }
            }
        }

        return filteredList
    }

    class ArtistViewModelFactory(private val database: ArtistRoomDatabase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
                return ArtistViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private fun createCategories() {
        val categories = ArtistCategories.values()
        val categorySelection = mutableListOf<CategorySelection>()

        categories.forEach {
            categorySelection.add(
                CategorySelection(it, MutableLiveData(false))
            )
        }

        onEvent(UIEvent.GenerateCategories(categorySelection))

        /*viewModelScope.launch {
            categories.forEach {
                categorySelection.add(
                    CategorySelection(it, MutableLiveData())
                )
            }
        }*/
    }

    fun onEvent(event: UIEvent) {
        when(event) {
            is UIEvent.CurrentArtistChanged -> {
                _uiState.value = _uiState.value.copy(
                    currentArtist = event.artist
                )
            }
            is UIEvent.GenerateArtists -> {
                _uiState.value = _uiState.value.copy(
                    allArtists = event.artists
                )
            }
            is UIEvent.CurrentArtistsChanged -> {
                _uiState.value = _uiState.value.copy(
                    currentArtists = event.artists
                )
            }
            is UIEvent.GenerateCategories -> {
                _uiState.value = _uiState.value.copy(
                    allCategories = event.categories
                )
            }
            is UIEvent.CurrentCategoriesChanged -> {
                _uiState.value = _uiState.value.copy(
                    currentCategories = event.categories
                )
            }
            is UIEvent.CurrentMapFocusChanged -> {
                _uiState.value = _uiState.value.copy(
                    currentMapFocus = event.coordinates
                )
            }
            is UIEvent.CurrentMapZoomChanged -> {
                _uiState.value = _uiState.value.copy(
                    currentMapZoom = event.zoom
                )
            }
            is UIEvent.CurrentMapFocusReset -> {
                _uiState.value = _uiState.value.copy(
                    currentMapFocus = event.coordinates
                )
            }
            is UIEvent.CurrentMapZoomReset -> {
                _uiState.value = _uiState.value.copy(
                    currentMapZoom = event.zoom
                )
            }
        }
    }

    fun getCurrentArtist(): Artist {
        return uiState.value.currentArtist
    }

    fun checkCurrentCategories() {
        val currentCategories = arrayListOf<CategorySelection>()
        val allCategories = uiState.value.allCategories

        for (category in allCategories) {
            if (category.isSelected.value == true) {
                currentCategories.add(
                    category
                )
            }
        }

        onEvent(UIEvent.CurrentCategoriesChanged(currentCategories))
    }

    fun getCurrentCategories(): List<CategorySelection> {
        return uiState.value.currentCategories
    }

    fun getCurrentArtists(): List<Artist> {
        return uiState.value.currentArtists
    }

    fun getCurrentMapFocus(): LatLng {
        return uiState.value.currentMapFocus
    }

    fun getCurrentMapZoom(): Float {
        return uiState.value.currentMapZoom
    }
}
