package ir.example.newstest.data.base

import ir.example.newstest.domain.base.FlowResult
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.error.ApiException
import ir.example.newstest.domain.error.ErrorType
import ir.example.newstest.domain.error.handleError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

fun <T> resultFlow(callback: suspend () -> T): FlowResult<T> {
    return flow {
        emit(Result.Loading)
        emit(safeCall { callback.invoke() })
    }
}

private suspend fun <T> safeCall(callback: suspend () -> T) = withContext(Dispatchers.IO) {
    return@withContext try {
        Result.Success(callback.invoke())
    } catch (e: Exception) {
        Result.Error(handleError(e))
    }
}

fun <T> Flow<T>.resultFlow(cond: ((T) -> Boolean)? = null): FlowResult<T> {
    return onStart {
        Result.Loading
    }.map { data ->
        if (cond?.let { cond(data) } != false) Result.Success(data)
        else Result.Error(ApiException("", ErrorType.CLIENT, -1))
    }.catch { cause: Throwable ->
        Result.Error(ApiException(cause.localizedMessage ?: "", ErrorType.CLIENT, -1))
    }
}