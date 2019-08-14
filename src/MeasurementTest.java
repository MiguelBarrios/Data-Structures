import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Barrios Davila
 * @version 27 Martch 2018 Test class for measurment class
 */
public class MeasurementTest
{
    /**
     * Test for measurment constructor
     */
    @Test
    public void measurementConstructorTest()
    {
        Measurement emptyTest = new Measurement();
        Assert.assertEquals("Incorrect tair value", Double.NaN, emptyTest.getValue(), 0.0);
        Assert.assertFalse("Measurment validity incorrect", emptyTest.isValid());

        Measurement validMeasurement = new Measurement(5);
        Assert.assertEquals("Incorrect tair value", 5, validMeasurement.getValue(), 0.0);
        Assert.assertTrue("Measurment validity incorrect", validMeasurement.isValid());

        Measurement invalidMeasurement = new Measurement(-999);
        Assert.assertEquals("Incorrect tair value", -999, invalidMeasurement.getValue(), 0.0);
        Assert.assertFalse("Measurment validity incorrect", invalidMeasurement.isValid());

        String expected = "5.0000";
        String actual = validMeasurement.toString();
        Assert.assertEquals("Incorrect to Measurment to String", expected, actual);
        Assert.assertEquals("bad", invalidMeasurement.toString());
    }

}
