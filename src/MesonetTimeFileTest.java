import java.io.IOException;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Barrios Davila
 * @version 12 February 2018 Poject 2 Test Class for mesonetTimeFile Class
 */
public class MesonetTimeFileTest
{
    /**
     * Test for MesonetTimeFile constructor
     * 
     * @throws IOException
     *             if invalid copyright is detected
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     */
    @Test
    public void mesonetTimeFileTest() throws IOException, WrongCopyrightException
    {
        MesonetTimeFile one = new MesonetTimeFile("data/mesonet/20180102stil.mts");

        String actual = one.getStarDateTimeStringFromFile();
        String expected = "2018-02-02T00:00:00 CST";

        Assert.assertEquals("incorrect start day string", expected, actual);
        Assert.assertEquals("Incorrect get file name", "data/mesonet/20180102stil.mts", one.toString());

    }

    /**
     * Test for parseFile/data method
     * 
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     */
    @Test
    public void parseFileAndDataTest() throws IOException, WrongCopyrightException
    {
        MesonetTimeFile one = new MesonetTimeFile("data/mesonet/20180102stil.mts");

        int actual = one.parseFile().size();
        Assert.assertEquals("Size if data recored incorrect", 288, actual);
    }

    /**
     * Test for cs abstract file class
     * 
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             invalid copyright is detected
     */
    @Test
    public void csAbstractFileTest() throws IOException, WrongCopyrightException
    {
        MesonetTimeFile one = new MesonetTimeFile("data/mesonet/20180101nrmn.mts");

        Assert.assertTrue("Incorrect file exis method", one.exists());

    }

    /**
     * Test CsFileCompareTo
     * 
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     * @throws ParseException
     *             if there is a problem with the input or output
     */
    @Test
    public void csFileCompareTo() throws IOException, WrongCopyrightException, ParseException
    {
        // fileDate 20180105 format
        MesonetTimeFile file20180105 = new MesonetTimeFile("data/mesonet/20180105stil.mts");

        // yyyymmdd format
        Assert.assertTrue("newer than method incorrect", file20180105.newerThan("20180101"));
        Assert.assertFalse("newerthan method incorrect", file20180105.newerThan("20180130"));
        Assert.assertTrue("newer than method incorrect", file20180105.olderThan("20180110"));
        Assert.assertFalse("newerthan method incorrect", file20180105.olderThan("20180104"));

        int actual = file20180105.compareWithTimeString("20180105");
        Assert.assertEquals("Compare to method incorrect", 0, actual);
    }
}
