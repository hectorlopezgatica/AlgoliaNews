package com.hlopezg.presentation_common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComponentRectangleLineShort(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.LightGray)
            .size(height = 16.dp, width = 50.dp)
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentRectangleLineLong(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color.LightGray)
            .size(height = 16.dp, width = 100.dp)
            .shimmerLoadingAnimation()
    )
}

@Composable
fun ComponentRectangleParagraphLong(modifier: Modifier = Modifier) {
    Column {
        repeat(6) {
            Box(
                modifier = modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(16.dp)
                    .shimmerLoadingAnimation()
            )
        }
    }
}

@Preview
@Composable
fun ComponentCircle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = Color.LightGray, shape = CircleShape)
            .shimmerLoadingAnimation()
            .size(60.dp)
    )
}