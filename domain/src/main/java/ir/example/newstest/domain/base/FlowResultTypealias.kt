package ir.example.newstest.domain.base

import kotlinx.coroutines.flow.Flow

typealias FlowResult<T> = Flow<Result<T>>
typealias FlowListResult<T> = Flow<Result<List<T>>>