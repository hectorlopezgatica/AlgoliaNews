package com.hlopezg.presentation_post.single

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.hlopezg.presentation_common.component.CommonScreen

@Composable
fun HitScreen(
    hitId: Int,
    viewModel: HitViewModel,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(HitUiAction.Load(hitId))
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state) { hitModel ->
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl(hitModel.storyUrl)
                }
            }, update = {
                it.loadUrl(hitModel.storyUrl)
            })
        }
    }
}