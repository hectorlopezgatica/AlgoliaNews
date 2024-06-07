package com.hlopezg.presentation_post.list

import androidx.lifecycle.viewModelScope
import com.hlopezg.domain.usecase.DeleteLocalHitUseCase
import com.hlopezg.domain.usecase.GetLocalHitsUseCase
import com.hlopezg.domain.usecase.GetRemoteHitsAndSaveItUseCase
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiState
import com.hlopezg.presentation_post.mapper.toItem
import com.hlopezg.presentation_post.model.HitModel
import com.hlopezg.presentation_post.model.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val remoteHitsUseCase: GetRemoteHitsAndSaveItUseCase,
    private val localHitsUseCase: GetLocalHitsUseCase,
    private val deleteLocalHitsUseCase: DeleteLocalHitUseCase,
    private val localPostConverter: LocalPostConverter,
) : MviViewModel<PostModel, UiState<PostModel>, HitListUiAction, HitListUiSingleEvent>() {
    var currentPage = 0

    override fun initState(): UiState<PostModel> = UiState.Loading
    override fun handleAction(action: HitListUiAction) {
        when (action) {
            HitListUiAction.LoadRemote -> {
                loadRemotePost()
            }

            HitListUiAction.Update -> {
                loadRemotePost()
            }

            is HitListUiAction.SingleHitClick -> {
                submitSingleEvent(
                    HitListUiSingleEvent.OpenHitDetailScreen(action.hit)
                )
            }

            is HitListUiAction.DeleteItem -> {
                deleteLocalHit(action.hit)
            }
        }
    }

    init {
        loadLocalPost()
    }

    private fun loadRemotePost() {
        viewModelScope.launch {
            remoteHitsUseCase.execute(GetRemoteHitsAndSaveItUseCase.Request).collect()
        }
    }

    private fun loadLocalPost() {
        viewModelScope.launch {
            localHitsUseCase.execute(GetLocalHitsUseCase.Request).collect {
                submitState(
                    localPostConverter.convert(it)
                )
            }
        }
    }

    private fun deleteLocalHit(hit: HitModel) {
        viewModelScope.launch {
            deleteLocalHitsUseCase.execute(DeleteLocalHitUseCase.Request(hit.toItem())).collect {
                loadLocalPost()
            }
        }
    }
}