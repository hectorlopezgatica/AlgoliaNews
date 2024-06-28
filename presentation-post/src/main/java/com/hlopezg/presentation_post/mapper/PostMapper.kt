package com.hlopezg.presentation_post.mapper

import com.hlopezg.domain.entity.Hit
import com.hlopezg.presentation_common.getCurrentTimeDifference
import com.hlopezg.presentation_post.model.HitModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Hit.toItemModel(): HitModel {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val result = formatter.parse(createdAt)

    return HitModel(
        author = author,
        createdAt = result.getCurrentTimeDifference(),
        storyId = storyId,
        storyTitle = storyTitle,
        storyUrl = storyUrl,
        updatedAt = updatedAt,
    )
}

fun HitModel.toItem(): Hit {
    return Hit(
        author = author,
        createdAt = createdAt,
        storyId = storyId,
        storyTitle = storyTitle,
        storyUrl = storyUrl,
        updatedAt = updatedAt,
        title = storyTitle,
    )
}

fun LocalDate.convertToDateViaInstant(): Date {
    return Date.from(
        this.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()
    )
}