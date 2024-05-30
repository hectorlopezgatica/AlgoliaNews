package com.hlopezg.presentation_post.list

import android.content.Context
import com.hlopezg.domain.usecase.GetHitsUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_post.mapper.toItemModel
import com.hlopezg.presentation_post.model.PostModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PostConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetHitsUseCase.Response, PostModel>() {
    override fun convertSuccess(data: GetHitsUseCase.Response): PostModel {
          return PostModel(
              page = data.post.page,
              hitApiModels = data.post.hitApiModels.map {
                  it.toItemModel()
              },
              hitsPerPage = data.post.hitsPerPage,
          )
    }
}