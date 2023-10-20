package com.farzin.imdb.models

class StateHolder<T> {

    private val stateMap = mutableMapOf<Int, T>()

    fun getState(key: Int, default: T): T {
        return stateMap.getOrDefault(key, default)
    }

    fun updateState(key: Int, value: T) {
        stateMap[key] = value
    }

}