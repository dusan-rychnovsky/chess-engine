package cz.dusanrychnovsky.chessengine.extensions

inline fun <reified T> Enum<T>.next(): T? where T : Enum<T> =
    if (this.ordinal < enumValues<T>().size - 1) enumValues<T>()[this.ordinal + 1] else null

inline fun <reified T> Enum<T>.prev(): T? where T : Enum<T> =
    if (this.ordinal > 0) enumValues<T>()[this.ordinal - 1] else null
