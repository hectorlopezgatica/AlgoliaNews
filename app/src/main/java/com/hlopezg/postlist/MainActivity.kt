package com.hlopezg.postlist

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.hlopezg.postlist.ui.theme.PostListTheme
import com.hlopezg.presentation_post.list.PostScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostListTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.ScreenPosts) {
                        composable<Routes.ScreenPosts> {
                            PostScreen(
                                viewModel = hiltViewModel(),
                                navigateToHitDetail = {
                                    navController.navigate(Routes.ScreenHit(it.storyId))
                                },
                                modifier = Modifier.padding(innerPadding),
                            )
                        }
                        composable<Routes.ScreenHit> { it ->
                            val args = it.toRoute<Routes.ScreenHit>()
                            AndroidView(factory = {
                                WebView(it).apply {
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    webViewClient = WebViewClient()
                                    loadUrl("www.google.cl")
                                }
                            }, update = {
                                it.loadUrl("www.google.cl")
                            })
                        }
                    }
                }
            }
        }
    }
}

@Serializable
sealed class Routes{
    @Serializable
    data object ScreenPosts: Routes()
    @Serializable
    data class ScreenHit(val id: Int): Routes()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PostListTheme {
    }
}