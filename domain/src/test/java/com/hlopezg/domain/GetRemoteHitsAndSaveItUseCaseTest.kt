package com.hlopezg.domain

import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.entity.Posts
import com.hlopezg.domain.repository.HitRepository
import com.hlopezg.domain.usecase.GetRemoteHitsAndSaveItUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRemoteHitsAndSaveItUseCaseTest {
    private val hitRepository: HitRepository = mock<HitRepository>()
    private val useCase = GetRemoteHitsAndSaveItUseCase(
        mock(),
        hitRepository,
    )

    private val fakeHitList =
        listOf(
            Hit(
                author = "WallHabit",
                createdAt = "2024-05-27T15:26:36Z",
                storyId = 40491635,
                storyTitle = "WallHabit: Block YT Shorts, IG Reels, Videos < 2min",
                storyUrl = "https://play.google.com/store/apps/details?id=com.wallhabit.app&hl=en_US",
                updatedAt = "2024-05-27T15:27:46Z",
                title = "",
            ),
            Hit(
                author = "coldtrait",
                createdAt = "2024-05-27T14:52:26Z",
                storyId = 40488774,
                storyTitle = "Twitter is now attention roulette and ultimately meaningless",
                storyUrl = "https://playpermissionless.substack.com/p/twitter-is-now-attention-roulette",
                updatedAt = "2024-05-27T14:56:46Z",
                title = "",
            ),
        )

    private val posts = Posts(
        hitApiModels = fakeHitList,
        hitsPerPage = 20,
        page = 0,
    )

    @Test
    fun `test HitsUseCase`() = runTest {
        val request = GetRemoteHitsAndSaveItUseCase.Request

        whenever(hitRepository.getRemoteHits()).thenReturn(flowOf(posts))
        whenever(hitRepository.saveHits(fakeHitList)).thenReturn(Unit)
        val response = useCase.process(request).first()
        Assert.assertEquals(GetRemoteHitsAndSaveItUseCase.Response(posts), response)
    }
}