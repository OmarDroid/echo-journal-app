package com.omaroid.echojournal.echos.presentation.echos.models

import com.omaroid.echojournal.core.presentation.util.UiText
import com.omaroid.echojournal.echos.presentation.models.EchoUi

data class EchoDaySection(
    val dateHeader: UiText,
    val echos: List<EchoUi>
)
