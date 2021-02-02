package com.lorenzofonseca.domain.base

typealias Error<L> = Either.Left<L>
typealias Success<R> = Either.Right<R>

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isLeft get() = this is Left<L>
    val isRight get() = this is Right<R>

    val left: L? get() = (this as? Left<L>)?.a
    val right: R? get() = (this as? Right<R>)?.b

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any = when (this) {
        is Left -> fnL(a)
        is Right -> fnR(b)
    }

    fun single(fn: () -> Any): Any = fn()
}