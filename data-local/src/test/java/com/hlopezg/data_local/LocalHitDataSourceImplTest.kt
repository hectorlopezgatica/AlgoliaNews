package com.hlopezg.data_local

import com.hlopezg.data_local.hit.HitDao
import com.hlopezg.data_local.hit.HitEntity
import com.hlopezg.data_local.mapper.toDomain
import com.hlopezg.data_local.source.LocalHitDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

class LocalHitDataSourceImplTest {
    private val hitDao = Mockito.mock<HitDao>()
    private val movieDataSource = LocalHitDataSourceImpl(hitDao)

    val localHits = listOf(
        HitEntity(
            author = "WallHabit",
            createdAt = "2024-05-27T15:26:36Z",
            storyId = 40491635,
            storyTitle = "WallHabit: Block YT Shorts, IG Reels, Videos < 2min",
            storyUrl = "https://play.google.com/store/apps/details?id=com.wallhabit.app&hl=en_US",
            updatedAt = "2024-05-27T15:27:46Z",
            title = "",
        ),
        HitEntity(
            author = "coldtrait",
            createdAt = "2024-05-27T14:52:26Z",
            storyId = 40488774,
            storyTitle = "Twitter is now attention roulette and ultimately meaningless",
            storyUrl = "https://playpermissionless.substack.com/p/twitter-is-now-attention-roulette",
            updatedAt = "2024-05-27T14:56:46Z",
            title = "",
        )
    )

    @ExperimentalCoroutinesApi
    @Test
    fun testGetHits() = runTest {
        val expectedHits = localHits.map { it.toDomain() }
        whenever(hitDao.getAll()).thenReturn(flowOf(localHits))
        val result = movieDataSource.getHits().first()
        Assert.assertEquals(expectedHits, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetHit() = runTest {
        val expectedHit = localHits.first().toDomain()
        whenever(hitDao.getById(40491635)).thenReturn(flowOf(localHits.first()))
        val result = movieDataSource.getHit(40491635).first()
        Assert.assertEquals(expectedHit, result)
    }
}