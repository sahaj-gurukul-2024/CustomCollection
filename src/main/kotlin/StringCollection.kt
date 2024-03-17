package org.example

sealed interface StringCollection {
    fun getAllUppercase(): StringCollection = when (this) {
        EmptyString -> EmptyString
        is NonEmptyString -> NonEmptyString(head.uppercase(), tail.getAllUppercase())
    }

    fun getAllLowercase(): StringCollection = when (this) {
        EmptyString -> EmptyString
        is NonEmptyString -> NonEmptyString(head.lowercase(), tail.getAllLowercase())
    }

    fun getThreeCharacterLongValues(): StringCollection = when (this) {
        EmptyString -> EmptyString
        is NonEmptyString -> if (head.length == 3) NonEmptyString(head, tail.getThreeCharacterLongValues()) else tail.getThreeCharacterLongValues()
    }


}

data class NonEmptyString(val head: String, val tail: StringCollection) : StringCollection

data object EmptyString : StringCollection

