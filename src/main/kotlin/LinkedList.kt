package org.example

import kotlin.math.pow

sealed interface LinkedList {
    fun append(value: Int): LinkedList = when(this) {
        is NonEmptyNode -> NonEmptyNode(head, tail.append(value))
        is EmptyNode -> NonEmptyNode(value, EmptyNode)
    }

    fun prepend(value: Int): LinkedList = when(this) {
        is NonEmptyNode -> NonEmptyNode(value, this)
        EmptyNode -> EmptyNode
    }

    fun getByIndex(index: Int): Int? = when(this) {
        is NonEmptyNode -> if (index == 0) head else tail.getByIndex(index - 1)
        is EmptyNode -> throw IndexOutOfBoundsException()
    }

    fun exponentsOf(power: Int) = operationAggregate(power) { it.toDouble().pow(power).toInt() }

    fun incrementBy(value: Int) = operationAggregate(value) { it.plus(value) }

    fun getAllOdds(): LinkedList = when(this) {
        is NonEmptyNode -> filter { it.mod(2) != 0 }
        EmptyNode -> EmptyNode
    }

    fun getAllEvens(): LinkedList = when(this) {
        is NonEmptyNode -> filter { it.mod(2) == 0 }
        is EmptyNode -> EmptyNode
    }


    fun getSum(): Int? = when(this) {
        is NonEmptyNode -> head + (tail.getSum() ?: 0)
        is EmptyNode -> 0
    }

    fun getMax(): Int? = when(this) {
        is NonEmptyNode -> maxOf(head, tail.getMax() ?: Int.MIN_VALUE)
        is EmptyNode -> null
    }

    fun getMin(): Int? = when(this) {
        is NonEmptyNode -> minOf(head, tail.getMin() ?: Int.MAX_VALUE)
        EmptyNode -> null
    }

    fun filter(condition: (Int) -> Boolean): LinkedList = when(this) {
        is NonEmptyNode -> if (condition(head)) NonEmptyNode(head, tail.filter(condition)) else tail.filter(condition)
        is EmptyNode -> EmptyNode
    }

    fun operationAggregate(value: Int, operation: (Int) -> Int): LinkedList = when(this) {
        is NonEmptyNode -> NonEmptyNode(operation(head), tail.operationAggregate(value, operation))
        is EmptyNode -> EmptyNode
    }
}

data object EmptyNode : LinkedList

data class NonEmptyNode(val head: Int, val tail: LinkedList) : LinkedList




