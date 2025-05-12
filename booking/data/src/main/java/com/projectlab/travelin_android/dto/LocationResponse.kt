package dto

data class LocationResponse(
    val data: List<LocationDto>
)

data class LocationDto(
    val name: String,
    val iataCode: String,
    val subType: String,
    val address: Address
)

data class Address(
    val cityName: String,
    val countryName: String
)