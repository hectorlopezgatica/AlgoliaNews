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
            comment_text = "I made this app to create the ultimate free commitment device for mobile users.<p>Features:\\n- Put a &quot;Wall&quot; in front of apps and unlock it by holding a button for X seconds\\n- Selectively block Shorts and Reels\\n- Block every youtube video under 2min\\n- Routine (disable app on certain hours) feature\\n- Time limit feature",
            created_at = "2024-05-27T15:26:36Z",
            story_id = 40491635,
            story_title = "WallHabit: Block YT Shorts, IG Reels, Videos < 2min",
            story_url = "https://play.google.com/store/apps/details?id=com.wallhabit.app&hl=en_US",
            updated_at = "2024-05-27T15:27:46Z",
        ),
        HitApiModel(
            author = "coldtrait",
            comment_text = "&gt; Just Arrived extension tweaked<p>This one? <a href=\\\"https:&#x2F;&#x2F;github.com&#x2F;tecoholic&#x2F;Just-Arrived\\\">https:&#x2F;&#x2F;github.com&#x2F;tecoholic&#x2F;Just-Arrived</a><p>What about mobile? I find myself scrolling on my phone a lot and I use a modded app because Android, thankfully. I feel like the best solution is to just delete the app, ultimately.",
            created_at = "2024-05-27T14:52:26Z",
            story_id = 40488774,
            story_title = "Twitter is now attention roulette and ultimately meaningless",
            story_url = "https://playpermissionless.substack.com/p/twitter-is-now-attention-roulette",
            updated_at = "2024-05-27T14:56:46Z",
        )
    )

    private val expectedHits = listOf(
        Hit(
            author = "WallHabit",
            comment_text = "I made this app to create the ultimate free commitment device for mobile users.<p>Features:\\n- Put a &quot;Wall&quot; in front of apps and unlock it by holding a button for X seconds\\n- Selectively block Shorts and Reels\\n- Block every youtube video under 2min\\n- Routine (disable app on certain hours) feature\\n- Time limit feature",
            created_at = "2024-05-27T15:26:36Z",
            story_id = 40491635,
            story_title = "WallHabit: Block YT Shorts, IG Reels, Videos < 2min",
            story_url = "https://play.google.com/store/apps/details?id=com.wallhabit.app&hl=en_US",
            updated_at = "2024-05-27T15:27:46Z",
        ),
        Hit(
            author = "coldtrait",
            comment_text = "&gt; Just Arrived extension tweaked<p>This one? <a href=\\\"https:&#x2F;&#x2F;github.com&#x2F;tecoholic&#x2F;Just-Arrived\\\">https:&#x2F;&#x2F;github.com&#x2F;tecoholic&#x2F;Just-Arrived</a><p>What about mobile? I find myself scrolling on my phone a lot and I use a modded app because Android, thankfully. I feel like the best solution is to just delete the app, ultimately.",
            created_at = "2024-05-27T14:52:26Z",
            story_id = 40488774,
            story_title = "Twitter is now attention roulette and ultimately meaningless",
            story_url = "https://playpermissionless.substack.com/p/twitter-is-now-attention-roulette",
            updated_at = "2024-05-27T14:56:46Z",
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