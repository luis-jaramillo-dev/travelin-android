package com.projectlab.booking.presentation.booking.hotels.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.projectlab.booking.presentation.booking.hotels.BookingHotelState
import com.projectlab.core.presentation.designsystem.component.ForwardIconButton
import com.projectlab.core.presentation.designsystem.component.TravelinDatePickerBottomSheet
import com.projectlab.core.presentation.designsystem.theme.spacing
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingHotelContent(
    modifier: Modifier = Modifier,
    state: BookingHotelState
) {

    val checkIn: MutableState<LocalDate?> = remember { mutableStateOf(null) }
    val checkOut: MutableState<LocalDate?> = remember { mutableStateOf(null) }

    val startDate = LocalDate.now().plusDays(1)
    val endDate = startDate.plusDays(90)

    val dateRange: ClosedRange<LocalDate> = startDate..endDate
    var openDatePicker by remember { mutableStateOf(false) }

    if (openDatePicker) {
        TravelinDatePickerBottomSheet(
            onDismiss = { openDatePicker = false },
            onConfirm = {
                openDatePicker = false
                checkIn.value =
                    Instant.ofEpochMilli(it.first!!).atZone(ZoneId.of("UTC")).toLocalDate()

                checkOut.value =
                    Instant.ofEpochMilli(it.second ?: (it.first!! + 86400000))
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDate()
            },
            titleText = "Select Dates",
            availableDateRange = dateRange
        )
    }


    Column(modifier = modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        HotelCardBooking(hotel = state.currentHotel!!)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.BigSpacing))

        Column {
            Text(
                text = "DATES",
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { openDatePicker = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Check-In ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )

                checkIn.value?.let {
                    Text(
                        text = "${checkIn.value!!.dayOfMonth} ${checkIn.value!!.month} ${checkIn.value!!.year}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                    )
                }

                ForwardIconButton(onClick = { openDatePicker = true })

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { openDatePicker = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Check-Out",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )

                checkOut.value?.let {
                    Text(
                        text = "${checkOut.value!!.dayOfMonth} ${checkOut.value!!.month} ${checkOut.value!!.year}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                    )
                }
                ForwardIconButton(onClick = { openDatePicker = true })
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.BigSpacing))

        Column {
            Text(
                text = "GUEST INFO",
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { openDatePicker = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = state.guestName,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W600,
                )
                ForwardIconButton(onClick = { })


            }
            state.idNumber?.let {
                Text(
                    text = "ID number: ${state.idNumber!!}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )
            }
            state.guestNumber?.let {
                Text(
                    text = "Phone number: ${state.countryCode} ${state.guestNumber!!} ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )
            }
            state.email?.let {
                Text(
                    text = "Email: ${state.email!!} ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )
            }
        }
    }
}