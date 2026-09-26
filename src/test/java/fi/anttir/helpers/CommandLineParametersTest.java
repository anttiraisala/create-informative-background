package fi.anttir.helpers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anttir on 18.11.2015.
 */
public class CommandLineParametersTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test(){

        CommandLineParameters clp = new CommandLineParameters();

        clp.addParameter(clp.new Parameter("TEST", "t", "test", "Test value"));
        clp.addParameter(clp.new Parameter("SUPER", "s", "super", "Doyle Brunson's Super System"));
        assertFalse(clp.hasUserEnteredParameter("TEST"));
        assertEquals("Test value", clp.getParameterValueForKey("TEST"));


        clp.parseCommandLineParameters(new String[]{"--test", "value 2"});

        assertTrue(clp.hasUserEnteredParameter("TEST"));
        assertEquals("value 2", clp.getParameterValueForKey("TEST"));


        assertFalse(clp.hasUserEnteredParameter("WILL_NOT_BE_FOUND"));

    }
}