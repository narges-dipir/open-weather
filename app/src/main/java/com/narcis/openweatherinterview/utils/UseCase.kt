package com.narcis.openweatherinterview.utils

import java.lang.RuntimeException
import kotlin.jvm.Throws


abstract class UseCase<in P, R> {
    operator fun invoke(parameter : P) : R = execute(parameter)

    @Throws(RuntimeException::class)
    protected abstract fun execute(parameter: P): R
}