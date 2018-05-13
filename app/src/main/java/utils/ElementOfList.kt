package utils

import java.util.*

/*
Data class for storing information about elements of created list.
It stores integer value of element and boolean which determines if given element is red or blue.
 */
data class ElementOfList(var value: Int, val isRed: Boolean) {

    companion object {
        private const val UPPER_BOUND = 11
        private val random = Random()

        /*
        Function returns LinkedList including elements of list.
        @param size Number of elements to be added to created list.
        @return Linked list containing given number of list elements.
         */
        fun getArrayOfElements(size: Int): LinkedList<ElementOfList>{
            val elements = LinkedList<ElementOfList>()

            for (i in 1..size){
                elements.add(ElementOfList(random.nextInt(UPPER_BOUND), random.nextBoolean()))
            }

            return elements
        }
    }
}