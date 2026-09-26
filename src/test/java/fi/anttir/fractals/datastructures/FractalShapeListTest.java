package fi.anttir.fractals.datastructures;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ListIterator;

import static org.junit.Assert.*;

/**
 * Created by anttir on 1.11.2015.
 */
public class FractalShapeListTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPeek() throws Exception {

    }


    @Test
    public void testAdd() throws Exception {

        FractalShapeList<Integer> fsl = new FractalShapeList<Integer>();

        assertEquals(0, fsl.getSize());

        fsl.add(15);
        assertEquals(1, fsl.getSize());

        fsl.add(16);
        assertEquals(2, fsl.getSize());

        fsl.add(22);
        assertEquals(3, fsl.getSize());

        fsl.add(30);
        assertEquals(4, fsl.getSize());
    }

    @Test
    public void testIterator() throws Exception {
        FractalShapeList<Integer> fsl = new FractalShapeList<Integer>();

        assertEquals(0, fsl.getSize());
        assertEquals(null, fsl.getFirst());
        assertEquals(null, fsl.getLast());
        //assertEquals(null, fsl.peek());

        fsl.add(15);
        assertEquals(1, fsl.getSize());
        assertEquals(new Integer(15), fsl.getFirst());
        assertEquals(new Integer(15), fsl.getLast());
        //assertEquals(new Integer(15), fsl.peek());

        fsl.add(16);
        assertEquals(2, fsl.getSize());
        assertEquals(new Integer(15), fsl.getFirst());
        assertEquals(new Integer(16), fsl.getLast());
        //assertEquals(new Integer(15), fsl.peek());

        fsl.add(22);
        assertEquals(3, fsl.getSize());
        assertEquals(new Integer(15), fsl.getFirst());
        assertEquals(new Integer(22), fsl.getLast());
        //assertEquals(new Integer(15), fsl.peek());

        fsl.add(30);
        assertEquals(4, fsl.getSize());
        assertEquals(new Integer(15), fsl.getFirst());
        assertEquals(new Integer(30), fsl.getLast());
        //assertEquals(new Integer(15), fsl.peek());

        // Test iterating

        ListIterator<Integer> lIter = fsl.listIterator();
        assertTrue(lIter.hasNext());
        assertFalse(lIter.hasPrevious());
        //
        assertEquals(new Integer(15), lIter.next());
        assertTrue(lIter.hasNext());
        assertFalse(lIter.hasPrevious());
        //
        assertEquals(new Integer(16), lIter.next());
        assertTrue(lIter.hasNext());
        assertTrue(lIter.hasPrevious());
        //
        assertEquals(new Integer(22), lIter.next());
        assertTrue(lIter.hasNext());
        assertTrue(lIter.hasPrevious());
        //
        assertEquals(new Integer(30), lIter.next());
        assertFalse(lIter.hasNext());
        assertTrue(lIter.hasPrevious());

        assertNull(lIter.next());
        assertNull(lIter.next());
        assertNull(lIter.next());

        // Create new iterator
        ListIterator<Integer> lIter2 = fsl.listIterator();
        assertTrue(lIter2.hasNext());
        assertFalse(lIter2.hasPrevious());
        //
        assertEquals(new Integer(15), lIter2.next());
        assertTrue(lIter2.hasNext());
        assertFalse(lIter2.hasPrevious());
        //
        assertEquals(new Integer(16), lIter2.next());
        assertTrue(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());
        //
        assertEquals(new Integer(22), lIter2.next());
        assertTrue(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());
        //
        assertEquals(new Integer(30), lIter2.next());
        assertFalse(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());

        assertNull(lIter2.next());
        assertNull(lIter2.next());
        assertNull(lIter2.next());

        //
        assertEquals(new Integer(22), lIter2.previous());
        assertTrue(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());
        //
        assertEquals(new Integer(16), lIter2.previous());
        assertTrue(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());
        //
        assertEquals(new Integer(15), lIter2.previous());
        assertTrue(lIter2.hasNext());
        assertFalse(lIter2.hasPrevious());

        assertNull(lIter2.previous());
        assertNull(lIter2.previous());
        assertNull(lIter2.previous());
    }


    @Test
    public void testTwoSimultaneousIterators() throws Exception {
        FractalShapeList<Integer> fsl = new FractalShapeList<Integer>();

        fsl.add(15);
        fsl.add(16);
        fsl.add(22);
        fsl.add(30);

        // Test iterating

        // 1st iterator
        ListIterator<Integer> lIter = fsl.listIterator();
        assertTrue(lIter.hasNext());
        assertFalse(lIter.hasPrevious());

        // 2nd iterator
        ListIterator<Integer> lIter2 = fsl.listIterator();
        assertTrue(lIter2.hasNext());
        assertFalse(lIter2.hasPrevious());


        // 1st
        //
        assertEquals(new Integer(15), lIter.next());
        assertTrue(lIter.hasNext());
        assertFalse(lIter.hasPrevious());


        // 2nd
        //
        assertEquals(new Integer(15), lIter2.next());
        assertTrue(lIter2.hasNext());
        assertFalse(lIter2.hasPrevious());
        // 2nd
        assertEquals(new Integer(16), lIter2.next());
        assertTrue(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());
        // 2nd
        assertEquals(new Integer(22), lIter2.next());
        assertTrue(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());



        // 1st
        assertEquals(new Integer(16), lIter.next());
        assertTrue(lIter.hasNext());
        assertTrue(lIter.hasPrevious());



        // 2nd
        assertEquals(new Integer(30), lIter2.next());
        assertFalse(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());
        // 2nd
        assertNull(lIter2.next());
        assertNull(lIter2.next());
        assertNull(lIter2.next());

        // 2nd
        assertEquals(new Integer(22), lIter2.previous());
        assertTrue(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());
        // 2nd
        assertEquals(new Integer(16), lIter2.previous());
        assertTrue(lIter2.hasNext());
        assertTrue(lIter2.hasPrevious());
        // 2nd
        assertEquals(new Integer(15), lIter2.previous());
        assertTrue(lIter2.hasNext());
        assertFalse(lIter2.hasPrevious());
        // 2nd
        assertNull(lIter2.previous());
        assertNull(lIter2.previous());
        assertNull(lIter2.previous());


        // 1st
        //
        assertEquals(new Integer(22), lIter.next());
        assertTrue(lIter.hasNext());
        assertTrue(lIter.hasPrevious());
        //
        assertEquals(new Integer(30), lIter.next());
        assertFalse(lIter.hasNext());
        assertTrue(lIter.hasPrevious());
        // 1st
        assertNull(lIter.next());
        assertNull(lIter.next());
        assertNull(lIter.next());
    }

    private FractalShapeList initFractalShapeList(FractalShapeList fsl){
        fsl.add(15);
        fsl.add(16);
        fsl.add(22);
        fsl.add(30);
        fsl.add(45);
        fsl.add(66);

        return fsl;
    }

    private FractalShapeList createFractalShapeList(){
        FractalShapeList<Integer> fsl = new FractalShapeList<Integer>();
        return initFractalShapeList(fsl);
    }


    @Test
    public void testListAddWhileIterating() throws Exception {
        FractalShapeList<Integer> fsl = createFractalShapeList();

        FractalShapeList.FractalListIterator lIter = fsl.listIterator();
        //
        assertEquals(new Integer(15), lIter.next());
        assertEquals(new Integer(16), lIter.next());
        assertEquals(new Integer(22), lIter.next());
        assertEquals(new Integer(30), lIter.next());

        assertEquals(6, fsl.getSize());
        fsl.add(109);
        assertEquals(7, fsl.getSize());

        assertEquals(new Integer(45), lIter.next());
        assertEquals(new Integer(66), lIter.next());

       lIter.addLast(169);
        assertEquals(8, fsl.getSize());

        assertEquals(new Integer(109), lIter.next());

        assertEquals(new Integer(169), lIter.next());

        assertFalse(lIter.hasNext());

        fsl.add(187);
        assertEquals(9, fsl.getSize());
        assertTrue(lIter.hasNext());

        assertEquals(new Integer(187), lIter.next());
        assertFalse(lIter.hasNext());

        fsl.add(190);
        assertEquals(new Integer(190), lIter.next());
        lIter.addLast(191);
        assertEquals(new Integer(191), lIter.next());
    }

    @Test
    public void testIteratorAdd() throws Exception {
        FractalShapeList<Integer> fsl = createFractalShapeList();

        FractalShapeList.FractalListIterator lIter = fsl.listIterator();
        //
        assertEquals(new Integer(15), lIter.next());
        assertEquals(new Integer(16), lIter.next());
        assertEquals(new Integer(22), lIter.next());
        assertEquals(new Integer(30), lIter.next());

        assertEquals(6, fsl.getSize());
        lIter.addLast(139);
        assertEquals(7, fsl.getSize());


        assertEquals(new Integer(45), lIter.next());
        assertEquals(new Integer(66), lIter.next());
        assertEquals(new Integer(139), lIter.next());

        assertFalse(lIter.hasNext());

        fsl.add(111);
        assertEquals(8, fsl.getSize());
        assertTrue(lIter.hasNext());

        assertEquals(new Integer(111), lIter.next());
        assertFalse(lIter.hasNext());

        // Test iterator.addLast
    }

    @Test
    public void testAddFirst() throws Exception {
        FractalShapeList<Integer> fsl = new FractalShapeList<Integer>();

        assertEquals(0, fsl.getSize());

        fsl.addFirst(10);
        assertEquals(1, fsl.getSize());
        assertEquals(new Integer(10), fsl.getFirst());

        fsl.addFirst(9);
        assertEquals(2, fsl.getSize());
        assertEquals(new Integer(9), fsl.getFirst());

        FractalShapeList<Integer>.FractalListIterator<Integer> lIter = fsl.listIterator();
        lIter.addFirst(8);

        int i1 = lIter.next();
        assertEquals(new Integer(8).intValue(), i1);
        int i2 = lIter.next();
        assertEquals(new Integer(9).intValue(), i2);
        int i3 = lIter.next();
        assertEquals(new Integer(10).intValue(), i3);

        // Try again by first using iterator.addFirst
        fsl = new FractalShapeList<Integer>();
        lIter = fsl.listIterator();

        lIter.addFirst(17);
        assertEquals(new Integer(17), fsl.getFirst());

        lIter.addFirst(15);
        assertEquals(new Integer(15), fsl.getFirst());

        fsl.addFirst(9);
        assertEquals(new Integer(9), fsl.getFirst());

        lIter.addFirst(8);
        assertEquals(new Integer(8), fsl.getFirst());
    }

    @Test
    public void testRemove() throws Exception {

    }
}