package org.example

sealed interface StringCollection {
    fun getAllUppercase(): StringCollection = when (this) {
        EmptyString -> EmptyString
        is NonEmptyString -> operationAggregate { it.uppercase() }
    }

    fun getAllLowercase(): StringCollection = when (this) {
        EmptyString -> EmptyString
        is NonEmptyString -> operationAggregate { it.lowercase() }
    }

    fun getThreeCharacterLongValues(): StringCollection = when (this) {
        EmptyString -> EmptyString
        is NonEmptyString -> filter { it.length == 3 }
    }

    fun getLength(): IntegerCollection = when (this) {
        EmptyString -> EmptyNode
        is NonEmptyString -> NonEmptyNode(head.length, tail.getLength())
    }

    fun getSumOfAllLengths(): Int = when (this) {
        EmptyString -> 0
        is NonEmptyString -> head.length + tail.getSumOfAllLengths()
    }

    fun getConcatenated(): String {
        val getHead: (String) -> String = { it }
        return when (this) {
            EmptyString -> ""
            is NonEmptyString -> {
                getHead(head).plus(tail.getConcatenated())
            }
        }
    }

    fun getFirstCharacterConcatenated(): String {
        val getHead: (String) -> String = { it }
        return when (this) {
            EmptyString -> ""
            is NonEmptyString -> getHead(head.take(1)).plus(tail.getFirstCharacterConcatenated())
        }
    }

    fun operationAggregate(operation: (String) -> String): StringCollection = when(this) {
        EmptyString -> EmptyString
        is NonEmptyString -> NonEmptyString(operation(head), tail.operationAggregate(operation))
    }

    fun filter(condition: (String) -> Boolean): StringCollection = when(this) {
        EmptyString -> EmptyString
        is NonEmptyString -> if (condition(head)) NonEmptyString(head, tail.filter(condition)) else tail.filter(condition)
    }
}

data class NonEmptyString(val head: String, val tail: StringCollection) : StringCollection

data object EmptyString : StringCollection

