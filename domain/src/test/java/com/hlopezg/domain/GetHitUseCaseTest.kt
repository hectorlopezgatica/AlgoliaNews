package com.hlopezg.domain

import com.hlopezg.domain.entity.Hit
import com.hlopezg.domain.repository.HitRepository
import com.hlopezg.domain.usecase.GetHitUseCase
import com.hlopezg.domain.usecase.GetHitsUseCase
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

    val fakeHit = Hit(
        author = "WallHabit",
        comment_text = "I made this app to create the ultimate free commitment device for mobile users.<p>Features:\\n- Put a &quot;Wall&quot; in front of apps and unlock it by holding a button for X seconds\\n- Selectively block Shorts and Reels\\n- Block every youtube video under 2min\\n- Routine (disable app on certain hours) feature\\n- Time limit feature",
        created_at = "2024-05-27T15:26:36Z",
        story_id = 40491635,
        story_title = "WallHabit: Block YT Shorts, IG Reels, Videos < 2min",
        story_url = "https://play.google.com/store/apps/details?id=com.wallhabit.app&hl=en_US",
        updated_at = "2024-05-27T15:27:46Z"
    )

    @Test
    fun `test hit UseCase`() = runTest {
        val request = GetHitUseCase.Request(40491635)

        whenever(hitRepository.getHit(40491635)).thenReturn(flowOf(fakeHit))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetHitUseCase.Response(fakeHit), response)
    }
}