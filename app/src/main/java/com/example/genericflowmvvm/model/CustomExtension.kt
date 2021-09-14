package com.example.genericflowmvvm.model

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <T> toResultFlow(call: suspend () -> Response<T>?): Flow<ApiResult<T>?> {
    return flow {
        emit(ApiResult.Loading)

        try {
            val c = call()
            c?.let {
                if (c.isSuccessful) {
                    emit(ApiResult.Success(c.body()))
                } else {
                    c.errorBody()?.let {
                        val error = it.string()
                        it.close()
                        emit(ApiResult.Error(error))
                    }
                }
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(e.toString()))
        }

    }.flowOn(Dispatchers.IO)
}

fun Any.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}
