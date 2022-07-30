package com.narcis.openweatherinterview.utils


import com.narcis.openweatherinterview.domain.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in R, T> (private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameter: R) : Flow<ResultWrapper<T>> = execute(parameter).
    catch { e -> emit(ResultWrapper.Error(Exception(e))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameter: R) : Flow<ResultWrapper<T>>
}
