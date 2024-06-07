package com.hlopezg.domain

import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.repository.HitRepository
import com.hlopezg.domain.usecase.GetRemoteHitsAndSaveItUseCase
import org.mockito.kotlin.mock

class GetHitsUseCaseTest {
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

/*    @Test
    fun `test HitsUseCase`() = runTest {
        val request = GetHitsUseCase.Request

        whenever(hitRepository.getHits()).thenReturn(flowOf(fakeHitList))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetHitsUseCase.Response(fakeHitList), response)
    }*/
}