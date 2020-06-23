package ir.example.newstest.util.ktx

import ir.example.newstest.domain.util.Result
import ir.example.newstest.util.MutableLiveResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

suspend fun <T> Flow<Result<T>>.collectOn(mld: MutableLiveResult<T>) {
    this.collect { mld.value = it }
}
