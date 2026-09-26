package fi.anttir.imagemagick;

import fi.anttir.random.RandomHelpers;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by anttir on 13.11.2015.
 */
public class ColorClouds {

    private String targetImageFilename = "color-clouds.png";
    private String targetScriptFilename = "create-colors.script";
    private int width = 2880;
    private int height = 1800;

    private PrintWriter writer = null;

    public ColorClouds(String[] args){
        checkForEmptyParameters(args);

        try {
            writer = new PrintWriter(targetScriptFilename, "UTF-8");

            writer.print("convert");
            addSize();
            writer.print(" plasma:fractal");
            addBlur();
            writer.print(" -colorspace Gray");
            addLevelcolors();
            writer.print(" " + targetImageFilename);

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

    private void checkForEmptyParameters(String[] args){
        if(args==null || args!=null && args.length<1){
            System.out.print("Usage: ColorClouds outputfilename scriptfilename");
            System.out.print("\tExample: ColorClouds colors.png create-colors.script");
            System.out.println("\tRun by: " +
                    "\n" +
                    "\t\t$ CMD=\"$(cat test.script)\"" +
                    "\n\t\t$ eval $CMD");

            System.out.print("\tor by:" +
                    "\n\t\t$ eval \"$(cat create-colors.script)\"");


            /*

            CMD="$(cat test.script)"
            eval $CMD

            eval "$(cat create-colors.script)"

             */


                    System.exit(1);
        }

        for(int i=0; i<args.length; i++){
            if("-oi".equals(args[i])){
                targetImageFilename = args[i+1];
            } else
            if("-os".equals(args[i])){
                targetScriptFilename = args[i+1];
            }
        }
    }

    private void addSize() {
        writer.print(" -size " + width + "x" + height);
    }


    private void addBlur() {
        writer.print(" -blur 0X" + RandomHelpers.getRandomBetween(0, 10));
    }

    private void addLevelcolors() {
        Color c1 = new Color();
        Color c2 = new Color();
        getColors(c1, c2);
        writer.print(" +level-colors 'rgba(" + c1.r + ", " + c1.g + ", " + c1.b + ", 1.0)','rgba(" + c2.r + ", " + c2.g + ", " + c2.b +", 1.0)'");
    }

    private void getColors(Color c1, Color c2){

        List<Colors> colors = new LinkedList<Colors>();
        colors.add(new Colors(new Color(30, 30, 80), new Color(230, 50, 230))); // purple - blue
        colors.add(new Colors(new Color(100, 100, 80), new Color(230, 20, 230))); // purple - yellow dull greenish
        colors.add(new Colors(new Color(200, 200, 80), new Color(230, 200, 230))); // yellow
        colors.add(new Colors(new Color(15, 60, 80), new Color(15, 150, 80))); // nice dark waving green

        colors.add(new Colors(new Color(220, 140, 50), new Color(15, 150, 80))); // yellow - green
        colors.add(new Colors(new Color(30, 30, 80), new Color(230, 50, 230))); // purple - blue
        colors.add(new Colors(new Color(160, 30, 50), new Color(210, 160, 10))); // red - orange
        colors.add(new Colors(new Color(50, 50, 50), new Color(130, 130, 130)));  // smoke like gray


        int c = (int)(Math.random()*colors.size());

        Colors selectedColors = colors.get(c);

        c1.r=selectedColors.c1.r;
        c1.g=selectedColors.c1.g;
        c1.b=selectedColors.c1.b;
        c2.r=selectedColors.c2.r;
        c2.g=selectedColors.c2.g;
        c2.b=selectedColors.c2.b;
    }


    public static void main(String[] args) {
        ColorClouds cc = new ColorClouds(args);
    }

    private class Color{
        int r;
        int g;
        int b;

        public Color(){};
        public Color(int r, int g, int b){
            this.r=r;
            this.g=g;
            this.b=b;
        }
    }

    private class Colors{
        Color c1;
        Color c2;

        public Colors(){};
        public Colors(Color c1, Color c2){
            this.c1=c1;
            this.c2=c2;
        }
    }


}
