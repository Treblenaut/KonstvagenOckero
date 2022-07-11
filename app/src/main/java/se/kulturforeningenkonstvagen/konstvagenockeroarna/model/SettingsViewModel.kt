package se.kulturforeningenkonstvagen.konstvagenockeroarna.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.Marker

class SettingsViewModel : ViewModel() {
    private val _isFilter: MutableLiveData<Boolean> = MutableLiveData()
    val isFilter: LiveData<Boolean> = _isFilter

    fun onFilterStateChange(newState: Boolean) {
        _isFilter.value = newState
    }

    private val _isMarkerClicked: MutableLiveData<Boolean> = MutableLiveData()
    val isMarkerClicked: LiveData<Boolean> = _isMarkerClicked

    fun onMarkerClickStateChange(newState: Boolean) {
        _isMarkerClicked.value = newState
    }

    private val _currentMarker: MutableLiveData<Marker> = MutableLiveData()
    val currentMarker: LiveData<Marker> = _currentMarker

    fun onClickedMarker(newMarker: Marker) {
        _currentMarker.value = newMarker
    }
}