package com.hlopezg.presentation_post.mapper

import com.hlopezg.domain.entity.Hit
import com.hlopezg.presentation_common.getCurrentTimeDifference
import com.hlopezg.presentation_post.model.HitModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date


fun Hit.toItemModel(): HitModel {
    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val localDateTime = LocalDateTime.parse(createdAt, pattern)

    return HitModel(
        author = author,
        createdAt = localDateTime.getCurrentTimeDifference(),
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