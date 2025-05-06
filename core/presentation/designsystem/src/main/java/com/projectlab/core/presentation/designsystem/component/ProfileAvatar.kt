package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectlab.core.presentation.designsystem.R


@Composable
fun ProfileAvatarComponent (modifier: Modifier = Modifier, profileName: String, profileCountry: String, profileCity: String){


    Row(modifier = modifier) {
        ProfilePicture()
        Spacer(modifier = Modifier.width(24.dp))
        Column(modifier = Modifier.height(68.dp), verticalArrangement = Arrangement.Center) {
            SearchPlaceText(modifier, profileName)
            Spacer(modifier.height(10.dp))
            CurrentLocationText(modifier, profileCountry, profileCity)
        }

    }

}


// The placeholder icon will be replaced by the official icon from the design system once it's ready to use
@Composable
fun ProfilePicture(modifier: Modifier = Modifier){

    Box(
        modifier = Modifier
            .size(68.dp)
            .clip(CircleShape)
    ) {
        Image(
            painter = painterResource(R.drawable.profilepictureplaceholder),
            contentDescription = "Profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

// Name comes from database
@Composable
fun ProfileName(modifier: Modifier = Modifier, name: String){
    Text(
        fontSize = 18.sp,
        fontWeight = FontWeight.W600,
        text = name
    )
}

// Location comes from database
@Composable
fun CurrentLocationText(modifier: Modifier = Modifier, country: String, city : String){
    Text(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        text = "$city, $country"
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileAvatarComponentPreview(){
    ProfileAvatarComponent(profileName = "John Doe", profileCountry = "Solar System", profileCity = "Mars")
}