package com.hlopezg.presentation_post.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hlopezg.presentation_common.component.CommonScreen
import com.hlopezg.presentation_post.model.HitModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    viewModel: PostViewModel,
    navigateToHitDetail: (HitModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        viewModel.submitAction(HitListUiAction.Update)
    }
    LaunchedEffect(true) {
        viewModel.submitAction(HitListUiAction.Load)
        viewModel.singleEventFlow.collect {
            when (it) {
                is HitListUiSingleEvent.OpenHitDetailScreen -> {
                    navigateToHitDetail(it.hit)
                }
            }
        }
    }
    Box(Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
        viewModel.uiStateFlow.collectAsState().value.let { state ->
            CommonScreen(state) {
                pullToRefreshState.endRefresh()
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier,
                ) {
                    items(it.hitApiModels) { item ->
                        Column(
                            modifier = Modifier.clickable {
                                viewModel.submitAction(HitListUiAction.SingleHitClick(item))
                            }
                        ) {
                            Text(text = item.storyTitle, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(8.dp))
                            Row {
                                Text(text = item.author)
                                Text(text = " - ")
                                Text(text = item.createdAt)
                            }

                            Spacer(Modifier.height(8.dp))
                            HorizontalDivider()
                        }
                    }
                }

            }
            PullToRefreshContainer(
                state = pullToRefreshState,
            )
        }
    }
}