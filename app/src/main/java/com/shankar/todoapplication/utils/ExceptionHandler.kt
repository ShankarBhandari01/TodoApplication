package com.shankar.todoapplication.utils


import com.shankar.todoapplication.base.UiState
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

inline fun <T> doTryCatch(task: () -> UiState<T>): UiState<T> {
    return try {
        task.invoke()
    } catch (e: Exception) {
        e.printStackTrace()
        UiState.Error(e.generateMessage())
    }
}

inline fun <T> handleTryCatch(task: () -> T) {
    try {
        task.invoke()
    } catch (e: Exception) {

    }
}

inline fun tryIgnoreCatch(task: () -> Unit) {
    try {
        task.invoke()
    } catch (e: Exception) {

    }
}

fun Throwable.generateMessage(): String {

    val couldNotConnectToServer = "Could not connect to server. Please try again later."
    val internetNotAvailableErrorMessage =
        "No internet connection. Please check your network connection and try again."
    val cannotConnectErrorMessage =
        "Could not connect to server. Please check your network connection and try again."
    val requestTimeOutErrorMessage = "Server request time out. Please try again later."
    val genericErrorMessage = "Error encountered. Please try again later."
    return try {
        when (this) {
            is UnknownHostException -> couldNotConnectToServer
            is java.net.ConnectException -> cannotConnectErrorMessage
            is SocketTimeoutException -> requestTimeOutErrorMessage
            is HttpException ->
                try {
                    val responseBody = response()?.errorBody()
                    val jsonObject = JSONObject(responseBody?.string()!!)
                    if (jsonObject.has("message")) {
                        jsonObject.optString("message")
                    } else {
                        genericErrorMessage
                    }
                } catch (e: Exception) {

                    genericErrorMessage
                }
            else -> genericErrorMessage
        }
    } catch (ex: java.lang.Exception) {

        genericErrorMessage
    }
}
