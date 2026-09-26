package fi.anttir.imagemagick;

import fi.fxstudio.math.Vector2;

import static fi.fxstudio.math.Constants.*;
import fi.fxstudio.math.Vector2.*;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.regex.Matcher;

import static fi.anttir.random.RandomHelpers.*;

/**
 * Created by anttir on 16.11.2015.
 */
public class Masks {

    private String targetImageFilename = "mask.png";
    private String targetScriptFilename = "create-mask.script";

    private MaskType maskType = MaskType.CIRCLES;

    public enum MaskType {
        CLOUDS("clouds"),
        CIRCLES("circles");

        private final String text;

        MaskType(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public static MaskType fromString(String text) {
            if (text != null) {
                for (MaskType b : MaskType.values()) {
                    if (text.equalsIgnoreCase(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }
    }

    int width = 2880;
    int height = 1800;

    private PrintWriter writer = null;

    public Masks(String[] args) {
        checkForEmptyParameters(args);

        try {
            writer = new PrintWriter(targetScriptFilename, "UTF-8");

            addStuff();

            writer.close();
        } catch (Exception e) {
            if (writer != null) {
                writer.flush();
                writer.close();
            }

            System.exit(1);
        }

        System.exit(0);

    }

    private void checkForEmptyParameters(String[] args) {
        if (args == null || args != null && args.length < 1) {
            System.out.print("Usage: Masks -t maskType -oi outputfilename -os scriptfilename");
            System.out.print("\tmaskType; " + MaskType.CLOUDS + ", " + MaskType.CIRCLES);
            System.out.print("\tExample: Masks mask.png create-mask.script");
            System.out.println("\tRun by: " +
                    "\n" +
                    "\t\t$ CMD=\"$(cat create-mask.script)\"" +
                    "\n\t\t$ eval $CMD");

            System.out.println("\tor by:" +
                    "\n\t\t$ eval \"$(cat create-mask.script)\"");


            /*

            CMD="$(cat test.script)"
            eval $CMD

            eval "$(cat create-colors.script)"

             */


            System.exit(1);
        }

        for (int i = 0; i < args.length; i++) {
            if ("-t".equals(args[i])) {
                maskType = MaskType.fromString(args[i + 1]);
            } else if ("-oi".equals(args[i])) {
                targetImageFilename = args[i + 1];
            } else if ("-os".equals(args[i])) {
                targetScriptFilename = args[i + 1];
            }
        }
    }

    public static void main(String[] args) {
        Masks m = new Masks(args);
    }

    private void addStuff() {
        System.out.println("addStuff() - " + maskType);

        if (maskType.equals(MaskType.CLOUDS)) {
            writer.print("convert -size " + width + "x" + height + " plasma:fractal -blur 0x5 -fx G -brightness-contrast 0x80 " + targetImageFilename);
            return;
        }

        if (maskType.equals(MaskType.CIRCLES)) {
            writer.print("convert -size " + width + "x" + height + " xc:black");
            generateCircles();
            writer.print(" " + targetImageFilename);
            return;
        }
    }

    private void generateCircles() {
        System.out.println("createCirles()");
        writer.print(" -fill white -stroke white");

        /*for(int i=0; i<50; i++){
            int centerX = ((int) Math.round(Math.random() * width));
            int centerY = ((int) Math.round(Math.random() * height));
            int radius = ((int) Math.round(Math.random() * 250.0));

            writer.print(" -draw \"circle " + centerX + ", " + centerY + ", " + (centerX+radius) + ", " + centerY + "\"");
        }*/

        createFractalCirles();


        // http://www.imagemagick.org/Usage/draw/#primitives
        //writer.print(" -draw \"circle 400, 400, 200, 400\"");
        //writer.print(" -draw \"circle 200, 400, 215, 400\"");

        writer.print(" -blur 0x8 -fx G");

        return;
    }

    private void createFractalCirles() {
        System.out.println("createFractalCirles()");

        LinkedList<CircleElement> taskList = new LinkedList<CircleElement>();
        LinkedList<CircleElement> holesToBeDrawn = new LinkedList<CircleElement>();

        // Add initial task
        double radius = getRandomBetween(height * 0.25, height * 0.5);
        taskList.add(new CircleElement(
                getRandomBetween(radius * 0.25, width - radius * 0.5),
                getRandomBetween(radius * 0.25, height - radius * 0.5),
                radius));

        int holeCount=0;

        // Loop through tasks until tasks are too small
        while (!taskList.isEmpty() && holeCount<500) {

            CircleElement task = taskList.removeFirst();

            // If radius is too small then just continue
            if(task.radius<3){
                continue;
            }

            double randomAction = Math.random();

            if(randomAction<0.35){
                // Big circle surrounded by little ones

                holesToBeDrawn.add(task);
                holeCount++;

                int surroundingCount = getRandomBetween(5, 15);
                double startRadians = getRandomBetween(0.0, TWO_PI);
                //
                double radianStep = TWO_PI / (double)surroundingCount;
                double currentRadians = startRadians;
                for(int i=0; i<surroundingCount; i++, currentRadians+=radianStep){
                    double targetAngle = currentRadians + getRandomBetween(-1.0 * radianStep*0.15, radianStep*0.15);
                    double targetCenterRDistance = task.radius * 1.3 + getRandomBetween(-1.0 * task.radius*0.3, task.radius*0.3);
                    double targetSmallRadius = task.radius * 0.35 + getRandomBetween(-1.0 * task.radius*0.35*0.3, task.radius*0.35*0.3);

                    Vector2 vCurrentTask = Vector2.ofXY(task.centerX, task.centerY);
                    Vector2 vTargetTaskRelativeToOrigin = Vector2.ofRA(targetCenterRDistance, targetAngle);
                    Vector2 vTargetTask = vCurrentTask.add(vTargetTaskRelativeToOrigin);

                    taskList.add(new CircleElement(vTargetTask.getX(), vTargetTask.getY(), targetSmallRadius));
                }
            } else {
                // Small circles inside imaginary big one

                int surroundingCount = getRandomBetween(3, 6);
                double startRadians = getRandomBetween(0.0, TWO_PI);
                //
                double radianStep = TWO_PI / (double)surroundingCount;
                double currentRadians = startRadians;
                for(int i=0; i<surroundingCount; i++, currentRadians+=radianStep){
                    double targetAngle = currentRadians + getRandomBetween(-1.0 * radianStep*0.15, radianStep*0.15);
                    double targetCenterRDistance = task.radius * 0.5 + getRandomBetween(-1.0 * task.radius*0.3, task.radius*0.3);
                    double targetSmallRadius = task.radius * 0.65 + getRandomBetween(-1.0 * task.radius*0.65*0.3, task.radius*0.65*0.3);

                    Vector2 vCurrentTask = Vector2.ofXY(task.centerX, task.centerY);
                    Vector2 vTargetTaskRelativeToOrigin = Vector2.ofRA(targetCenterRDistance, targetAngle);
                    Vector2 vTargetTask = vCurrentTask.add(vTargetTaskRelativeToOrigin);

                    CircleElement newTask = new CircleElement(vTargetTask.getX(), vTargetTask.getY(), targetSmallRadius*0.6);
                    if(Math.random()<0.5){
                        taskList.add(newTask);
                    } else {
                        holesToBeDrawn.add(newTask);
                        holeCount++;
                    }
                }
            }
        }

        for(CircleElement task : holesToBeDrawn){
            writer.print(" -draw \"circle " + (int)Math.round(task.centerX) + ", " + (int)Math.round(task.centerY) + ", " + (int)Math.round((task.centerX+task.radius)) + ", " + (int)Math.round(task.centerY) + "\"");
        }
    }

    private class CircleElement {
        private double centerX;
        private double centerY;
        private double radius;

        public CircleElement(double centerX, double centerY, double radius) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.radius = radius;
        }

    }

}
