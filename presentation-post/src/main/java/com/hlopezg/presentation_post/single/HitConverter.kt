package com.hlopezg.presentation_post.single

import android.content.Context
import com.hlopezg.domain.usecase.GetHitUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_post.mapper.toItemModel
import com.hlopezg.presentation_post.model.HitModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HitConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetHitUseCase.Response, HitModel>() {
    override fun convertSuccess(data: GetHitUseCase.Response) = data.hit.toItemModel()
}