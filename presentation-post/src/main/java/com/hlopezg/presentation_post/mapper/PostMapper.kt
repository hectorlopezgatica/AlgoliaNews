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
    val localDateTime = LocalDateTime.parse(created_at, pattern)

    return HitModel(
        author = author,
        createdAt = localDateTime.getCurrentTimeDifference(),
        storyId = story_id,
        storyTitle = story_title,
        storyUrl = story_url,
        updatedAt = updated_at,
    )
}

fun LocalDate.convertToDateViaInstant(): Date {
    return Date.from(
        this.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()
    )
}