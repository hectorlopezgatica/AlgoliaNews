package com.hlopezg.data_local.mapper

import com.hlopezg.data_local.hit.HitEntity
import com.hlopezg.domain.entity.Hit

fun HitEntity.toDomain() =
    Hit(
        author = this.author,
        created_at = this.createdAt,
        story_id = this.storyId,
        story_title = this.storyTitle,
        story_url = this.storyUrl,
        updated_at = this.updatedAt,
        title = this.title,
    )

fun Hit.toEntity() =
    HitEntity(
        author = this.author,
        createdAt = this.created_at,
        storyId = this.story_id,
        storyTitle = this.story_title,
        storyUrl = this.story_url,
        updatedAt = this.updated_at,
        title = this.title,
    )