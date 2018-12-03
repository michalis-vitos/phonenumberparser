package gr.michalisvitos.phoneparser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class NumberParserTest {

    private static NumberParser parser;

    @BeforeClass
    public static void setupCountryCodes() {

        System.out.println("Initialise the country codes and prefixes:");
        Map<String, Integer> countryCodes = new HashMap<>();
        Map<String, String> prefixes = new HashMap<>();

        // GB
        countryCodes.put("GB", 44);
        prefixes.put("GB", "0");

        // UK
        countryCodes.put("US", 1);
        prefixes.put("US", "1");

        parser = new NumberParser(countryCodes, prefixes);
    }

    @Test
    public void testEmptyStrings() {
        Assert.assertEquals("", parser.parse("", "+441614960148"));
        Assert.assertEquals("", parser.parse("+441614960148", ""));
        Assert.assertEquals("", parser.parse("", ""));
    }

    @Test
    public void testUK() {
        Assert.assertEquals("+442079460056", parser.parse("02079460056", "+441614960148"));
        Assert.assertEquals("+442079460056", parser.parse("+442079460056", "+441614960148"));
    }

    @Test
    public void testUSA() {
        Assert.assertEquals("+15417549999", parser.parse("15417549999", "+15417543010"));
        Assert.assertEquals("+15417549999", parser.parse("+15417549999", "+15417543010"));
    }
}
