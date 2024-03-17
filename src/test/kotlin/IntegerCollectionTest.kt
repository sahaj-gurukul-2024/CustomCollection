import org.example.EmptyNode
import org.example.NonEmptyNode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class IntegerCollectionTest {

    @Test
    fun initList() {
        val nonEmptyNode = NonEmptyNode(1, EmptyNode)
        assertEquals(1, nonEmptyNode.head)
    }

    @Test
    fun appendToList() {
        assertEquals(
            NonEmptyNode(1, NonEmptyNode(2, EmptyNode)),
            NonEmptyNode(1, EmptyNode).append(2)
        )
    }

    @Test
    fun prependToList() {
        assertEquals(
            NonEmptyNode(1, NonEmptyNode(2, EmptyNode)),
            NonEmptyNode(2, EmptyNode).prepend(1)
        )
    }

    @Test
    fun getByIndex() {
        assertEquals(2,
            NonEmptyNode(1, NonEmptyNode(2, EmptyNode)).getByIndex(1))
    }

    @Test
    fun getByIndexThrowsException() {
        assertThrows<IndexOutOfBoundsException> {
            NonEmptyNode(1, EmptyNode).getByIndex(2)
        }
    }

    @Test
    fun getSquaresOfListElements() {
        assertEquals(
            NonEmptyNode(1, NonEmptyNode(4, EmptyNode)),
            NonEmptyNode(1, NonEmptyNode(2, EmptyNode)).exponentsOf(2)
        )
    }

    @Test
    fun getCubesOfListElements() {
        assertEquals(
            NonEmptyNode(1, NonEmptyNode(8, EmptyNode)),
            NonEmptyNode(1, NonEmptyNode(2, EmptyNode)).exponentsOf(3)
        )
    }

    @Test
    fun incrementBy() {
        assertEquals(
            NonEmptyNode(2, NonEmptyNode(3, EmptyNode)),
            NonEmptyNode(1, NonEmptyNode(2, EmptyNode)).incrementBy(1)
        )
    }

    @Test
    fun getAllOdds() {
        val linkedList = NonEmptyNode(1, NonEmptyNode(2, NonEmptyNode(3, EmptyNode)))
        assertEquals(
            NonEmptyNode(1, NonEmptyNode(3, EmptyNode)),
            linkedList.getAllOdds()
        )
    }

    @Test
    fun getAllEvens() {
        val linkedList = NonEmptyNode(1, NonEmptyNode(2, NonEmptyNode(3, EmptyNode)))
        assertEquals(
            NonEmptyNode(2, EmptyNode),
            linkedList.getAllEvens()
        )
    }

    @Test
    fun getSumOfAllItems() {
        val linkedList = NonEmptyNode(1, NonEmptyNode(2, NonEmptyNode(3, EmptyNode)))
        assertEquals(6, linkedList.getSum())
    }

    @Test
    fun getMaxOfAllItems() {
        val linkedList = NonEmptyNode(1, NonEmptyNode(2, NonEmptyNode(3, EmptyNode)))
        assertEquals(3, linkedList.getMax())
    }

    @Test
    fun getMinOfAllItems() {
        val linkedList = NonEmptyNode(1, NonEmptyNode(2, NonEmptyNode(3, EmptyNode)))
        assertEquals(1, linkedList.getMin())
    }
}