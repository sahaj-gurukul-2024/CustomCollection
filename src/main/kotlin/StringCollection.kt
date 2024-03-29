package org.example

sealed interface StringCollection {
    fun getAllUppercase(): StringCollection
    fun getAllLowercase(): StringCollection
    fun getThreeCharacterLongValues(): StringCollection
    fun getLength(): IntegerCollection
    fun getSumOfAllLengths(): Int
    fun getConcatenated(): String
    fun getFirstCharacterConcatenated(): String
    
    fun transform(operation: (String) -> String): StringCollection
    fun filter(condition: (String) -> Boolean): StringCollection
    fun concatenate(getHead: (String) -> String): String

}

data class NonEmptyString(val head: String, val tail: StringCollection) : StringCollection {
    override fun getAllUppercase(): StringCollection = transform { it.uppercase() }
    override fun getAllLowercase(): StringCollection = transform { it.lowercase() }
    override fun getThreeCharacterLongValues(): StringCollection = filter { it.length == 3 }
    override fun getLength(): IntegerCollection = NonEmptyNode(head.length, tail.getLength())
    override fun getSumOfAllLengths(): Int = head.length + tail.getSumOfAllLengths()
    override fun getConcatenated(): String = concatenate { it }
    override fun getFirstCharacterConcatenated(): String = concatenate { it.take(1) }
    
    override fun transform(operation: (String) -> String): StringCollection = NonEmptyString(operation(head), tail.transform(operation))
    override fun filter(condition: (String) -> Boolean): StringCollection = if (condition(head)) NonEmptyString(head, tail.filter(condition)) else tail.filter(condition)
    override fun concatenate(getHead: (String) -> String): String = getHead(head).plus(tail.concatenate(getHead))

}

data object EmptyString : StringCollection {
    override fun getAllUppercase(): StringCollection = this
    override fun getAllLowercase(): StringCollection = this
    override fun getThreeCharacterLongValues(): StringCollection = this
    override fun getLength(): IntegerCollection = EmptyNode
    override fun getSumOfAllLengths(): Int = 0
    override fun getConcatenated(): String = ""
    override fun getFirstCharacterConcatenated(): String = ""
    
    override fun transform(operation: (String) -> String): StringCollection = this
    override fun filter(condition: (String) -> Boolean): StringCollection = this
    override fun concatenate(getHead: (String) -> String): String = ""
}
