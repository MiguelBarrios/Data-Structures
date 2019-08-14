import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.GregorianCalendar;

/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 Project 2 Test class for statMeasurment
 */
public class StatMeasurementTest
{
    /**
     * Test for the StatMeasurement Constructor
     */
    @Test
    public void statMeasurementConstructorTest()
    {
        StatMeasurement test = new StatMeasurement();
        Assert.assertFalse(test.valid);

        GregorianCalendar date = new GregorianCalendar(2018, 2, 15, 0, 5);
        StatMeasurement one = new StatMeasurement(5, date, "NRNM", "TAIR", StatType.MAX);

        Assert.assertEquals("Incorrect get date Method", date, one.getDateTimeOfMeasurment());
        Assert.assertEquals("Incorrect Paramid", "TAIR", one.getParamId());
        Assert.assertEquals("Incorrect statType", StatType.MAX, one.getStatType());
    }

    /**
     * Test for statMeasurement CompareTo method
     */
    @Test
    public void statMeasurmentCompareToTest()
    {

        GregorianCalendar date = new GregorianCalendar(2018, 2, 15, 0, 5);
        // only the value and and if the measurement is valid matter for testest
        StatMeasurement validLessThanStat = new StatMeasurement(5, date, "NRNM", "TAIR", StatType.MAX);
        StatMeasurement validGreaterThanStat = new StatMeasurement(7, date, "NRNM", "TAIR", StatType.MAX);
        StatMeasurement validEqualtoLessThanStat = new StatMeasurement(5, date, "NRNM", "TAIR", StatType.MAX);
        StatMeasurement invalidMeasurmentOne = new StatMeasurement(-999, date, "NRNM", "TAIR", StatType.MAX);
        StatMeasurement invalidMeasurmentTwo = new StatMeasurement(-999, date, "NRNM", "TAIR", StatType.MAX);

        Assert.assertTrue("Incorrect compare to method value", validLessThanStat.isLessThan(validGreaterThanStat));
        Assert.assertFalse("Incorrect compare to method value", validLessThanStat.isLessThan(validEqualtoLessThanStat));
        Assert.assertFalse("Incorrect compare to method value", validGreaterThanStat.isLessThan(validLessThanStat));
        Assert.assertTrue("Incorrect compare to method value", validLessThanStat.isLessThan(invalidMeasurmentOne));
        Assert.assertFalse("Incorrect compare to method value", invalidMeasurmentOne.isLessThan(validLessThanStat));
        Assert.assertTrue("Incorrect compare to method value", invalidMeasurmentTwo.isLessThan(invalidMeasurmentTwo));

        Assert.assertFalse("Incorrect compare to method value", validLessThanStat.isGreaterThan(validGreaterThanStat));
        Assert.assertFalse("Incorrect compare to method value",
                validLessThanStat.isGreaterThan(validEqualtoLessThanStat));
        Assert.assertTrue("Incorrect compare to method value", validGreaterThanStat.isGreaterThan(validLessThanStat));
        Assert.assertTrue("Incorrect compare to method value", validLessThanStat.isGreaterThan(invalidMeasurmentOne));
        Assert.assertFalse("Incorrect compare to method value", invalidMeasurmentOne.isGreaterThan(validLessThanStat));
        Assert.assertTrue("Incorrect compare to method value",
                invalidMeasurmentOne.isGreaterThan(invalidMeasurmentTwo));

    }

    /**
     * Test for compareWithTimeString method
     * 
     * @throws ParseException
     *             if there a problem occurs while parsing the imputed string
     */
    @Test
    public void compareWithTimeStringTest() throws ParseException
    {
        // format yyyy-MM-dd-mm
        GregorianCalendar date1 = new GregorianCalendar(2018, 2, 15, 0, 0);
        StatMeasurement measurment = new StatMeasurement(5, date1, "NRNM", "TAIR", StatType.MAX);

        Assert.assertEquals("Incorrect compare with time string method. equal to", 0,
                measurment.compareWithTimeString("2018-03-15-00"));
        Assert.assertEquals("Incorrect compare with time string method newer than", 1,
                measurment.compareWithTimeString("2018-02-15-00"));
        Assert.assertEquals("Incorrect compare with time string method older than", -1,
                measurment.compareWithTimeString("2018-03-45-00"));

        Assert.assertTrue("Incorrect compare with time string method newer than",
                measurment.newerThan("2018-02-15-00"));
        Assert.assertTrue("Incorrect compare with time string method older than",
                measurment.olderThan("2018-03-45-00"));
    }

    /**
     * Test for the StatMeasurement setters
     */
    @Test
    public void statMeasurementSettersTest()
    {
        GregorianCalendar date = new GregorianCalendar(2018, 2, 15, 0, 5);
        StatMeasurement one = new StatMeasurement(5, date, "NRNM", "TAIR", StatType.MAX);
        one.setParamId("SRAD");
        one.setStatType(StatType.MIN);

        Assert.assertEquals("Incorrect Paramid", "SRAD", one.getParamId());
        Assert.assertEquals("Incorrect statType", StatType.MIN, one.getStatType());
    }

    /**
     * Test for StatMeasurement toString
     */
    @Test
    public void statMeasurementToStringTest()
    {
        GregorianCalendar date = new GregorianCalendar(2018, 2, 15, 0, 5);
        StatMeasurement one = new StatMeasurement(5, date, "NRNM", "TAIR", StatType.MAX);

        String expected = " TAIR   MAX   5.0000  NRNM 2018-03-15T00:05:00 CDT\n";

        String actual = one.toString();
        Assert.assertEquals("incorrect to string", expected, actual);

    }
}
