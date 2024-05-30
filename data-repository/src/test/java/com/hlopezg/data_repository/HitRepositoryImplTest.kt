package com.hlopezg.data_repository

import com.hlopezg.data_repository.data_source.local.LocalHitDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteHitDataSource
import com.hlopezg.data_repository.repository.HitRepositoryImpl
import com.hlopezg.domain.entity.Hit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class HitRepositoryImplTest {
    private val localHitDataSource = mock<LocalHitDataSource>()
    private val remoteHitDataSource = mock<RemoteHitDataSource>()
    private val hitRepository = HitRepositoryImpl(
        localDataSource = localHitDataSource,
        remoteDataSource = remoteHitDataSource
    )

    private val fakeHitList =
        listOf(
            Hit(
                author = "WallHabit",
                created_at = "2024-05-27T15:26:36Z",
                story_id = 40491635,
                story_title = "WallHabit: Block YT Shorts, IG Reels, Videos < 2min",
                story_url = "https://play.google.com/store/apps/details?id=com.wallhabit.app&hl=en_US",
                updated_at = "2024-05-27T15:27:46Z",
                title = "",
            ),
            Hit(
                author = "coldtrait",
                created_at = "2024-05-27T14:52:26Z",
                story_id = 40488774,
                story_title = "Twitter is now attention roulette and ultimately meaningless",
                story_url = "https://playpermissionless.substack.com/p/twitter-is-now-attention-roulette",
                updated_at = "2024-05-27T14:56:46Z",
                title = "",
            ),
        )

    @Test
    fun `test get Hits`() = runTest {
        whenever(hitRepository.getHits()).thenReturn(flowOf(fakeHitList))
        val result = hitRepository.getHits().first()
        Assert.assertEquals(result, fakeHitList)
    }

    @Test
    fun `test get Hit`() = runTest {
        whenever(hitRepository.getHit(40491635)).thenReturn(flowOf(fakeHitList.first()))
        val result = hitRepository.getHit(40491635).first()
        Assert.assertEquals(result, fakeHitList.first())
    }

    @Test
    fun `test save Hit`() = runTest {
        whenever(hitRepository.saveHits(fakeHitList)).thenReturn(flowOf(fakeHitList))
        val result = hitRepository.saveHits(fakeHitList).first()
        Assert.assertEquals(result, fakeHitList)
    }
}