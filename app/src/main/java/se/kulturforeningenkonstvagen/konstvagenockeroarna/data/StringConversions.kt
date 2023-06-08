package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

fun getMarkerNumber(id: Int): String {
    var result = "000"
    val idStringLength = id.toString().length

    val cutString = result.dropLast(idStringLength)
    result = cutString + id.toString()
    return result
}

fun createExhibitionUrl(id: Int): String {
    val longNumber = getMarkerNumber(id)
    val base =
        "https://treblenaut.github.io/KonstvagenOckeroarna/docs/images/"
    return "$base$longNumber.jpg"
}

fun createArtistImageUrl(id: Int): String {
    val longNumber = getMarkerNumber(id)
    val base =
        "https://treblenaut.github.io/KonstvagenOckeroarna/docs/images/"
    return base + "selfie_" + longNumber + ".jpg"
}