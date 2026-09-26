package fi.anttir.povray;

import java.util.*;

/**
 * Created by anttir on 31.10.2015.
 */

/**
 * Does some thing in old style.
 *
 * @deprecated use {@link #fi.anttir.fractals.datastructures.FractalShapeList} instead.
 */
@Deprecated
public class FractalShapeList<E> {

    private Slot first;
    private Slot last;
    private Slot processingPointer;


    public FractalShapeList(){

    }

    public Slot peek(){
        return null;
    }

    public Slot add(E object){

        // If list is empty
        if(first==null){
            Slot s = new Slot(object);

            first=last=processingPointer=s;
            return s;
        }

        // List was not empty, so add as last element
        Slot s = new Slot(object);
        last.setNext(s);
        s.setPrevious(last);
        last=s;

        return s;
    }

    public Slot remove(){
        return null;
    }







    private class Slot<E>{
        private E object;
        private Slot previous;
        private Slot next;

        public Slot(){}
        public Slot(E object){
            setObject(object);
        }

        public E getObject() {
            return object;
        }

        public void setObject(E object) {
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
    }
}
