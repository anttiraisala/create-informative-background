package fi.anttir.povray;

import java.util.*;

/**
 * Created by anttir on 31.10.2015.
 */
public class
        BarsMask {

    public BarsMask(){

        List<Shape> queue = new LinkedList<Shape>();

        queue.add(new Shape(1));
        queue.add(new Shape(2));
        queue.add(new Shape(3));

        // Process list
        for(ListIterator<Shape> iter = queue.listIterator(); iter.hasNext(); ){
            Shape shape = iter.next();

            if(shape.number==1) {
                iter.remove();
                shape = iter.next();
                iter.add(new Shape(4));
            }

            System.out.print(shape.number);



        }




    }



    public static void main(String[] args) {
        BarsMask bm = new BarsMask();
    }

    public class Shape{
        int number;

        public Shape(int number){this.number=number;}

    }

    public class ShapeQueue{
        private Shape first;
        List<Shape> queue = new LinkedList<Shape>();





    }


}
