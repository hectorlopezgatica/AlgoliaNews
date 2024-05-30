package com.hlopezg.presentation_post.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hlopezg.presentation_common.component.CommonScreen
import com.hlopezg.presentation_common.component.ErrorComponent
import com.hlopezg.presentation_common.component.LoadingComponentList

@Composable
fun PostScreen(
    viewModel: PostViewModel,
    navigateToHitDetail: (HitModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(HitListUiAction.Load)
        viewModel.singleEventFlow.collect {
            when (it) {
                is HitListUiSingleEvent.OpenHitDetailScreen -> {
                    navigateToHitDetail(it.hit)
                }
            }
        }
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state) {
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
    }
}