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
    fun <StringCollection> StringCollection.isNotEmpty(): Boolean {
        return this is NonEmptyString
    }

    fun getSumOfAllLengths(): Int = when (this) {
        EmptyString -> 0
        is NonEmptyString -> head.length + tail.getSumOfAllLengths()
    }

    fun getConcatenated(): String = concatenate { it }

    fun getFirstCharacterConcatenated(): String = concatenate { it.take(1) }

    fun operationAggregate(operation: (String) -> String): StringCollection = when(this) {
        EmptyString -> EmptyString
        is NonEmptyString -> NonEmptyString(operation(head), tail.operationAggregate(operation))
    }

    fun filter(condition: (String) -> Boolean): StringCollection = when(this) {
        EmptyString -> EmptyString
        is NonEmptyString -> if (condition(head)) NonEmptyString(head, tail.filter(condition)) else tail.filter(condition)
    }

    fun concatenate(getHead: (String) -> String): String = when (this) {
        EmptyString -> ""
        is NonEmptyString -> getHead(head).plus(tail.getConcatenated())
    }
}

data class NonEmptyString(val head: String, val tail: StringCollection) : StringCollection

data object EmptyString : StringCollection

