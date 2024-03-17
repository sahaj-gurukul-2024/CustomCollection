package org.example

sealed interface StringCollection {
    fun getAllUppercase(): StringCollection = when (this) {
        EmptyString -> EmptyString
        is NonEmptyString -> NonEmptyString(head.uppercase(), tail.getAllUppercase())
    }

    fun getAllLowercase(): StringCollection = when(this) {
        EmptyString -> EmptyString
        is NonEmptyString -> NonEmptyString(head.lowercase(), tail.getAllLowercase())
    }

    

}

data class NonEmptyString(val head: String, val tail: StringCollection) : StringCollection

data object EmptyString : StringCollection

