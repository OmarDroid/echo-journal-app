package com.omaroid.echojournal.echos.presentation.echos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omaroid.echojournal.R
import com.omaroid.echojournal.core.presentation.designsystem.theme.EchoJournalTheme

@Composable
fun SwipeBackground(
    swipeDirection: SwipeToDismissBoxValue,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (swipeDirection) {
        SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
        SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.errorContainer
        SwipeToDismissBoxValue.Settled -> MaterialTheme.colorScheme.surface.copy(alpha = 0f)
    }

    val contentColor = when (swipeDirection) {
        SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.onPrimaryContainer
        SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.onErrorContainer
        SwipeToDismissBoxValue.Settled -> MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 20.dp),
        contentAlignment = when (swipeDirection) {
            SwipeToDismissBoxValue.StartToEnd -> Alignment.CenterStart
            SwipeToDismissBoxValue.EndToStart -> Alignment.CenterEnd
            SwipeToDismissBoxValue.Settled -> Alignment.Center
        }
    ) {
        when (swipeDirection) {
            SwipeToDismissBoxValue.StartToEnd -> {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(R.string.share_echo),
                        tint = contentColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            SwipeToDismissBoxValue.EndToStart -> {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete_echo),
                    tint = contentColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            SwipeToDismissBoxValue.Settled -> {
                // No Swipe - Do nothing.
            }
        }

    }

}

@Preview
@Composable
private fun SwipeBackgroundSharePreview() {
    EchoJournalTheme {
        SwipeBackground(
            swipeDirection = SwipeToDismissBoxValue.StartToEnd,
        )
    }
}
@Preview
@Composable
private fun SwipeBackgroundDeletePreview() {
    EchoJournalTheme {
        SwipeBackground(
            swipeDirection = SwipeToDismissBoxValue.EndToStart,
        )
    }
}