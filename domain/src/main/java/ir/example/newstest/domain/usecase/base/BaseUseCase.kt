package ir.example.newstest.domain.usecase.base

import ir.example.newstest.domain.base.Result
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T, W> {

    abstract operator fun invoke(params: T): Flow<Result<W>>

}