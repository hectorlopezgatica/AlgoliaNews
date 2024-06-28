package com.hlopezg.presentation_post.list

import android.content.Context
import com.hlopezg.domain.usecase.DeleteLocalHitUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_post.model.HitModel
import com.hlopezg.presentation_post.model.PostModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DeleteHitConverter @Inject constructor() :
    CommonResultConverter<DeleteLocalHitUseCase.Response, PostModel>() {
    override fun convertSuccess(data: DeleteLocalHitUseCase.Response): PostModel {
        return PostModel(
            page = 0,
            hitApiModels = mutableListOf(),
        )
    }
}