package com.hlopezg.presentation_post.single

import com.hlopezg.presentation_common.state.UiAction

sealed class HitUiAction: UiAction {
    data class Load(val hitId: Int) : HitUiAction()
}