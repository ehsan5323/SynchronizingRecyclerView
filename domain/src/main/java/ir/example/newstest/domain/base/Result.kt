package ir.example.newstest.domain.base

import ir.example.newstest.domain.error.ApiException

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    object Loading : Result<Nothing>()
    data class Error(val error: ApiException) : Result<Nothing>()
}