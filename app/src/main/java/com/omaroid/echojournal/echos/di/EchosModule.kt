package com.omaroid.echojournal.echos.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.omaroid.echojournal.echos.data.audio.AndroidAudioPlayer
import com.omaroid.echojournal.echos.data.echo.RoomEchoDataSource
import com.omaroid.echojournal.echos.data.recording.AndroidVoiceRecorder
import com.omaroid.echojournal.echos.data.recording.InternalRecordingStorage
import com.omaroid.echojournal.echos.data.settings.DataStoreSettings
import com.omaroid.echojournal.echos.domain.audio.AudioPlayer
import com.omaroid.echojournal.echos.domain.echo.EchoDataSource
import com.omaroid.echojournal.echos.domain.recording.RecordingStorage
import com.omaroid.echojournal.echos.domain.recording.VoiceRecorder
import com.omaroid.echojournal.echos.domain.settings.SettingsPreferences
import com.omaroid.echojournal.echos.presentation.echos.create_echo.CreateEchoViewModel
import com.omaroid.echojournal.echos.presentation.echos.main_screen.EchosViewModel
import com.omaroid.echojournal.echos.presentation.echos.settings.SettingsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
val echoModule = module {
    singleOf(::AndroidVoiceRecorder) bind VoiceRecorder::class
    singleOf(::InternalRecordingStorage) bind RecordingStorage::class
    singleOf(::AndroidAudioPlayer) bind AudioPlayer::class
    singleOf(::RoomEchoDataSource) bind EchoDataSource::class
    singleOf(::DataStoreSettings) bind SettingsPreferences::class

    viewModelOf(::EchosViewModel)
    viewModelOf(::CreateEchoViewModel)
    viewModelOf(::SettingsViewModel)

}