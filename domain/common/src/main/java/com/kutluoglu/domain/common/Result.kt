package com.kutluoglu.domain.common

sealed class Result<out T> {
	data class Success<T>(val value: T) : Result<T>()
	data class Error(val throwable: Throwable) : Result<Nothing>()

	inline fun <R> map(transform: (T) -> R): Result<R> = when (this) {
		is Success -> Success(transform(value))
		is Error -> this
	}
}