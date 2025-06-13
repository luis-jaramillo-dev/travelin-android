package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DoNotDisturbOn
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Hail
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.designsystem.R

object TravelinIcon {
    val Account = Icons.Rounded.AccountCircle
    val Airplane = R.drawable.travel_24px
    val ArrowBack = Icons.AutoMirrored.Rounded.ArrowBack
    val Back = Icons.AutoMirrored.Rounded.KeyboardArrowLeft
    val Bus = R.drawable.directions_bus_24px
    val Cross = R.drawable.cross_24px
    val Expand = Icons.Filled.ArrowDropDown
    val Eye = R.drawable.visibility_fill_24px //remove_red_eye
    val Forward = Icons.AutoMirrored.Rounded.KeyboardArrowRight
    val Heart = Icons.Outlined.Favorite
    val HeartBorder = Icons.Outlined.FavoriteBorder
    val History = R.drawable.history_24px
    val Hotel = R.drawable.baseline_local_hotel_24
    val House = R.drawable.house_fill_24px
    val Less = Icons.Filled.DoNotDisturbOn
    val Location = Icons.Outlined.LocationOn
    val Moon = R.drawable.bedtime_fill_24px
    val More = Icons.Filled.AddCircle
    val Qr = R.drawable.qr_code_24px
    val Remove = R.drawable.check_indeterminate_small_grade200_24px
    val Search = Icons.Outlined.Search
    val Share = Icons.Rounded.Share
    val Star = Icons.Rounded.Star //grade
    val Time = R.drawable.schedule_fill_24px
    val VisibilityOff = R.drawable.visibility_off_24px
    val Hail = Icons.Outlined.Hail
}

//Search destination
@Composable
fun IconHotel(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color(35, 183, 246),
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Hotel),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconAirplane(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color(35, 183, 246),
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Airplane),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .rotate(45f)
            .alpha(alpha),
    )
}

@Composable
fun IconLocation(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Location,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

//Favorites in red color = Color(255,0,0)
@Composable
fun IconHeartBorder(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.HeartBorder,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

//Footer
//Color selected  Color(35,183,246)
@Composable
fun IconHouseSelected(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color(35, 183, 246),
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.House),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconHouse(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 0.2f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.House),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconHeartSelected(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color(35, 183, 246),
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Heart,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconHeart(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 0.2f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Heart,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconProfileSelected(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color(35, 183, 246),
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Account,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconProfile(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 0.2f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Account,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

//Icons of inputs
@Composable
fun IconEye(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Eye),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconVisibilityOff(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.VisibilityOff),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconSearch(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 0.5f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Search,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconMoon(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Moon),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconMore(
    modifier: Modifier = Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 0.5f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.More,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconLess(
    modifier: Modifier = Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 0.5f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Less,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconExpand(
    modifier: Modifier = Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 0.5f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Expand,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconRectangleSelect(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color(255, 255, 255),
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Remove),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconRectangle(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color(255, 255, 255),
    alpha: Float = 0.2f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Remove),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

//Icons of Detail
@Composable
fun IconQr(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Qr),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconTime(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Time),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconBus(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Bus),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconStar(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color(255, 178, 63),
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Star,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconStarEmpty(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 0.2f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Star,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

//Icons back and share
@Composable
fun IconBackArrow(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.ArrowBack,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconBack(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Back,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconForward(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Forward,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconShare(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Share,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconHistory(
    modifier: Modifier = Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.History),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconCross(
    modifier: Modifier = Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(TravelinIcon.Cross),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}

@Composable
fun IconItinerary(
    modifier: Modifier,
    size: Int = 24,
    tint: Color = Color.Black,
    alpha: Float = 1f,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = TravelinIcon.Hail,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .alpha(alpha),
    )
}
