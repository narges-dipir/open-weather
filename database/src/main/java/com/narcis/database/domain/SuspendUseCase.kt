package com.narcis.database.domain


import com.narcis.model.domain.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.RuntimeException

abstract class SuspendUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P) : ResultWrapper<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    ResultWrapper.Success(it)
                }
            }
        } catch (e: Exception){
            ResultWrapper.Error(e)
        }
    }

@Throws(RuntimeException::class)
protected abstract suspend fun execute(parameters: P): R
}

