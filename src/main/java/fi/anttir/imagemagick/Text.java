package fi.anttir.imagemagick;

import fi.anttir.helpers.TextHelper;
import fi.anttir.random.RandomHelpers;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;


/**
 * Created by anttir on 13.11.2015.
 */
public class Text {

    private String sourceImageFilename = "color-clouds.png";
    private String targetImageFilename = "text.png";
    private String targetScriptFilename = "create-colors.script";
    private String bigText = "machinename";
    private String smallText = "2010-A.D.";
    private String infoText = "InfoText";
    private String font = "";  // The filename w/o extension

    private int width = 2880;
    private int height = 1800;

    private PrintWriter writer = null;

    public Text(String[] args) {
        checkForEmptyParameters(args);

        try {
            writer = new PrintWriter(targetScriptFilename, "UTF-8");

            writer.print("convert " + sourceImageFilename);

            addTexts();

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

    private void checkForEmptyParameters(String[] args) {
        if (args == null || args != null && args.length < 1) {
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

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            if ("-bt".equals(args[i])) {
                bigText = args[i + 1];
            } else if ("-st".equals(args[i])) {
                smallText = args[i + 1];
                smallText = TextHelper.cleanString(StringUtils.newStringUtf8(Base64.decodeBase64(smallText)));
            } else if ("-ii".equals(args[i])) {
                sourceImageFilename = args[i + 1];
            } else if ("-oi".equals(args[i])) {
                targetImageFilename = args[i + 1];
            } else if ("-os".equals(args[i])) {
                targetScriptFilename = args[i + 1];
            }else if ("-font".equals(args[i])) {
                font = args[i + 1];
            }else if ("-it".equals(args[i])) {
            infoText = args[i + 1];
                infoText = TextHelper.cleanString(StringUtils.newStringUtf8(Base64.decodeBase64(infoText)));
            }
        }
    }

    private void addTexts() {
        addTopAndBottomTitle();
    }

    private void addTopAndBottomTitle() {

        // NOTE! Text's y-position is the baseline, not top-left-corner of text.

        // http://www.imagemagick.org/Usage/text/#font_info

        double bigPointSize = width / 1500.0 * 110.0;
        double smallPointSize = bigPointSize * 1.5 / 5.0;

        double xStep = width / 22.0 + 50.0;
        double yStep = height / 10.0;

        int loc_1b_x = (int) Math.round(xStep);
        int loc_1b_y = (int) Math.round(1.2 * yStep);
        int loc_1m_x = (int) Math.round(loc_1b_x + bigPointSize);

        int loc_1s_x = (int) Math.round(loc_1b_x + bigPointSize);
        int loc_1s_y = (int) Math.round(loc_1b_y + bigPointSize * 2.5 / 5.0);

        int loc_2b_x = (int) Math.round(xStep * 5.0);
        int loc_2b_y = (int) Math.round(height - yStep * 1.3 - bigPointSize / 2.0);
        int loc_2m_x = (int) Math.round(loc_2b_x + bigPointSize);
        int loc_2s_x = (int) Math.round(loc_2b_x + bigPointSize);
        int loc_2s_y = (int) Math.round(loc_2b_y + bigPointSize * 2.5 / 5.0);

        int bigTextBelow = (int) Math.round(bigPointSize*0.6);
        int smallTextBelow = (int) Math.round(smallPointSize*1.2);

        // Top text
        writer.print(getTextStamp(bigPointSize, loc_1b_x, loc_1b_y, bigText, 0));
        writer.print(getTextStamp(smallPointSize, loc_1m_x, loc_1b_y+bigTextBelow, infoText, 0));
        writer.print(getTextStamp(smallPointSize, loc_1s_x, loc_1b_y+bigTextBelow+smallTextBelow, smallText, 0));

        // Bottom text
        //writer.print(getTextStamp(bigPointSize, loc_2b_x, loc_2b_y, bigText, 0));
        //writer.print(getTextStamp(smallPointSize, loc_2m_x, loc_2b_y+bigTextBelow, infoText, 0));
        //writer.print(getTextStamp(smallPointSize, loc_2s_x, loc_2b_y+bigTextBelow+smallTextBelow, smallText, 0));



        // Lower text the other way
        loc_2b_y = (int) Math.round(height - yStep * 0.5 - bigPointSize * 0.1);
        int loc_2s_m = (int) Math.round(loc_2b_y - bigPointSize);
        loc_2s_y = (int) Math.round(loc_2s_m - smallPointSize - 0.2 * smallPointSize);

        writer.print(getTextStamp(bigPointSize, loc_2b_x, loc_2b_y, bigText, 0));
        writer.print(getTextStamp(smallPointSize, loc_2m_x, loc_2s_m, infoText, 0));
        writer.print(getTextStamp(smallPointSize, loc_2s_x, loc_2s_y, smallText, 0));





/*
        double verticalBigPointSize = bigPointSize / 3.0;
        double verticalSmallPointSize = smallPointSize / 2.0;

        int right_b_x = (int) Math.round(width - xStep);
        int right_b_y = (int) Math.round(height - yStep);
        int right_s_x = (int) Math.round(width - xStep*0.7);
        int right_s_y = (int) Math.round(height - yStep*1.3);

        // Right text
        writer.print(getTextStamp(verticalBigPointSize, 1000, 1000, bigText, 0));
        writer.print(getTextStamp(verticalSmallPointSize, 1200, 1200, smallText, -10));*/
    }

    private String getTextStamp(double pointSize, int x, int y, String text, int rotate) {
        String value = " ";

        for (int i = 1; i < 7; i++) {
            value += getSingleText(pointSize, x + i, y + i, text, "black", rotate);
        }

        value += getSingleText(pointSize, x, y, text, "white", rotate);

        return value;
    }

    private String getSingleText(double pointSize, int x, int y, String text, String fillColor, int rotate) {
        String value = " ";

        if(font!=null && font.length()>0){
            value += "-font '" + font + "' ";
        }
        value += "-pointsize " + pointSize + " -fill " + fillColor + " -stroke black -draw '";
        if(rotate!=0) {
            value += " rotate " + rotate;
            value += " translate " + x + " " + y;
            value += " text 0 0";
            value += " \"" + text + "\"";

        } else {
            value += "text " + x + " " + y;
            value += " \"" + text + "\"";
        }
        value += "'";

        return value;
    }

    private String getSingleText(double pointSize, int x, int y, String text, String fillColor) {
        return getSingleText(pointSize, x, y, text, fillColor, 0);
    }

    public static void main(String[] args) {
        Text cc = new Text(args);
    }
}
