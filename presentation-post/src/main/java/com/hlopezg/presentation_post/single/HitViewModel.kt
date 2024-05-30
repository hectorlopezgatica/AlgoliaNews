package com.hlopezg.presentation_post.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.usecase.GetHitUseCase
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiState
import com.hlopezg.presentation_post.model.HitModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HitViewModel  @Inject constructor(
    private val hitUseCase: GetHitUseCase,
    private val hitConverter: HitConverter,
) : MviViewModel<HitModel, UiState<HitModel>, HitUiAction, HitUiSingleEvent>(){
    override fun initState(): UiState<HitModel> = UiState.Loading

    override fun handleAction(action: HitUiAction) {
        when(action){
            is HitUiAction.Load -> {
                loadHit(action.hitId)
            }
        }
    }

    private fun loadHit(hitId: Int){
        viewModelScope.launch {
            hitUseCase.execute(GetHitUseCase.Request(hitId)).map {
                hitConverter.convert(it)
            }.collect{
                submitState(it)
            }
        }
    }
}