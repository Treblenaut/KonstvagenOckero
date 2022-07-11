package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

import androidx.lifecycle.MutableLiveData

data class CategorySelection(
    val artistCategory: ArtistCategories,
    var isSelected: MutableLiveData<Boolean>
) {
    fun onSelectionChanged(newState: Boolean) {
        isSelected.value = newState
    }

    fun clearSelection() {
        isSelected.value = false
    }
}