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
            commentText = "I made this app to create the ultimate free commitment device for mobile users.<p>Features:\\n- Put a &quot;Wall&quot; in front of apps and unlock it by holding a button for X seconds\\n- Selectively block Shorts and Reels\\n- Block every youtube video under 2min\\n- Routine (disable app on certain hours) feature\\n- Time limit feature",
            createdAt = "2024-05-27T15:26:36Z",
            storyId = 40491635,
            storyTitle = "WallHabit: Block YT Shorts, IG Reels, Videos < 2min",
            storyUrl = "https://play.google.com/store/apps/details?id=com.wallhabit.app&hl=en_US",
            updatedAt = "2024-05-27T15:27:46Z",
        ),
        HitEntity(
            author = "coldtrait",
            commentText = "&gt; Just Arrived extension tweaked<p>This one? <a href=\\\"https:&#x2F;&#x2F;github.com&#x2F;tecoholic&#x2F;Just-Arrived\\\">https:&#x2F;&#x2F;github.com&#x2F;tecoholic&#x2F;Just-Arrived</a><p>What about mobile? I find myself scrolling on my phone a lot and I use a modded app because Android, thankfully. I feel like the best solution is to just delete the app, ultimately.",
            createdAt = "2024-05-27T14:52:26Z",
            storyId = 40488774,
            storyTitle = "Twitter is now attention roulette and ultimately meaningless",
            storyUrl = "https://playpermissionless.substack.com/p/twitter-is-now-attention-roulette",
            updatedAt = "2024-05-27T14:56:46Z",
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