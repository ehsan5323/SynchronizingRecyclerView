package ir.example.newstest.domain.error
import ir.example.newstest.domain.error.ErrorType.*

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun handleError(e: Exception): ApiException =
    when (e) {
        is UnknownHostException -> ApiException(
            "Internet sis not available",
            NETWORK
        )
        is HttpException -> {
            when (e.code()) {
                400 -> ApiException(
                    "The request that was sent to the server is invalid.",
                    CLIENT,
                    e.code()
                )
                else -> ApiException(
                    "Unknown HTTP error, please try again",
                    UNKNOWN,
                    e.code()
                )
            }
        }
        else -> ApiException(
            "Unknown error, please try again",
            UNKNOWN
        )
    }
