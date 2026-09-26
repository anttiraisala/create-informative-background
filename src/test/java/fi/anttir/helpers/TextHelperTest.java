package fi.anttir.helpers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by anttir on 9.1.2016.
 */
public class TextHelperTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCleanString() throws Exception {
        String s = "a";
        String sExpected = "a";
        String sActual = TextHelper.cleanString(s);

        assertEquals(sExpected, sActual);

        s = " ab   cd   ";
        sExpected = "ab cd";
        sActual = TextHelper.cleanString(s);

        assertEquals(sExpected, sActual);

        s = " ab \"\" '' cd  ' ";
        sExpected = "ab cd";
        sActual = TextHelper.cleanString(s);

        assertEquals(sExpected, sActual);
    }
}
