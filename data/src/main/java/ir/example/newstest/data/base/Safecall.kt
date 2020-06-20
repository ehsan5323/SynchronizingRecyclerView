package ir.example.newstest.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.error.handleError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

fun <T> resultFlow(callback: suspend () -> T): Flow<Result<T>> {
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

typealias MutableResult<T> = MutableLiveData<Result<T>>
typealias LiveResult<T> = LiveData<Result<T>>


