package com.projectlab.travelin_android.presentation.screens.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterContent(
    paddingValues: PaddingValues
) {

    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            RegisterHeader()
            Spacer(modifier = Modifier.height(20.dp))
            RegisterForm()
            Spacer(modifier = Modifier.height(8.dp))

            // esta comentado por el viewmodel, se debe descomentar al implementar el viewmodel
            /*ButtonSimple(
                text = "Create Account",
                onClick = { },
                enabled = viewModel.isFormValid.value
            )*/
        }
    }
}





