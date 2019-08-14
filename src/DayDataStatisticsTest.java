import java.io.IOException;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Barrios Davila
 * @version 22 March 2018 Test Class for data data statistics class
 */
public class DayDataStatisticsTest
{
    /**
     * Test constructor for dayDataStatistics class
     * 
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     */
    @Test
    public void dayDataStatisticsConstructorTest() throws IOException, WrongCopyrightException
    {
        MesonetTimeFile file = new MesonetTimeFile("data/mesonet/20180101nrmn.mts");
        ArrayList<TimeData> data = file.parseFile();

        DayDataStatistics dayStats = new DayDataStatistics(data);

        // the two test below will still be needed
        Assert.assertEquals("Incorrect get SRAD total", 40270, dayStats.getSolarRadiationTotal().value, 0.001);
        Assert.assertEquals("Incorrect station id", "NRMN", dayStats.getStationID());
    }

    /**
     * dayDataStatistics to String test
     * 
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     */
    @Test
    public void dayDataStatisticsTest() throws IOException, WrongCopyrightException
    {
        MesonetTimeFile file = new MesonetTimeFile("data/mesonet/20180101nrmn.mts");
        ArrayList<TimeData> data = file.parseFile();

        DayDataStatistics dayStats = new DayDataStatistics(data);

        String expected = "   ID  STAT    VALUE  STID     DATE T TIME      TZ\n"
                + " TAIR   MAX  -6.5000  NRMN 2018-02-01T22:00:00 CST\n"
                + " TAIR   MIN  -14.9000  NRMN 2018-02-01T13:25:00 CST\n"
                + " TA9M   MAX  -6.9000  NRMN 2018-02-01T22:00:00 CST\n"
                + " TA9M   MIN  -14.8000  NRMN 2018-02-01T13:40:00 CST\n"
                + " SRAD   MAX   564.0000  NRMN 2018-02-01T18:30:00 CST\n"
                + " SRAD   MIN   0.0000  NRMN 2018-02-01T00:00:00 CST\n";
        String actual = dayStats.toString();
        Assert.assertEquals("incorrect dayDataStatistics to String", expected, actual);
    }

    /**
     * Test for getting the measurement min
     * 
     * @throws IOException
     *             if there is probem with the file input our output
     * @throws WrongCopyrightException
     *             if the file has an incorrect copyright exception
     */
    @Test
    public void getMeasurementMin() throws IOException, WrongCopyrightException
    {
        MesonetTimeFile file = new MesonetTimeFile("data/mesonet/20180101nrmn.mts");
        ArrayList<TimeData> data = file.parseFile();

        DayDataStatistics dayStats = new DayDataStatistics(data);

        Assert.assertEquals("Incorrect get tair min ", -14.9, dayStats.getMeasurementMin("tair").value, 0.001);
        Assert.assertEquals("Incorrect get ta9r min", -14.8, dayStats.getMeasurementMin("ta9m").value, 0.001);
        Assert.assertEquals("Incorrect get SRAD min", 0, dayStats.getMeasurementMin("srad").value, 0.001);
    }

    /**
     * Test for getting measurement max
     * 
     * @throws IOException
     *             if there is a problom with the input or output
     * @throws WrongCopyrightException
     *             if the file has an invalid copyright
     */
    @Test
    public void getMeasurementMax() throws IOException, WrongCopyrightException
    {
        MesonetTimeFile file = new MesonetTimeFile("data/mesonet/20180101nrmn.mts");
        ArrayList<TimeData> data = file.parseFile();

        DayDataStatistics dayStats = new DayDataStatistics(data);
        Assert.assertEquals("Inncorrect get tair max", -6.5, dayStats.getMeasurementMax("tair").value, 0.001);
        Assert.assertEquals("Incorrect get ta9r max", -6.9, dayStats.getMeasurementMax("ta9m").value, 0.001);
        Assert.assertEquals("Incorrect get SRAD max, actual", 564, dayStats.getMeasurementMax("srad").value, 0.001);
    }

    /**
     * Test for getting measurment average
     * 
     * @throws IOException
     *             throws if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             throws if file has invalid copyright exception
     */
    @Test
    public void getMeasurementAvg() throws IOException, WrongCopyrightException
    {
        MesonetTimeFile file = new MesonetTimeFile("data/mesonet/20180101nrmn.mts");
        ArrayList<TimeData> data = file.parseFile();

        DayDataStatistics dayStats = new DayDataStatistics(data);
        Assert.assertEquals("Incorrect get tair average", -10.8930, dayStats.getMeasurementAvg("tair").value, 0.001);
        Assert.assertEquals("Incorrect ta9m average", -10.9725, dayStats.getMeasurementAvg("ta9m").getValue(), 0.001);
        Assert.assertEquals("Incorrect get SRAD average", 139.8263, dayStats.getMeasurementAvg("srad").value, 0.001);

    }

}
