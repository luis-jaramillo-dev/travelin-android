package com.projectlab.core.presentation.desingsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.materialIcon
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.presentation.designsystem.R

object TravelinIcon {
    val ArrowBack = Icons.AutoMirrored.Rounded.ArrowBack
    val Star = Icons.Rounded.Star //grade
    val Eye = R.drawable.visibility_fill_24px //remove_red_eye
    val Moon = R.drawable.bedtime_fill_24px
    val HeartBorder = Icons.Outlined.FavoriteBorder
    val Heart = Icons.Outlined.Favorite
    val Account = Icons.Rounded.AccountCircle
    val Hotel = R.drawable.baseline_local_hotel_24
    val Location = Icons.Outlined.LocationOn
    val Back = Icons.AutoMirrored.Rounded.KeyboardArrowLeft
    val Forward = Icons.AutoMirrored.Rounded.KeyboardArrowRight
    val Search = Icons.Outlined.Search
    val Share = Icons.Rounded.Share
    val House = R.drawable.house_fill_24px
    val Remove = R.drawable.check_indeterminate_small_grade200_24px
    val Qr = R.drawable.qr_code_24px
    val Time = R.drawable.schedule_fill_24px
    val Bus = R.drawable.directions_bus_24px
    val Airplane = R.drawable.travel_24px

}

//Search destination
@Composable
fun IconHotel(modifier: Modifier, size: Int = 24, tint: Color = Color(35,183,246), alpha: Float = 1f){
        Icon(
            painterResource(TravelinIcon.Hotel),
            contentDescription = null,
            tint = tint,
            modifier = modifier.size(size.dp)
                .alpha(alpha)
        )
}

@Composable
fun IconAirplane(modifier: Modifier, size: Int = 24, tint: Color = Color(35,183,246), alpha: Float = 1f){
    Icon(
        painterResource(TravelinIcon.Airplane),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .rotate(45f)
            .alpha(alpha)
    )
}

@Composable
fun IconLocation(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        imageVector = TravelinIcon.Location,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

//Favorites in red color = Color(255,0,0)
@Composable
fun IconHeartBorder(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        imageVector = TravelinIcon.HeartBorder,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

//Footer
//Color selected  Color(35,183,246)
@Composable
fun IconHouseSelected(modifier: Modifier, size: Int = 24, tint: Color =  Color(35,183,246), alpha: Float = 1f){
    Icon(
        painterResource(TravelinIcon.House),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconHouse(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 0.2f){
    Icon(
        painterResource(TravelinIcon.House),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconHeartSelected(modifier: Modifier, size: Int = 24, tint: Color =  Color(35,183,246), alpha: Float = 1f){
    Icon(
        imageVector = TravelinIcon.Heart,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconHeart(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 0.2f){
    Icon(
        imageVector = TravelinIcon.Heart,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconProfileSelected(modifier: Modifier, size: Int = 24, tint: Color =  Color(35,183,246), alpha: Float = 1f){
    Icon(
        imageVector = TravelinIcon.Account,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconProfile(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 0.2f){
    Icon(
        imageVector = TravelinIcon.Account,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}
//Icons of inputs
@Composable
fun IconEye(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        painterResource(TravelinIcon.Eye),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconSearch(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 0.5f){
    Icon(
        imageVector = TravelinIcon.Search,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconMoon(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        painterResource(TravelinIcon.Moon),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconRectangleSelect(modifier: Modifier, size: Int = 24, tint: Color = Color(255,255,255), alpha: Float = 1f){
    Icon(
        painterResource(TravelinIcon.Remove),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconRectangle(modifier: Modifier, size: Int = 24, tint: Color = Color(255,255,255), alpha : Float = 0.2f){
    Icon(
        painterResource(TravelinIcon.Remove),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}
//Icons of Detail
@Composable
fun IconQr(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        painterResource(TravelinIcon.Qr),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconTime(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        painterResource(TravelinIcon.Time),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconBus(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        painterResource(TravelinIcon.Bus),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconStar(modifier: Modifier, size: Int = 24, tint : Color =  Color(255,178,63), alpha: Float = 1f){
    Icon(
        imageVector = TravelinIcon.Star,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconStarEmpty(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha : Float = 0.2f){
    Icon(
        imageVector = TravelinIcon.Star,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

//Icons back and share
@Composable
fun IconBackArrow(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        imageVector = TravelinIcon.ArrowBack,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconBack(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        imageVector = TravelinIcon.Back,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconForward(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        imageVector = TravelinIcon.Forward,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}

@Composable
fun IconShare(modifier: Modifier, size: Int = 24, tint: Color = Color.Black, alpha: Float = 1f){
    Icon(
        imageVector = TravelinIcon.Share,
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(size.dp)
            .alpha(alpha)
    )
}



