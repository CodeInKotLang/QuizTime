package com.synac.quiztime.domain.util

sealed interface DataError: Error {
    data object RequestTimeout : DataError
    data object TooManyRequests : DataError
    data object NoInternet : DataError
    data object Server : DataError
    data object Serialization : DataError
    data class Unknown(val errorMessage: String? = null) : DataError
}