package com.projectlab.core.presentation.desingsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun IconTextButton (
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colorBackground:Color = Color.White,
    colorContent:Color = Color.Black,
    disabledContainerColor: Color = Color.White,
    disabledContentColor: Color = Color.White,
    icon: @Composable ()->Unit,
    text: String,
    width: Int = 131
) {
    Button(
        onClick = onClick,
        modifier = modifier.width(width.dp),
        enabled = enabled,
        colors = ButtonColors(
            containerColor = colorBackground,
            contentColor = colorContent,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row (modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start){
            icon()
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text)
        }

    }
}

@Composable
fun ButtonHotel(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colorBackground:Color = Color.White,
    colorContent:Color = Color.Black,
    disabledContainerColor: Color = Color.White,
    disabledContentColor: Color = Color.White,
    text: String = "Hotel",
    width: Int = 131,
    iconColor: Color = Color(35,183,246)
){
    IconTextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colorBackground = colorBackground,
        colorContent = colorContent,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        icon = {IconHotel(modifier, tint = iconColor)},
        text = text,
        width = width
    )
}

@Composable
fun ButtonOversea(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colorBackground:Color = Color.White,
    colorContent:Color = Color.Black,
    disabledContainerColor: Color = Color.White,
    disabledContentColor: Color = Color.White,
    text: String = "Oversea",
    width: Int = 131,
    iconColor: Color = Color(35,183,246)
){
    IconTextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colorBackground = colorBackground,
        colorContent = colorContent,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        icon =  {IconAirplane(modifier, tint = iconColor)} ,
        text = text,
        width = width
    )
}


@Composable
fun IconButtonHeart(
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
){
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = IconToggleButtonColors(containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White,
            checkedContainerColor = Color.White,
            checkedContentColor = Color.White)
    ){
        if(!checked) {
            IconHeartBorder(modifier)
        } else {
            IconHeartSelected(modifier, tint = Color(255,0,0))
        }
    }
}

@Preview
@Composable
fun IconButtonHeartPreview(modifier: Modifier = Modifier){
    var checked by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize()
    )
    { IconButtonHeart(
        onCheckedChange =  {
            checked = it
        },
        modifier = modifier,
        checked = checked
    )

        ButtonHotel(modifier = modifier, onClick = {})
        ButtonOversea(modifier = modifier, onClick = {})
    }
}
