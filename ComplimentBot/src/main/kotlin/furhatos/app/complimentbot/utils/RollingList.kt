package furhatos.app.complimentbot.utils

import furhatos.records.GenericRecord

/**
 * Implements a "rolling list". A rolling list has a maximum capacity, and
 * removes the oldest elements from the list to maintain this capacity.
 *
 * @param <T> The type if items that this list contains
 * @param capacity The capacity of this list.
 */
class RollingList<T>(private val capacity: Int): GenericRecord<T>() {
    /** The items in this rolling list.  */
    private val items: MutableList<T> = ArrayList()

    /**
     * Retrieves the current position within the list.
     *
     * @return This list's positional pointer
     */
    /**
     * Sets the positional pointer of this list.
     *
     * @param position The new position
     */
    /** This list's position pointer.  */
    var position = 0

    /** Whether to add a fake empty item to the end of this list.  */
    private var addEmpty: Boolean = false

    /** The "empty" item to be added.  */
    private var empty: T? = null

    /**
     * Removes the specified element from this list.
     *
     * @param o The object to be removed from the list.
     * @return True if the list contained the specified element, false otherwise.
     */
    fun remove(o: T?): Boolean {
        return items.remove(o)
    }

    /**
     * Determines if this list is currently empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    override fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    /**
     * Determines if this list is currently not empty.
     *
     * @return True if the list has at least one element, false otherwise.
     */
    override fun isNotEmpty(): Boolean {
        return items.isNotEmpty()
    }

    /**
     * Retrieves the item at the specified index in this list.
     *
     * @param index The index to look up
     * @return The item at the specified index
     */
    operator fun get(index: Int): T {
        return items[index]
    }

    /**
     * Determines if this list contains the specified object.
     *
     * @param o The object to be checked
     * @return True if this list contains the item, false otherwise.
     */
    operator fun contains(o: Any?): Boolean {
        return items.contains(o)
    }

    /**
     * Clears all items from this list.
     */
    fun clear() {
        items.clear()
    }

    /**
     * Adds the specified item to this list. If the list has reached its
     * maximum capacity, this method will remove elements from the start of the
     * list until there is sufficient room for the new element.
     *
     * @param e The element to be added to the list.
     * @return True
     */
    fun add(e: T): Boolean {
        while (items.size > capacity - 1) {
            items.removeAt(0)
            position--
        }
        return items.add(e)
    }

    /**
     * Determines if there is an element after the positional pointer of the list.
     *
     * @return True if there is an element, false otherwise.
     */
    operator fun hasNext(): Boolean {
        return items.size > position + 1 || items.size > position && addEmpty
    }

    val next: T?
        /**
         * Retrieves the element after the positional pointer of the list.
         *
         * @return The next element in the list
         */
        get() = if (items.size > position + 1 || !addEmpty) {
            get(++position)
        } else {
            position++
            empty
        }

    /**
     * Determines if there is an element befpre the positional pointer of the list.
     *
     * @return True if there is an element, false otherwise.
     */
    fun hasPrevious(): Boolean {
        return 0 < position
    }

    val previous: T
        /**
         * Retrieves the element before the positional pointer of the list.
         *
         * @return The previous element in the list
         */
        get() = get(--position)

    /**
     * Sets the positional pointer of this list to the end.
     */
    fun seekToEnd() {
        position = items.size
    }

    /**
     * Sets the positional pointer of this list to the start.
     */
    fun seekToStart() {
        position = 0
    }

    val list: List<T>
        /**
         * Retrieves a list of items that this rolling list contains.
         *
         * @return A list of items in this rolling list.
         */
        get() = ArrayList(items)
}