package com.hlopezg.presentation_post.list

import com.hlopezg.presentation_common.state.UiAction
import com.hlopezg.presentation_post.model.HitModel

sealed class HitListUiAction : UiAction {
    data object LoadRemote : HitListUiAction()
    data object Update : HitListUiAction()
    data class DeleteItem(val hit: HitModel) : HitListUiAction()
    data class SingleHitClick(val hit: HitModel) : HitListUiAction()
}