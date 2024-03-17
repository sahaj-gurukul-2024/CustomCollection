import org.example.EmptyString
import org.example.NonEmptyString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StringCollectionTest{

    @Test
    fun getAllUpperCase() {
        val stringCollection = NonEmptyString("a", NonEmptyString("b", NonEmptyString("c", EmptyString)))
        assertEquals(
            NonEmptyString("A", NonEmptyString("B", NonEmptyString("C", EmptyString))),
            stringCollection.getAllUppercase()
        )
    }

    @Test
    fun getAllLowerCase() {
        val stringCollection = NonEmptyString("A", NonEmptyString("B", NonEmptyString("C", EmptyString)))
        assertEquals(
            NonEmptyString("a", NonEmptyString("b", NonEmptyString("c", EmptyString))),
            stringCollection.getAllLowercase()
        )
    }
}