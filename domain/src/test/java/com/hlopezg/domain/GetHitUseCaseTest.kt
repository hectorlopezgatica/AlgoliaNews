package com.hlopezg.domain

import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.repository.HitRepository
import com.hlopezg.domain.usecase.GetHitUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetHitUseCaseTest {
    private val hitRepository: HitRepository = mock<HitRepository>()
    private val useCase = GetHitUseCase(
        mock(),
        hitRepository,
    )

    private val fakeHit = Hit(
        author = "WallHabit",
        createdAt = "2024-05-27T15:26:36Z",
        storyId = 40491635,
        storyTitle = "WallHabit: Block YT Shorts, IG Reels, Videos < 2min",
        storyUrl = "https://play.google.com/store/apps/details?id=com.wallhabit.app&hl=en_US",
        updatedAt = "2024-05-27T15:27:46Z",
        title = "",
    )

    @Test
    fun `test hit UseCase`() = runTest {
        val request = GetHitUseCase.Request(40491635)

        whenever(hitRepository.getHit(40491635)).thenReturn(flowOf(fakeHit))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetHitUseCase.Response(fakeHit), response)
    }
}