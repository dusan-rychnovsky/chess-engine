package cz.dusanrychnovsky.chessengine

interface Navigable<T> {
    fun next(): Navigable<T>?
    fun prev(): Navigable<T>?
}
