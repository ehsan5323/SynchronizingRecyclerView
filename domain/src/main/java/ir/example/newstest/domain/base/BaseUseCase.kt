package ir.example.newstest.domain.base

abstract class BaseUseCase<T, W> {

    abstract operator fun invoke(params: T): W

}