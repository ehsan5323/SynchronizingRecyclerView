package ir.example.newstest.domain.util

import kotlinx.coroutines.flow.Flow

typealias FlowResult<T> = Flow<Result<T>>
typealias FlowListResult<T> = Flow<Result<List<T>>>