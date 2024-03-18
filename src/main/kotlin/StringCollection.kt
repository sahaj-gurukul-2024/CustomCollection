package org.example

sealed interface StringCollection {
    fun getAllUppercase(): StringCollection
    fun getAllLowercase(): StringCollection
    fun getThreeCharacterLongValues(): StringCollection
    fun getLength(): IntegerCollection
    fun getSumOfAllLengths(): Int
    fun getConcatenated(): String
    fun getFirstCharacterConcatenated(): String
    
    fun operationAggregate(operation: (String) -> String): StringCollection
    fun filter(condition: (String) -> Boolean): StringCollection
    fun concatenate(getHead: (String) -> String): String

}

data class NonEmptyString(val head: String, val tail: StringCollection) : StringCollection {
    override fun getAllUppercase(): StringCollection = operationAggregate { it.uppercase() }
    override fun getAllLowercase(): StringCollection = operationAggregate { it.lowercase() }
    override fun getThreeCharacterLongValues(): StringCollection = filter { it.length == 3 }
    override fun getLength(): IntegerCollection = NonEmptyNode(head.length, tail.getLength())
    override fun getSumOfAllLengths(): Int = head.length + tail.getSumOfAllLengths()
    override fun getConcatenated(): String = concatenate { it }
    override fun getFirstCharacterConcatenated(): String = concatenate { it.take(1) }
    
    override fun operationAggregate(operation: (String) -> String): StringCollection = NonEmptyString(operation(head), tail.operationAggregate(operation))
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
    
    override fun operationAggregate(operation: (String) -> String): StringCollection = this
    override fun filter(condition: (String) -> Boolean): StringCollection = this
    override fun concatenate(getHead: (String) -> String): String = ""
}
