package com.omaroid.echojournal.echos.presentation.components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.omaroid.echojournal.core.presentation.designsystem.theme.EchoJournalTheme
import com.omaroid.echojournal.echos.presentation.models.MoodUi
import kotlin.random.Random

@Composable
fun EchoPlayBar(
    amplitudeBarWidth: Dp,
    amplitudeBasSpacing: Dp,
    powerRatio: List<Float>,
    trackColor: Color,
    trackFillColor: Color,
    playerProgress: () -> Float,
    modifier: Modifier = Modifier,
) {

    Canvas(
        modifier = modifier
    ) {
        val amplitudeBarWidthPx = amplitudeBarWidth.toPx()
        val amplitudeBarSpacingPx = amplitudeBasSpacing.toPx()

        val clipPath = Path()

        powerRatio.forEachIndexed { i, ratio ->
            val height = (size.height * ratio)

            val xOffset = i * (amplitudeBarWidthPx + amplitudeBarSpacingPx)
            val yTopStart = center.y - height / 2f

            val topLeft = Offset(
                x = xOffset,
                y = yTopStart
            )

            val rectSize = Size(
                width = amplitudeBarWidthPx,
                height = height
            )

            val roundRect = RoundRect(
                rect = Rect(
                    offset = topLeft,
                    size = rectSize
                ),
                cornerRadius = CornerRadius(100f)
            )
            clipPath.addRoundRect(roundRect)

            drawRoundRect(
                color = trackColor,
                topLeft = topLeft,
                size = rectSize,
                cornerRadius = CornerRadius(100f)
            )
        }

        clipPath(clipPath) {
            drawRect(
                color = trackFillColor,
                size = Size(
                    width = playerProgress() * size.width,
                    height = size.height
                )
            )
        }

    }
}

@Preview
@Composable
private fun EchoPlayBarPreview() {
    EchoJournalTheme {
        val ratios = remember {
            (1..30).map { Random.nextFloat() }
        }
        EchoPlayBar(
            amplitudeBasSpacing = 3.dp,
            amplitudeBarWidth = 4.dp,
            powerRatio = ratios,
            trackColor = MoodUi.SAD.colorSet.desaturated,
            trackFillColor = MoodUi.SAD.colorSet.vivid,
            playerProgress = {0.21f},
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}