package com.hlopezg.presentation_common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun PreviewLoadingComponentList() {
    LoadingComponentList()
}

@Composable
fun LoadingComponentList() {
    Column {
        ComponentRectangleLineShort()
        Spacer(modifier = Modifier.size(8.dp))
        ComponentRectangleLineLong()
    }
}