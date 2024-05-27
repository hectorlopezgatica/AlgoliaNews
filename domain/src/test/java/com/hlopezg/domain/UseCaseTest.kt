package com.hlopezg.domain

import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.entity.UseCaseException
import com.hlopezg.domain.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class UseCaseTest {
    @ExperimentalCoroutinesApi
    private val configuration = UseCase.Configuration(UnconfinedTestDispatcher())
    private val request = mock<UseCase.Request>()
    private val response = mock<UseCase.Response>()

    @ExperimentalCoroutinesApi
    private lateinit var useCase: UseCase<UseCase.Request, UseCase.Response>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                Assert.assertEquals(this@UseCaseTest.request, request)
                return flowOf(response)
            }
        }
    }

    @Test
    fun testExecutePostException() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                Assert.assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw UseCaseException.PostException(Throwable())
                }
            }

        }
        runTest {
            val result = useCase.execute(request).first()
            Assert.assertTrue((result as Result.Error).exception is UseCaseException.PostException)
        }
    }
}