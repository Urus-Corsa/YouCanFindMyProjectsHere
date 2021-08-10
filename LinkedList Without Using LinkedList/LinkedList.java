package CS146S21.SinaKalantar.Project1;

import java.util.Objects;

/**
 * @author Sina Kalantar
 * @param <T>
 * @version 1.1
 *
 * Class below is the LinkedList using attributes of object T.
 *
 */


public class LinkedList<T> {

    Node<T> head = null;

    /**
     * a) Below is the add method which will add an element to the set and ignore if it already exists.
     *
     * @param element
     */
    public void add(T element) {

        if (this.match(element)) {

            return;
        }
        else {

            Node<T> nodeObject1 = new Node<T>(element);

            if (head != null) {

                nodeObject1.next = head;

            }
            head = nodeObject1;
        }
    }

    /**
     * b) Below is the remove method which would remove an element from the set. However first, it'll throw
     * a Null pointer Exception to see whether the head would return null.
     */

    public void remove(){

        try {

            if (head.getNext() != null) {

                head = head.getNext();

            } else {
                head = null;
            }
        }

        catch (NullPointerException ignored) {

            System.out.println("An error has occurred");

        }
    }

    /**
     * c) Below is the element counter method which would check to see if the current element of
     *    cursor is not null, if not it would increment and proceed to count all elements(nodes).
     *
     * @return number of elements in set (numberOfElements)
     */

    public int elementCounter() {

        int numberOfElements = 0;

        Node<T> currentNode;

        currentNode = head;

        if (currentNode != null) {

            do {

                currentNode = currentNode.next;

                numberOfElements++;

            } while (currentNode != null);
        }
        return numberOfElements;
    }

    /**
     * d) Below is the method that would iterate through each element of the set to see if there is a match.
     * @param node of T object.
     * @return true if a match found, returns false if not
     */


    public boolean match(T node) {

        Node<T> currentNode;

        currentNode = head;

        while(true) {

            if (currentNode == null)

                break;

            if (!Objects.equals(currentNode.data, node)) {

                currentNode = currentNode.next;
            } else {

                return true;
            }
        }
        return false;
    }

    /**
     * e) Below method would look for the element, if found, it'll return its index.
     *
     * @param node
     * @return the index if found, -1 if not found
     */

    public int returnIndex(T node) {

        Node<T> currentNode;

        currentNode = head;

        int startingPoint = 1;

        if (currentNode != null) {

            do if (currentNode.data.equals(node)) {

                return startingPoint;
            }

            else {
                currentNode = currentNode.next;

                startingPoint++;
            }
            while (currentNode != null);
        }
        return -1;
    }

    /**
     * f.1)Below methods will be determining the union and the intersection of two sets.
     * @param Linky
     * @return union
     */

     LinkedList<T> union

     (LinkedList<T> Linky) {

        LinkedList<T> union;

         union = new LinkedList<T>();

         Node<T> currentNode;

         currentNode = head;

         if (currentNode != null) {

             do {

                 union.add(currentNode.data);

                 currentNode = currentNode.next;

             } while (currentNode != null);
             currentNode = Linky.head;
         } else {
             currentNode = Linky.head;
         }

         do {

             if (currentNode == null)

                 break;

             union.add(currentNode.data);

             currentNode = currentNode.next;
         }

         while (true);

         return union;

    }

    /**
     * f.2) Below is the intersection method where it'd find the common element
     * @param newList
     * @return intersection
     */
    LinkedList<T> intersection

    (LinkedList<T> newList){

       LinkedList<T> interSection;

       interSection = new LinkedList<T>();

        Node<T> currentNode = head;

        if (currentNode != null) {

            do {

                if (newList.match(currentNode.data)) {

                    interSection.add(currentNode.data);
                }
                currentNode = currentNode.next;
            }
            while (currentNode != null);
        }
        return interSection;
    }

    /**
     * Below toString method is implemented to be used where the actual value will be compared to the expected value
     * This will be more of a use in the LinkedListTest class where Junit testing takes place.
     */

    public String toString() {

        StringBuilder checkList = new StringBuilder();

        Node<T> current = head;

        while (current != null) {

            checkList.append(current.getData());

            current = current.getNext();
        }
        return checkList.toString();
    }




}

