package com.hlopezg.presentation_post.list

import com.hlopezg.presentation_common.state.UiAction

sealed class HitListUiAction : UiAction {
    data object Load : HitListUiAction()
    data class SingleHitClick(val hit: HitModel) : HitListUiAction()
}