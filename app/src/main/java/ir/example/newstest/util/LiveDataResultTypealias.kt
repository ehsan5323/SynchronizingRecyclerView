package ir.example.newstest.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.example.newstest.domain.util.Result

typealias MutableLiveResult<T> = MutableLiveData<Result<T>>
typealias LiveResult<T> = LiveData<Result<T>>

typealias MutableLiveListResult<T> = MutableLiveData<Result<List<T>>>
typealias LiveListResult<T> = LiveData<Result<List<T>>>