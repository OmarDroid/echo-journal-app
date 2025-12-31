package com.omaroid.echojournal.echos.presentation.models

import com.omaroid.echojournal.echos.presentation.echos.models.PlaybackState
import com.omaroid.echojournal.echos.presentation.util.toReadableTime
import java.time.Instant
import kotlin.time.Duration

data class EchoUi(
    val id: Int,
    val title: String,
    val mood: MoodUi,
    val recordedAt: Instant,
    val note: String?,
    val topics: List<String>,
    val amplitudes: List<Float>,
    val playbackTotalDuration: Duration,
    val audioFilePath: String,
    val playbackCurrentDuration: Duration = Duration.ZERO,
    val playbackState: PlaybackState = PlaybackState.STOPPED,
) {
    val formattedRecodedAt = recordedAt.toReadableTime()
    val playbackRatio = (playbackCurrentDuration / playbackTotalDuration).toFloat()
}
