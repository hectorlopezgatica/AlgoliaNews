package com.hlopezg.presentation_post.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
        viewModel.submitAction(HitListUiAction.LoadRemote)
        viewModel.singleEventFlow.collect {
            when (it) {
                is HitListUiSingleEvent.OpenHitDetailScreen -> {
                    navigateToHitDetail(it.hit)
                }
            }
        }
    }

    viewModel.uiStateFlow.collectAsStateWithLifecycle().value.let { state ->
        Box(Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
            CommonScreen(state) {
                pullToRefreshState.endRefresh()
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier,
                ) {
                    itemsIndexed(it.hitApiModels) { index, item ->
                        val value = rememberSwipeToDismissBoxState(
                            confirmValueChange = { swipeValue ->
                                if (swipeValue == SwipeToDismissBoxValue.EndToStart) {
                                    viewModel.submitAction(HitListUiAction.DeleteItem(item))
                                    false
                                } else {
                                    true
                                }
                            }
                        )

                        SwipeToDismissBox(
                            state = value,
                            enableDismissFromStartToEnd = false,
                            backgroundContent = {
                                val color = when (value.dismissDirection) {
                                    SwipeToDismissBoxValue.StartToEnd -> Color.Transparent
                                    SwipeToDismissBoxValue.EndToStart -> Color.Red
                                    SwipeToDismissBoxValue.Settled -> Color.Transparent
                                }
                                DismissBoxContent(color)
                            }) {
                            HitRow(item = item) {
                                viewModel.submitAction(HitListUiAction.SingleHitClick(item))
                            }
                        }
                    }
                }
                PullToRefreshContainer(
                    modifier = Modifier.align(Alignment.TopCenter),
                    state = pullToRefreshState,
                )
            }
        }
    }
}

@Composable
fun HitRow(
    item: HitModel,
    onSingleHitClick: (HitModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable {
                onSingleHitClick(item)

            }
            .background(MaterialTheme.colorScheme.background)
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

@Composable
fun DismissBoxContent(backGroundColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundColor)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(8.dp)
                .size(32.dp),
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete"
        )
    }
}