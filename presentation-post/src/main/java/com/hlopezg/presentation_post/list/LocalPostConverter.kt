package com.hlopezg.presentation_post.list

import android.content.Context
import com.hlopezg.domain.usecase.GetLocalHitsUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_post.mapper.toItemModel
import com.hlopezg.presentation_post.model.PostModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalPostConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetLocalHitsUseCase.Response, PostModel>() {
    override fun convertSuccess(data: GetLocalHitsUseCase.Response): PostModel {
          return PostModel(
              page = data.post.page,
              hitApiModels = data.post.hitApiModels.map {
                  it.toItemModel()
              }.toMutableList(),
          )
    }
}