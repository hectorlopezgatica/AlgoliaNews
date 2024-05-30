package com.hlopezg.data_remote

import com.hlopezg.data_remote.networking.HitApiModel
import com.hlopezg.data_remote.networking.PostsApiModel
import com.hlopezg.data_remote.networking.service.HitService
import com.hlopezg.data_remote.source.RemoteHitDataSourceImpl
import com.hlopezg.data_repository.data_source.local.LocalHitDataSource
import com.hlopezg.domain.entity.Hit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class RemoteHitDataSourceImplTest {
    private val hitService = mock<HitService>()
    private val localHitDataSource = mock<LocalHitDataSource>()
    private val remoteHitDataSource = RemoteHitDataSourceImpl(hitService)

    private val remoteHits = listOf(
        HitApiModel(
            author = "WallHabit",
            created_at = "2024-05-27T15:26:36Z",
            story_id = 40491635,
            story_title = "WallHabit: Block YT Shorts, IG Reels, Videos < 2min",
            story_url = "https://play.google.com/store/apps/details?id=com.wallhabit.app&hl=en_US",
            updated_at = "2024-05-27T15:27:46Z",
            title = "",
        ),
        HitApiModel(
            author = "coldtrait",
            created_at = "2024-05-27T14:52:26Z",
            story_id = 40488774,
            story_title = "Twitter is now attention roulette and ultimately meaningless",
            story_url = "https://playpermissionless.substack.com/p/twitter-is-now-attention-roulette",
            updated_at = "2024-05-27T14:56:46Z",
            title = "",
        )
    )

    private val expectedHits = listOf(
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
    fun testFetchHits() = runTest {
        val postApiModel = PostsApiModel(remoteHits, hitsPerPage = 20, page = 1)
        whenever(hitService.getPosts()).thenReturn(postApiModel)
        val result = remoteHitDataSource.getHits().first()
        assert(result == expectedHits)
    }
}