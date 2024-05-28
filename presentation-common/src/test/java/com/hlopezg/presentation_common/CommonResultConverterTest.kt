package com.hlopezg.presentation_common

import com.hlopezg.domain.entity.Result
import com.hlopezg.domain.entity.UseCaseException
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_common.state.UiState
import junit.framework.TestCase
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CommonResultConverterTest {
    private val converter = object : CommonResultConverter<String, String>() {
        override fun convertSuccess(data: String): String {
            return "result${data}"
        }
    }

    @Test
    fun testConvertError() {
        val errorMessage = "errorMessage"
        val exception = mock<UseCaseException>()
        whenever(exception.localizedMessage).thenReturn(errorMessage)
        val errorResult = Result.Error(exception)
        val result = converter.convert(errorResult)
        TestCase.assertEquals(UiState.Error(errorMessage), result)
    }

    @Test
    fun testConvertSuccess() {
        val data = "data"
        val successResult = Result.Success(data)
        val result = converter.convert(successResult)
        TestCase.assertEquals(UiState.Success("result${data}"), result)
    }
}