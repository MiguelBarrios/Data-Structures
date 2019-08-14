import java.io.IOException;
import java.text.ParseException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Testing Class for DayData Class.
 * 
 * @author Miguel Barrios Davila
 * @version 2018-02-10
 */
public class DaysStatisticsTest
{
    /**
     * Test for dayStatistics constructor
     * 
     * @throws WrongParameterIdException
     *             if invalid parameter is passed
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     * @throws ParseException
     *             if there a problem occurs while parsing the imputed string
     */
    @Test
    public void daysStatisticsTest()
            throws WrongParameterIdException, IOException, WrongCopyrightException, ParseException
    {
        String[] files = { "data/mesonet/20180102stil.mts", "data/mesonet/20180102okcn.mts" };
        DaysStatistics stats = new DaysStatistics(files);

        stats.findStatistics();

        double expected = -12.7;
        StatMeasurement actual = stats.getMinimumDay("TAIR");
        Assert.assertEquals("incorect minimum measurement", expected, actual.value, 0.00);

        expected = -5.6;
        actual = stats.getMaximumDay("TA9M");
        Assert.assertEquals("incorect maximum measurement", expected, actual.value, 0.00);
    }

    /**
     * Test for combineMaxMinStats
     * 
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     * @throws ParseException
     *             if there a problem occurs while parsing the imputed string
     * @throws WrongParameterIdException
     *             if invalid parameter is passed
     */
    @Test
    public void combineMaxMinStatsTest()
            throws IOException, WrongCopyrightException, ParseException, WrongParameterIdException
    {
        String[] files = { "data/mesonet/20180102stil.mts", "data/mesonet/20180102okcn.mts" };
        DaysStatistics stats = new DaysStatistics(files);

        stats.findStatistics();

        String expected = " TAIR   MAX  -5.3000  STIL 2018-02-02T21:40:00 CST\n" + "\n"
                + " TAIR   MIN  -12.7000  OKCN 2018-02-02T07:10:00 CST\n\n";
        String actual = stats.combineMinMaxStatistics("tair");
        Assert.assertEquals("Incorrect combineMaxMin to string", expected, actual);

    }

    /**
     * Test for daysStatistics to string
     * 
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     * @throws ParseException
     *             if there a problem occurs while parsing the imputed string
     */
    @Test
    public void daysStatisticsToString() throws IOException, WrongCopyrightException, ParseException
    {
        String[] files = { "data/mesonet/20180102stil.mts", "data/mesonet/20180102okcn.mts" };
        DaysStatistics stats = new DaysStatistics(files);

        stats.findStatistics();

        String expected = "   ID  STAT    VALUE  STID     DATE T TIME      TZ\n"
                + "__________________________________________________\n"
                + " TAIR   MAX  -5.3000  STIL 2018-02-02T21:40:00 CST\n" + "\n"
                + " TAIR   MIN  -12.7000  OKCN 2018-02-02T07:10:00 CST\n" + "\n"
                + " TA9M   MAX  -5.6000  STIL 2018-02-02T21:40:00 CST\n" + "\n"
                + " TA9M   MIN  -12.1000  OKCN 2018-02-02T06:25:00 CST\n" + "\n"
                + " SRAD   MAX   447.0000  STIL 2018-02-02T18:50:00 CST\n" + "\n"
                + " SRAD   MIN   0.0000  STIL 2018-02-02T00:00:00 CST\n\n";
        String actual = stats.toString();
        Assert.assertEquals("Incorrect days statistics to string", expected, actual);
    }
}
