import java.text.ParseException;

/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 comparable interface
 */
public interface TimeComparable
{
    /**
     * Checks to see if object is newerThan inputed date
     * 
     * @param inDateTimeStr
     *            date to be compared agains measurement
     * @return true if StatMeasurement is newerThan inDateTime
     * @throws ParseException
     *             if there a problem occurs while parsing the imputed string
     */
    boolean newerThan(String inDateTimeStr) throws ParseException;

    /**
     * Checks to see if object is newerThan inputed date
     * 
     * @param inDateTimeStr
     *            Date that object will be compared against
     * @return true if StatMeasurement is older inDateTime
     * @throws ParseException
     *             if there a problem occurs while parsing the imputed string
     */
    boolean olderThan(String inDateTimeStr) throws ParseException;
}