package fi.anttir.fractals.datastructures;

/**
 * Created by anttir on 1.11.2015.
 */


import java.util.ListIterator;
import java.util.UUID;

/**
 * Created by anttir on 31.10.2015.
 */

public class FractalShapeList<E> {

    private Slot<E> first;
    private Slot<E> last;
    //private Slot<E> processingPointer;
    private int size;

    public FractalShapeList() {

    }


    public FractalListIterator<E> listIterator() {
        return new FractalListIterator<E>(this);
    }

    public Slot<E> getFirstSlot() {
        return first;
    }

    public Slot<E> getLastSlot() {
        return last;
    }

    /**
     * Get first element value in list
     * @return
     */
    public E getFirst() {
        return getValueOfSlot(first);
    }

    private E getValueOfSlot(Slot slot) {
        if (slot != null)
            return (E) slot.getValue();

        return null;
    }

    public E getLast() {
        return getValueOfSlot(last);
    }


/*
    public E peek(){
        return getValueOfSlot( processingPointer);
    }*/

    public int getSize() {
        return size;
    }


    /**
     * Add to the end of collection
     * @param object
     * @return
     */
    public Slot<E> add(E object) {

        // If list is empty
        if (first == null) {
            return addFirst(object);
        }

        // List was not empty, so add as last element
        Slot s = new Slot(object);
        last.setNext(s);
        s.setPrevious(last);
        last = s;
        size++;

        return s;
    }

    /**
     * Add element into beginning of list
     * @param object
     * @return
     */
    public Slot<E> addFirst(E object) {
        // If list is empty
        if (first == null) {
            Slot s = new Slot(object);

            first = last = s;
            size = 1;
            return s;
        }

        // List was not empty, so add as last element
        Slot originalFirstSlot = first;
        Slot s = new Slot(object);
        s.next = originalFirstSlot;
        originalFirstSlot.previous = s;
        first=s;
        size++;

        return s;
    }

    public E remove() {
        return null;
    }

    /********************************************************************************/
    /***** FractalListIterator - begins *********************************************/
    /********************************************************************************/
    public class FractalListIterator<E> implements ListIterator<E> {

        private FractalShapeList<E> sourceFractalShapeList;
        private Slot<E> cursorPosition;

        public FractalListIterator(FractalShapeList fractalShapeList) {
            sourceFractalShapeList = fractalShapeList;
            /*if(sourceFractalShapeList.size>0){
                cursorPosition = (Slot<E> )sourceFractalShapeList.getFirstSlot();
            }*/
        }

        public boolean hasNext() {
            if (cursorPosition == null && sourceFractalShapeList.getFirstSlot() != null)
                return true;

            return cursorPosition.next != null;
        }

        public E next() {
            if (hasNext()) {
                if (cursorPosition != null)
                    cursorPosition = cursorPosition.getNext();
                else
                    cursorPosition = (Slot<E>) sourceFractalShapeList.getFirstSlot();

                return cursorPosition.getValue();
            }

            return null;
        }

        public boolean hasPrevious() {
            if (cursorPosition == null)
                return false;

            return cursorPosition.previous != null;
        }

        public E previous() {
            if (hasPrevious()) {
                cursorPosition = cursorPosition.getPrevious();
                return cursorPosition.getValue();
            }

            return null;
        }

        public int nextIndex() {
            throw new UnsupportedOperationException();
        }


        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }


        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        /**
         * Add ( "insert" ) element to cursor's position, i.e. after the previously fetched next(), so the added element will be the next next()
         * @param e
         */
        public void add(E e) {
            if (cursorPosition != null)
                cursorPosition = cursorPosition.getNext();
            else{

                cursorPosition = (Slot<E>) sourceFractalShapeList.getFirstSlot();
            }
        }

        /**
         * Add to the end of collection
         * @param e
         * @return
         */
        public Slot<E> addLast(E e) {
            return (Slot<E>)sourceFractalShapeList.add(e);
        }

        /**
         * Add element into beginning of list
         * @param e
         */
        public void addFirst(E e) {
            sourceFractalShapeList.addFirst(e);
        }
    }
    /***** FractalListIterator - ends ***********************************************/
    /********************************************************************************/

    /********************************************************************************/
    /***** Slot -begins *************************************************************/
    /********************************************************************************/
    private class Slot<E> {
        private E object;
        private Slot previous;
        private Slot next;
        public final UUID GUID = UUID.randomUUID();

        public Slot() {
        }

        public Slot(E object) {
            setValue(object);
        }

        public E getValue() {
            return object;
        }

        public void setValue(E object) {
            this.object = object;
        }

        public Slot getPrevious() {
            return previous;
        }

        public void setPrevious(Slot previous) {
            this.previous = previous;
        }

        public Slot getNext() {
            return next;
        }

        public void setNext(Slot next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Slot<?> slot = (Slot<?>) o;

            return GUID.equals(slot.GUID);

        }

        @Override
        public int hashCode() {
            return GUID.hashCode();
        }
    }
    /***** Slot -ends ***************************************************************/
    /********************************************************************************/

}
