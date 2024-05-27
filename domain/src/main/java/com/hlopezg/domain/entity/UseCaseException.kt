package com.hlopezg.domain.entity

sealed class UseCaseException(cause: Throwable) : Throwable(cause) {

    class PostException(cause: Throwable) : UseCaseException(cause)

    class UnknownException(cause: Throwable) : UseCaseException(cause)

    companion object {
        fun createFromThrowable(throwable: Throwable): UseCaseException {
            throwable.localizedMessage?.let { println("UseCaseException: $it") }
            return if (throwable is UseCaseException) throwable else UnknownException(throwable)
        }
    }
}