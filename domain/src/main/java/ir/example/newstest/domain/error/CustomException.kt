package ir.example.newstest.domain.error

data class CustomException(override val message: String) : Exception()