package com.hlopezg.domain

import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.repository.HitRepository
import com.hlopezg.domain.usecase.GetHitsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetHitsUseCaseTest {
    private val hitRepository: HitRepository = mock<HitRepository>()
    private val useCase = GetHitsUseCase(
        mock(),
        hitRepository,
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

/*    @Test
    fun `test HitsUseCase`() = runTest {
        val request = GetHitsUseCase.Request

        whenever(hitRepository.getHits()).thenReturn(flowOf(fakeHitList))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetHitsUseCase.Response(fakeHitList), response)
    }*/
}