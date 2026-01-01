package com.omaroid.echojournal.echos.presentation.echos.main_screen

import com.omaroid.echojournal.R
import com.omaroid.echojournal.core.presentation.designsystem.dropdowns.Selectable
import com.omaroid.echojournal.core.presentation.util.UiText
import com.omaroid.echojournal.echos.presentation.echos.models.AudioCaptureMethod
import com.omaroid.echojournal.echos.presentation.echos.models.EchoDaySection
import com.omaroid.echojournal.echos.presentation.echos.models.EchoFilterChip
import com.omaroid.echojournal.echos.presentation.echos.models.MoodChipContent
import com.omaroid.echojournal.echos.presentation.echos.models.RecordingState
import com.omaroid.echojournal.echos.presentation.models.EchoUi
import com.omaroid.echojournal.echos.presentation.models.MoodUi
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.time.Duration

data class EchosState(
    val echos: Map<UiText, List<EchoUi>> = emptyMap(),
    val currentCaptureMethod: AudioCaptureMethod? = null,
    val recordingElapsedDuration: Duration = Duration.ZERO,
    val hasEchosRecorded: Boolean = false,
    val hasActiveTopicFilters: Boolean = false,
    val hasActiveMoodFilters: Boolean = false,
    val isLoadingData: Boolean = true,
    val recordingState: RecordingState = RecordingState.NOT_RECORDING,
    val moods: List<Selectable<MoodUi>> = emptyList(),
    val topics: List<Selectable<String>> = emptyList(),
    val moodChipContent: MoodChipContent = MoodChipContent(),
    val selectedEchoFilterChip: EchoFilterChip? = null,
    val topicChipTitle: UiText = UiText.StringResource(R.string.all_topics),
    val echoToDelete: Int? = null
) {
    val echoDaySections = echos
        .toList()
        .map { (dateHeader, echos) ->
            EchoDaySection(dateHeader, echos)
        }

    val formattedRecordDuration: String
        get() {
            val minutes = (recordingElapsedDuration.inWholeMinutes % 60).toInt()
            val seconds = (recordingElapsedDuration.inWholeSeconds % 60).toInt()
            val centiseconds = ((recordingElapsedDuration.inWholeMilliseconds % 1000) / 10.0).roundToInt()

            return String.format(
                locale = Locale.US,
                format = "%02d:%02d:%02d",
                minutes, seconds, centiseconds
            )
        }
}