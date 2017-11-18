package com.grasell

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf
import kotlinx.collections.immutable.mutate


fun <T> Sequence<T>.toImmutableList(): ImmutableList<T> {
    val sequence = this
    return immutableListOf<T>().mutate { list ->
        sequence.forEach {
            list.add(it)
        }
    }
}