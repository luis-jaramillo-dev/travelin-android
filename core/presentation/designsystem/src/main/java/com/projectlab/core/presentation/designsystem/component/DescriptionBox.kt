package com.projectlab.core.presentation.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun DescriptionBox(
    modifier: Modifier = Modifier,
    activity: ActivityDto
) {

    var expanded by remember { mutableStateOf(false) }
    var isTextOverflow by remember { mutableStateOf(false) }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    val cleanDescription = if(activity.description.isEmpty()) { stringResource(R.string.no_description) } else activity.description
        .replace("<br><br>", "\n\n")
        .replace("<br>", "\n")
        .replace("\\n", "\n")
        .replace("\\n\\n", "\n\n")

    LaunchedEffect(textLayoutResult) {
        textLayoutResult?.let {
            isTextOverflow = it.lineCount > 6
        }
    }

    Column(
        modifier = modifier
            .padding(MaterialTheme.spacing.semiLarge)
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )

        Spacer(Modifier.height(MaterialTheme.spacing.SectionSpacing))

        Text(
            text = cleanDescription,
            maxLines = if (expanded) Int.MAX_VALUE else 6,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = cleanDescription,
            onTextLayout = { textLayoutResult = it },
            maxLines = Int.MAX_VALUE,
            modifier = Modifier.alpha(0f).padding(MaterialTheme.spacing.none).height(MaterialTheme.spacing.none)
        )

        if (isTextOverflow) {
            Text(
                text = if (expanded) stringResource(R.string.read_less) else stringResource(R.string.read_more),
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.extraSmall)
                    .clickable { expanded = !expanded },
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textDecoration = TextDecoration.Underline
            )
        }

    }
}