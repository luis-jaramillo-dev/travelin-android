package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun HeaderWithBack(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    title: String = ""
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackIconButton(
                onClick = { onClickBack() },
                modifier = Modifier
                    .size(MaterialTheme.spacing.extraLarge2)
                    .padding(MaterialTheme.spacing.extraSmall)
            )
            Spacer(modifier = Modifier.width(12.dp))
            SubTitle(text = title)
        }
    }
}