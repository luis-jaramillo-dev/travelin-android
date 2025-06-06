package com.projectlab.travelin_android.presentation.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.presentation.screens.login.LoginViewModel

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: LoginViewModel,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        LoginHeader()

        Spacer(modifier = Modifier.height(20.dp))

        LoginForm(viewModel = viewModel)
    }
}
