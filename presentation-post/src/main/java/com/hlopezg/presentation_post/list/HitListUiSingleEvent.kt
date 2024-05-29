package com.hlopezg.presentation_post.list

import com.hlopezg.presentation_common.state.UiSingleEvent

sealed class HitListUiSingleEvent : UiSingleEvent {
    data class OpenHitDetailScreen(val hit: HitModel) : HitListUiSingleEvent()
}