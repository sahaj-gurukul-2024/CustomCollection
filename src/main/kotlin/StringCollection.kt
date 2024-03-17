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
        is NonEmptyString -> if (head.length == 3) NonEmptyString(head, tail.getThreeCharacterLongValues()) else tail.getThreeCharacterLongValues()
    }

    fun getLength(): IntegerCollection = when (this) {
        EmptyString -> EmptyNode
        is NonEmptyString -> NonEmptyNode(head.length, tail.getLength())
    }

    fun getSumOfAllLengths(): Int = when (this) {
        EmptyString -> 0
        is NonEmptyString -> head.length + tail.getSumOfAllLengths()
    }

    fun getConcatenated(): String = when(this) {
        EmptyString -> ""
        is NonEmptyString -> head.plus(tail.getConcatenated())
    }

    fun getFirstCharacterConcatenated(): String = when(this) {
        EmptyString -> ""
        is NonEmptyString -> head.take(1).plus(tail.getFirstCharacterConcatenated())
    }

    fun operationAggregate(operation: (String) -> String): StringCollection = when(this) {
        EmptyString -> EmptyString
        is NonEmptyString -> NonEmptyString(operation(head), tail.operationAggregate(operation))
    }
}

data class NonEmptyString(val head: String, val tail: StringCollection) : StringCollection

data object EmptyString : StringCollection

