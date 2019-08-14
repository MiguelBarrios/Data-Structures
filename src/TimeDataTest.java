import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Barrios Davila
 * @version 27 Martch 2018 Test class for time data class
 */
public class TimeDataTest
{
    /**
     * Test for timeData constructor
     */
    @Test
    public void timeDataConstructorTest()
    {
        Measurement tair = new Measurement(5);
        Measurement ta9m = new Measurement(-10);
        Measurement solarRadiation = new Measurement(0);
        TimeData one = new TimeData("STIL", 2018, 2, 15, 5, tair, ta9m, solarRadiation, null);

        Assert.assertEquals("Incorrect stid", "STIL", one.getStationID());
        Assert.assertEquals("Incorrect year", 2018, one.getYear());
        Assert.assertEquals("Incorrect Month", 2, one.getMonth());
        Assert.assertEquals("Incorrect day", 15, one.getDay());
        Assert.assertEquals("Incorrect minute", 5, one.getMinute());
        Assert.assertEquals("Incorrect tair measurement", tair, one.getMeasurement("tair"));
        Assert.assertEquals("Incorrect ta9m measurement", ta9m, one.getMeasurement("ta9m"));
        Assert.assertEquals("Incorrect solarRadiation measurement", solarRadiation, one.getMeasurement("srad"));
    }

    /**
     * Test for timeData 2nd constructor
     */
    @Test
    public void timeDataConstructor2Test()
    {
        GregorianCalendar date = new GregorianCalendar(2018, 2, 15, 0, 5);
        Measurement tair = new Measurement(5);
        Measurement ta9m = new Measurement(-10);
        Measurement solarRadiation = new Measurement(0);

        TimeData one = new TimeData("STIL", date, tair, ta9m, solarRadiation, null);

        Assert.assertEquals("Incorrect stid", "STIL", one.getStationID());
        Assert.assertEquals("Incorrect year", 2018, one.getYear());
        Assert.assertEquals("Incorrect Month", 2, one.getMonth());
        Assert.assertEquals("Incorrect day", 15, one.getDay());
        Assert.assertEquals("Incorrect minute", 5, one.getMinute());
        Assert.assertEquals("Incorrect tair measurement", tair, one.getMeasurement("tair"));
        Assert.assertEquals("Incorrect ta9m measurement", ta9m, one.getMeasurement("ta9m"));
        Assert.assertEquals("Incorrect solarRadiation measurement", solarRadiation, one.getMeasurement("srad"));
        Assert.assertEquals("Incorrect calendar date", date, one.getMeasurementDateTime());

        Assert.assertEquals("Incorrect calendar date", date, one.getMeasurementDateTime());
    }

}
