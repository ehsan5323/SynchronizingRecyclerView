package ir.example.newstest.domain.base

import ir.example.newstest.domain.util.Result
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T, W> {

    abstract operator fun invoke(params: T): Flow<Result<W>>

}