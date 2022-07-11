package se.kulturforeningenkonstvagen.konstvagenockeroarna.data

fun getMarkerNumber(id: Int): String {
    var result = "000"
    val idStringLength = id.toString().length

    val cutString = result.dropLast(idStringLength)
    result = cutString + id.toString()
    return result
}

fun generateImgSrc(id: Int): String {
    val longNumber = getMarkerNumber(id)
    return "exhibition$longNumber"
}

fun generatePortraitSrc(id: Int): String {
    val longNumber = getMarkerNumber(id)
    return "selfie_$longNumber"
}