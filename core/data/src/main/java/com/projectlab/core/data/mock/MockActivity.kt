package com.projectlab.core.data.mock

import com.projectlab.core.data.model.Activity
import com.projectlab.core.data.model.Price

/**
 * MockActivities contains predefined mock data used for UI development and testing.
 *
 * Usage:
 * - Use this mock data when developing features that require an Activity instance,
 *   especially when the backend is unavailable or the real data is not yet integrated.
 * - Ideal for previews in Jetpack Compose or unit tests that need consistent input data.
 *
 */

object MockActivities {
    val sampleActivity = Activity(
        id = "6379377",
        name = "Cena clandestina en DAME",
        description = "Join the latest culinary trend of immersive and social dining where local hosts invite you into their homes for a magical meal.\n\nDAME es un antiguo almacén que rinde homenaje a los locales clandestinos de la época de la Ley Seca, flirteando con la clandestinidad y elaborando la oferta gastronómica a partir de la mejor materia prima para sorprenderte.\n\nTu anfitrión, Mario, te ofrece un menú degustación de Tapas Gourmet maridados con vino y un delicioso cóctel mexicano.\n\nDe la decoración y la atmósfera hasta los ingredientes de temporada y la presentación de los platos, la experiencia de Mario es de alta calidad y no se lo puede perder!\n\n*Para grupos inferiores a 10 personas, el precio puede aumentar. Clica el tasto \"Privatiza y personaliza el evento\" para obtener una propuesta customizada.",
        pictures = listOf(
            "https://screen-api.vizeater.co/files/1488986/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1501685/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1488984/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1501690/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1501686/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1501691/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1501958/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1487770/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1488987/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1488985/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1487757/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1487769/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1501687/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1501692/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1501688/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1487768/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1488983/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1501689/-/preview/-/progressive/yes/-/format/jpeg/image.jpg",
            "https://screen-api.vizeater.co/files/1488982/-/preview/-/progressive/yes/-/format/jpeg/image.jpg"
        ),
        minimumDuration = "3 horas",
        price = Price(
            amount = "72.0",
            currencyCode = "EUR"
        ),
        rating = 3.5F
    )
}
