package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties


@Composable
fun TravelinTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    border: Int = 1,
    alpha: Float = 0.1f,
    borderColor: Color = MaterialTheme.colorScheme.scrim.copy(alpha = alpha),
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    height: Int = 51,
    keyboardType: KeyboardType = KeyboardType.Text,
    textColor: Color = MaterialTheme.colorScheme.scrim,
    iconStart: @Composable() (() -> Unit)? = null,
    iconEnd: @Composable() (() -> Unit)? = null,
    onClick : () -> Unit = {},
    iconColor: Color = MaterialTheme.colorScheme.scrim
){
    BasicTextField(
        state = state,
        textStyle = MaterialTheme.typography.titleMedium.copy(color = textColor),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = marginStart.dp, end = marginEnd.dp, top = marginTop.dp, bottom = marginBottom.dp)
            .border(border.dp, borderColor, RoundedCornerShape(15.dp), )
            .height(height.dp)
            .padding(6.dp),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (iconStart != null) {
                    IconButton(
                        onClick = onClick,
                        colors = IconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = iconColor,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = iconColor.copy(alpha = 0.3f)
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    ) {
                        iconStart()
                    }
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = (if(iconStart == null){16}else{48}).dp, end = (if(iconEnd == null){16}else{48}).dp)
                ) {
                    innerTextField()
                }
                if (iconEnd != null) {
                    IconButton(
                        onClick = onClick,
                        colors = IconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = iconColor,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = iconColor.copy(alpha = 0.3f)
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    ) {
                        iconEnd()
                    }
                }
            }
        }
    )
}

@Composable
fun TravelinTextFieldColumn(
    modifier: Modifier = Modifier,
    title: String? = null,
    state: TextFieldState,
    items : List<String>? = null,
    selectedItem: MutableIntState  = mutableIntStateOf(0),
    error: String? = null,
    border: Int = 1,
    alpha: Float = 0.1f,
    borderColor: Color = MaterialTheme.colorScheme.scrim.copy(alpha = alpha),
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    height: Int = 84,
    keyboardType: KeyboardType = KeyboardType.Text,
    textColor: Color = MaterialTheme.colorScheme.scrim,
    iconStart: @Composable() (() -> Unit)? = null,
    iconEnd: @Composable() (() -> Unit)? = null,
    onClick : () -> Unit = {},
    iconColor: Color = MaterialTheme.colorScheme.scrim
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(top = marginTop.dp, bottom = marginBottom.dp, start = marginStart.dp, end = marginEnd.dp)
            .height(height.dp),
        verticalArrangement = Arrangement.Center
    ){
        if (title != null){
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                color = textColor,
                modifier = modifier.padding(bottom = 2.dp)
            )
        }
        Row(
            modifier = modifier.fillMaxWidth()
        ){
            if (items != null){
                TravelinDropdownMenu(
                    modifier = modifier
                        .width(85.dp)
                        .padding(end = 5.dp),
                    items = items,
                    selectedItem = selectedItem
                )
            }
            TravelinTextField(
                modifier= modifier,
                border = border,
                alpha = alpha,
                borderColor = (
                        if (error == null) {
                            borderColor
                        } else {
                            MaterialTheme.colorScheme.error
                        }),
                state= state,
                keyboardType = keyboardType,
                textColor = textColor,
                iconColor= iconColor
            )
        }

        if (error != null){
            Text(
                text = error,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                color = MaterialTheme.colorScheme.error,
                modifier = modifier.padding(top = 0.dp)
            )
        }
    }
}

//No expande
@Composable
fun TravelinDropdownMenu(
    modifier: Modifier = Modifier,
    items : List<String>,
    selectedItem: MutableIntState  = mutableIntStateOf(0),
    border: Int = 1,
    alpha: Float = 0.1f,
    borderColor: Color = MaterialTheme.colorScheme.scrim.copy(alpha = alpha),
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    height: Int = 51,
    contentColor: Color = MaterialTheme.colorScheme.scrim
){
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = marginStart.dp, end = marginEnd.dp, top = marginTop.dp, bottom = marginBottom.dp)
            .border(border.dp, borderColor, RoundedCornerShape(15.dp), )
            .height(height.dp)
            .padding(6.dp),
    ){
        Row(
            modifier = modifier
                .fillMaxSize()
                .clickable(onClick = { expanded = true })
                ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = items.get(selectedItem.value),
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
            IconExpand(modifier = modifier, tint = contentColor, alpha = 1f)
        }
        DropdownMenu(
            modifier = modifier,
            expanded = expanded,
            offset = DpOffset(0.dp, 0.dp),
            properties = PopupProperties(focusable = true),
            onDismissRequest = { expanded = false }
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        selectedItem.value = index
                        expanded = false
                    }
                )
            }
        }
    }
}