import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Sina Kalantar
 * @version 1.1
 *
 * Class below is the LinkedListTest using Junit where tests every and each method of class LinkedList.
 *
 */

class LinkedListTest {

    LinkedList<Integer> list = new LinkedList<Integer>();

    @Test
    void testAdd() {

        long firstTime = System.currentTimeMillis();

        list.add(6);

        long secondTime = System.currentTimeMillis();

        list.add(9);

        list.add(21);

        System.out.println("The time taken for add method in miliseconds is " + (secondTime - firstTime));

        assertEquals("2196",list.toString());
    }

    @Test
    void testRemove() {

        long firstTime = System.currentTimeMillis();

        list.add(8);

        list.add(5);

        list.add(6);

        long secondTime = System.currentTimeMillis();

        list.remove();

        System.out.println("The time taken for remove method in miliseconds is " + (secondTime - firstTime));

        assertEquals("58",list.toString());

    }

    @Test
    void testElementCounter() {

        list.add(3);

        list.add(8);

        long firstTime = System.currentTimeMillis();

        list.elementCounter();

        long secondTime = System.currentTimeMillis();

        System.out.println("The time taken for element counter method in miliseconds is " + (secondTime - firstTime));

        assertEquals("83",list.toString());

    }


    @Test
    void testMatch() {

        long firstTime = System.currentTimeMillis();

        list.add(8);

        list.add(9);

        list.add(5);

        long secondTime = System.currentTimeMillis();

        System.out.println("The time taken for match method in miliseconds is " + (secondTime - firstTime));

        assertEquals(true,list.match(5));

        assertEquals(false,list.match(3));
    }

    @Test
    void testReturnIndex() {

        long firstTime = System.currentTimeMillis();

        list.add(85);

        list.add(9);

        list.add(1);

        long secondTime = System.currentTimeMillis();

        System.out.println("The time taken for return index method in miliseconds is " + (secondTime - firstTime));

        assertEquals(1,list.returnIndex(1));
    }



    @Test
    void testUnion() {

        LinkedList<String> firstList = new LinkedList<String>();

        firstList.add("S"); firstList.add("I"); firstList.add("N"); firstList.add("A");

        LinkedList<String> secondList = new LinkedList<String>();

        secondList.add("K"); secondList.add("A"); secondList.add("L"); secondList.add("A");

        secondList.add("N"); secondList.add("T"); secondList.add("A"); secondList.add("R");

        LinkedList<String> unionOfTwoLists = new LinkedList<String>();

        long firstTime = System.currentTimeMillis();

        unionOfTwoLists = firstList.union(secondList);

        long secondTime = System.currentTimeMillis();

        System.out.println("The time taken for union method in miliseconds is " + (secondTime - firstTime));

        assertEquals("KLTRSINA", unionOfTwoLists.toString());
    }

    @Test
    void testIntersection() {

        LinkedList<String> firstList = new LinkedList<String>();

        firstList.add("S"); firstList.add("I"); firstList.add("N");

        firstList.add("A");

        LinkedList<String> secondList = new LinkedList<String>();

        secondList.add("K"); secondList.add("A"); secondList.add("L"); secondList.add("A");

        secondList.add("N"); secondList.add("T"); secondList.add("A"); secondList.add("R");

        LinkedList<String> interSectionOfTwoLists = new LinkedList<String>();

        long firstTime = System.currentTimeMillis();

        interSectionOfTwoLists = firstList.intersection(secondList);

        long secondTime = System.currentTimeMillis();

        System.out.println("The time taken for intersection method in miliseconds is " + (secondTime - firstTime));

        assertEquals("NA", interSectionOfTwoLists.toString());
    }




}
