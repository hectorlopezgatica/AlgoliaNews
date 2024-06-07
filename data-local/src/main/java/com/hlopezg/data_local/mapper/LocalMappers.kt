package com.hlopezg.data_local.mapper

import com.hlopezg.data_local.hit.HitEntity
import com.hlopezg.domain.entity.Hit

fun HitEntity.toDomain() =
    Hit(
        author = this.author,
        createdAt = this.createdAt,
        storyId = this.storyId,
        storyTitle = this.storyTitle,
        storyUrl = this.storyUrl,
        updatedAt = this.updatedAt,
        title = this.title,
    )

fun Hit.toEntity() =
    HitEntity(
        author = this.author,
        createdAt = this.createdAt,
        storyId = this.storyId,
        storyTitle = this.storyTitle,
        storyUrl = this.storyUrl,
        updatedAt = this.updatedAt,
        title = this.title,
    )