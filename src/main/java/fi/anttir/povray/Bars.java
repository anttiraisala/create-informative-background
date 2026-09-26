package fi.anttir.povray;

//import fi.fxstudio.random.BiasedRandom;


// http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java
// http://www.povray.org/documentation/3.7.0/t2_2.html#t2_2
// povray create-bg.pov +w2000 +h2000 +a0.1 && pinta create-bg.png
// povray create-bg.pov +w500 +h500 +a0.1 && pinta create-bg.png
// cat Bars.java | base64 -w0

// http://www.f-lohmueller.de/pov_tut/tex/tex_890e.htm


import fi.anttir.random.RandomHelpers;

import java.io.PrintWriter;

public class Bars {

    private static final double PI = 3.14159265359;
    double diagonalRadius = 20;
    private PrintWriter writer = null;
    private double maxBarHeight = Double.MIN_VALUE;
    private double minBarHeight = Double.MAX_VALUE;
    private double barThickness = 0.0;

    private Bars() {

        //System.out.println("Biased=[" + BiasedRandom.getSomething() + "]");
        //System.out.println("Biased BIAS=[" + BiasedRandom.Bias.BELL_CURVE + "]");

        try {
            writer = new PrintWriter("create-bg.pov", "UTF-8");

            addHeaders();
            addMaterials();
            addObjects();
            // Light and camera is created after objects because the extends of objects affect the light's and camera's position.
            addLights();
            addCamera();

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

    public static void main(String[] args) {
        Bars m = new Bars();
    }

    private void addHeaders() {
        writer.println("#include \"colors.inc\"    // The include files contain");
        writer.println("#include \"stones.inc\"    // pre-defined scene elements");
        writer.println("#include \"textures.inc\"    // pre-defined scene elements");
        writer.println("#include \"shapes.inc\"");
        writer.println("#include \"glass.inc\"");
        writer.println("#include \"metals.inc\"");
        writer.println("#include \"woods.inc\"");

        writer.println("");
        writer.println("");
        writer.println("// povray create-bg.pov +w2880 +h1800 +a0.1 && pinta create-bg.png");
    }

    private void addMaterials() {
        writer.println();

    }

    private void addLights() {
        writer.println();

        // Lamp's Y pos should be proportional to bar area's width

        writer.println("light_source{<" + (Math.random() * 2.0 * diagonalRadius - diagonalRadius) + ", " + Math.max((maxBarHeight + (Math.random() * 7.0 + 0.5) * barThickness), (diagonalRadius * 2.0 / 4.0)) + ", " + (Math.random() * 2.0 * diagonalRadius - diagonalRadius) + "> color White}");
    }

    private void addCamera() {
        writer.println();

        double angle = Math.random() * PI / 2.0 - PI / 4.0;
        writer.println("// angle=" + angle);

        writer.println("camera {\n" +
                "    location <0, " + (maxBarHeight + 3.0 * barThickness) + ", -" + (random(diagonalRadius * 0.4, diagonalRadius * 1.1)) + ">\n" +
                "    look_at  <0, 0,  0>\n" +
                "sky<" + Math.sin(angle) + "," + Math.cos(angle) + ",0>\n" +
                // http://www.povray.org/documentation/view/3.7.0/246#s02_03_01_01_05
                "up<0,1800,0>" +
                "right<2880,0,0>" +
                //"up<0,1,0>" +
                //"right<1,0,0>" +
                "angle 45             // FOV\n" +
                "  }");
    }

    private void addObjects() {
        writer.println();

        createBars();

    }

    private void createBars() {
        writer.println();

        int barDivisor = (int) Math.round(random(20.0, 60.0));// barDivisor=(int)Math.round(Math.sqrt(30000.0));
        barThickness = 2.0 * diagonalRadius / (double) barDivisor;
        double roundness = barThickness / 16.0;
        double randomizedCenterDistance = (Math.random() * 2.0 + 3.0);

        for (int y = 0; y < barDivisor; y++) {
            for (int x = 0; x < barDivisor; x++) {

                double choke = Math.pow(Math.random(), 2.0) * 0.1 * barThickness;
/*
                if(between(x, barDivisor/2-1, barDivisor/2+1) && between(y, barDivisor/2-1, barDivisor/2+1))
                    continue;
*/
                //double startX = -1.0 * diagonalRadius + x * barThickness;
                double startX = -0.5 * barThickness;
                double endX = startX + barThickness;
                //double startY = -1.0 * diagonalRadius + y * barThickness;
                double startY = -0.5 * barThickness;
                double endY = startY + barThickness;
                double translateX = -1.0 * diagonalRadius + 0.5 * barThickness + (double) x * barThickness;
                double translateY = -1.0 * diagonalRadius + 0.5 * barThickness + (double) y * barThickness;
                double middleX = (endX + translateX + startX + translateX) / 2.0;
                double middleY = (endY + translateY + startY + translateY) / 2.0;
                double centerDistance = Math.sqrt(middleX * middleX + middleY * middleY) / diagonalRadius * randomizedCenterDistance;
                double perfectHeight = centerDistance * centerDistance * barThickness;

                double rotateX = RandomHelpers.getRandomBetween(-4.0, 4.0);
                double rotateY = RandomHelpers.getRandomBetween(-8.0, 8.0)*Math.pow(Math.random(), 1.5);
                double rotateZ = RandomHelpers.getRandomBetween(-4.0, 4.0);

                // Calculate bar's height and store max/min -values.
                double height = perfectHeight - (Math.random() * barThickness * 1.5 + 0.5);
                if (height > maxBarHeight)
                    maxBarHeight = height;
                if (height < minBarHeight)
                    minBarHeight = height;

                double r = (Math.random() * .25 - 0.125 + 0.5);
                double g = (Math.random() * .25 - 0.125 + 0.5);
                double b = (Math.random() * .25 - 0.125 + 0.5);
                r = g = b = (Math.random() * .25 * 1.33 - 0.125 * 1.33 + 0.5);
                //r = g = b = 0.5;

                writer.println("object {\n" +
                        " Round_Box(<" + (startX + choke) + ",-100," + (startY + choke) + ">,<" + (endX - choke) + "," + height + "," + (endY - choke) + ">, " + roundness + ", true)\n" +
                        " texture{\n");
                //"   pigment{ color rgb<0.5,0.5,0.5>}\n" +


                if (Math.random() < 0.995) {
                    writer.println("   pigment{ color rgb<" + r + ", " + g + ", " + b + ">} finish { phong 1}");
                } else {
                    //writer.println("  Gold_Metal");
                    //writer.println("  pigment{Col_Glass_Clear}");
                    //writer.println("  pigment{Pink_Granite}");
                    //writer.println("  Glass");
                    //writer.println("  normal{crackle 1.0 scale 0.012}");
                    writer.println("  pigment{ radial frequency 15} finish { diffuse 0.9 specular 0.7} rotate<" + random(0.0, 90.0) + ", " + random(0.0, 90.0) + ", " + random(0.0, 90.0) + "> scale<" + random(0.7, 2.0) + ", " + random(0.7, 2.0) + ", " + random(0.7, 2.0) + ">// end of texture");
                }

                //writer.println("  pigment{ radial frequency 15} finish { diffuse 0.9 specular 0.7} // end of texture");

                //"normal{crackle 1.0 scale 0.012}" +


                writer.println("}");
                writer.println(" translate <" + translateX + ", 0, " + translateY + ">");
                //writer.println(" rotate<" + rotateX + ", " + rotateY + ", " + rotateZ + ">");
                writer.println(" rotate<0, " + rotateY + ", 0>");
                writer.println("}");


/*
                writer.println("object {\n" +
                        " Round_Box(<" + (x+bulge) + ",2," + (y+bulge) +">,<" + (x+1.0-bulge) + "," + (Math.random()*2.0+3.0) + "," + (y+1.0-bulge) +">, 0.125*3.0/4.0, 0)\n" +
                        " texture{\n" +
                        "   pigment{ color rgb<0.5,0.5,0.5>}\n" +
                        "   finish { phong 1}\n" +
                        " }\n" +
                        "}");*/
            }
        }
    }

    public double random(double min, double max) {

        double dynamics = max - min;
        double halfDynamics = 0.5 * dynamics;

        return Math.random() * dynamics + min;
    }

    public boolean between(int value, int min, int max) {
        if (value >= min && value <= max)
            return true;

        return false;
    }

}
