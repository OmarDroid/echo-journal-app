package com.omaroid.echojournal.echos.presentation.echos.models

import com.omaroid.echojournal.R
import com.omaroid.echojournal.core.presentation.util.UiText

data class MoodChipContent(
    val iconsRes: List<Int> = emptyList(),
    val title: UiText = UiText.StringResource(R.string.all_moods)
)