package com.omaroid.echojournal.echos.presentation.echos.main_screen

import com.omaroid.echojournal.echos.domain.recording.RecordingDetails

sealed interface EchosEvent {
    data object RequestAudioPermission : EchosEvent
    data object RecordingTooShort : EchosEvent
    data class OnDoneRecording(val details: RecordingDetails) : EchosEvent
    data class ShareEcho(val audioFilePath: String, val title: String) : EchosEvent
    data class ShowError(val message: String?) : EchosEvent
}