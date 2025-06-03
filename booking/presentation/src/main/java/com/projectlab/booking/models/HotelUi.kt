package com.projectlab.booking.models

import com.projectlab.core.data.utils.CurrencyUtils
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.HotelOffer
import com.projectlab.core.domain.model.HotelPrice
import okhttp3.Address
import java.text.NumberFormat
import java.util.Locale

data class HotelUi(
    val id: String,
    val stars: Int,
    val name: String,
    val isFavorite: Boolean,
    val displayImageUrl: String,
    val photoUrls: List<String>,
    val price: DisplayablePrice,
    val hotelOffers: List<HotelOffer>?,
    val address: String,
    val city: String,
    val country: String,
    val amenities: List<String>?
)

data class DisplayablePrice(
    val value: HotelPrice,
    val formatted: String
)

fun Hotel.toHotelUi(): HotelUi {
    return HotelUi(
        id = id,
        name = name,
        stars = rating.stars,
        isFavorite = isFavourite,
        displayImageUrl = photoUrls[0],
        photoUrls = photoUrls,
        price = hotelOffers!![0].price.toDisplayablePrice(),
        hotelOffers = hotelOffers,
        address = location.address,
        city = location.city,
        country = location.country,
        amenities = amenities
    )
}

fun HotelPrice.toDisplayablePrice(): DisplayablePrice {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    val currencySymbol = CurrencyUtils().getCurrencySymbolByCurrencyCode(this.currencyCode)

    return DisplayablePrice(
        value = this,
        formatted = "${currencySymbol}${formatter.format(amount)} ${currencyCode}"
    )
}







