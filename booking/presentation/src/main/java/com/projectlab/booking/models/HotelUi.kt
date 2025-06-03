package com.projectlab.booking.models

import com.projectlab.core.data.utils.CurrencyUtils
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.HotelPrice
import java.text.NumberFormat
import java.util.Locale

data class HotelUi(
    val id: String,
    val stars: Int,
    val name: String,
    val isFavorite: Boolean,
    val displayImageUrl: String,
    val photoUrls: List<String>,
    val price: DisplayablePrice
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
        price = hotelOffers!![0].price.toDisplayablePrice()
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







