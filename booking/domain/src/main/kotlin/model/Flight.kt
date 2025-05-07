package model

data class Flight(
    val id: String,
    val origin: String,
    val destination: String,
    val departureTime: String,
    val price: String
){
    override fun toString(): String {
        return "Flight(origin='$origin', destination='$destination', departure='$departureTime', price=$price)"
    }
}
