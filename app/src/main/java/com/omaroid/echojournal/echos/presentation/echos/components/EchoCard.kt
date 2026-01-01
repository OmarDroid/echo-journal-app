package com.omaroid.echojournal.echos.presentation.echos.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omaroid.echojournal.core.presentation.designsystem.chips.HashtagChip
import com.omaroid.echojournal.core.presentation.designsystem.theme.EchoJournalTheme
import com.omaroid.echojournal.core.presentation.util.defaultShadow
import com.omaroid.echojournal.echos.presentation.components.EchoMoodPlayer
import com.omaroid.echojournal.echos.presentation.echos.models.TrackSizeInfo
import com.omaroid.echojournal.echos.presentation.echos.preview.PreviewModels
import com.omaroid.echojournal.echos.presentation.models.EchoUi
import com.omaroid.echojournal.echos.presentation.models.MoodUi

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EchoCard(
    echoUi: EchoUi,
    onTrackSizeAvailable: (TrackSizeInfo) -> Unit,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            when (dismissValue) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onShareClick()
                    false
                }
                SwipeToDismissBoxValue.EndToStart -> {
                    onDeleteClick()
                    false
                }
                SwipeToDismissBoxValue.Settled -> false
            }
        },
        positionalThreshold = { distance -> distance * 0.4f }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            SwipeBackground(
                swipeDirection = dismissState.dismissDirection,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)
            )
        },
        enableDismissFromStartToEnd = true,
        enableDismissFromEndToStart = true,
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = modifier
                .defaultShadow(shape = RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = echoUi.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = echoUi.formattedRecodedAt,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                EchoMoodPlayer(
                    moodUi = echoUi.mood,
                    playbackState = echoUi.playbackState,
                    playerProgress = { echoUi.playbackRatio },
                    durationPlayed = echoUi.playbackCurrentDuration,
                    totalPlaybackDuration = echoUi.playbackTotalDuration,
                    powerRatios = echoUi.amplitudes,
                    onPlayClick = onPlayClick,
                    onPauseClick = onPauseClick,
                    onTrackSizeAvailable = onTrackSizeAvailable
                )

                if (!echoUi.note.isNullOrBlank()) {
                    EchoExpandableText(echoUi.note)
                }
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    echoUi.topics.forEach { topic ->
                        HashtagChip(text = topic)
                    }
                }
            }
        }
    }

}
@Preview
@Composable
private fun EchoCardPreview() {
    EchoJournalTheme {
        EchoCard(
            echoUi = PreviewModels.echoUi.copy(
                mood = MoodUi.EXCITED
            ),
            onTrackSizeAvailable = {},
            onPauseClick = {},
            onPlayClick = {},
            onDeleteClick = {},
            onShareClick = {}
        )
    }
}