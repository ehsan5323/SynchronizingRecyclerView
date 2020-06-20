package ir.example.newstest.util.ktx

import ir.example.newstest.data.base.MutableResult
import ir.example.newstest.domain.base.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

suspend fun <T> Flow<Result<T>>.collectOn(mld: MutableResult<T>) {
    this.collect { mld.value = it }
}
