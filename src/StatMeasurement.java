import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 Project 2 Class the holds information about measurment
 *          extremes
 */
public class StatMeasurement extends Measurement implements TimeComparable
{
    /** date of measurement */
    private GregorianCalendar dateTimeOfMeasurment;
    /** Perameter ID of measurment */
    private String paramId;
    /** StatType of measurement */
    private StatType statType;
    /** Stationid where measurement was taken */
    private String stationId;

    /**
     * empty constructor
     */
    public StatMeasurement()
    {
        // empty constructor
    }

    /**
     * Constructor for StatMeasurement
     * 
     * @param inValue
     *            numerical value of the measurment
     * @param obsDateTime
     *            time information of when the measurement was taken
     * @param inStationId
     *            station measurement was taken
     * @param inParamId
     *            type of measurement
     * @param inStatType
     *            whether the measurement is the a max, min, avg, or total
     */
    public StatMeasurement(double inValue, GregorianCalendar obsDateTime, String inStationId, String inParamId,
            StatType inStatType)
    {
        super(inValue);
        this.dateTimeOfMeasurment = obsDateTime;
        this.stationId = inStationId;
        this.paramId = inParamId;
        this.statType = inStatType;
    }

    /**
     * Gets the date the measurement was taken
     * 
     * @return dateTimeOfMeasurement
     */
    public GregorianCalendar getDateTimeOfMeasurment()
    {
        return dateTimeOfMeasurment;
    }

    /**
     * Sets the parameter id
     * 
     * @param inParamId
     *            perameter identification
     */
    public void setParamId(String inParamId)
    {
        this.paramId = inParamId;
    }

    /**
     * gets the paramId
     * 
     * @return the paramID
     */
    public String getParamId()
    {
        return paramId;
    }

    /**
     * sets the statType of the measurement
     * 
     * @param type
     *            StateType of the measurement
     */
    public void setStatType(StatType type)
    {
        this.statType = type;
    }

    /**
     * Gets the statType
     * 
     * @return statType statType of measurement
     */
    public StatType getStatType()
    {
        return statType;
    }

    /**
     * Checks to see if this measurement is less than campareWith
     * 
     * @param compareWith
     *            Measurement to compare with
     * @return true if both Measurements are valid AND this is strictly smaller than
     *         s OR if this is valid and s is not valid
     */
    public boolean isLessThan(StatMeasurement compareWith)
    {
        double thisMeasurement = this.getValue();
        double compareWithMeasurement = compareWith.getValue();

        boolean thisValid = this.isValid();
        boolean compareValid = compareWith.isValid();

        // if both measurments are invalid returnes true
        if (!thisValid && !compareValid)
        {
            return true;
        }
        // returns false if this measurment is invalid
        if (!thisValid)
        {
            return false;
        }
        // returns true if compareWith measurment is invalid
        if (!compareValid)
        {
            return true;
        }

        // returns true if mesurement is less then
        return thisMeasurement < compareWithMeasurement;
    }

    /**
     * Checks to see if this measurement is greater than campareWith
     * 
     * @param compareWith
     *            Measurement to compare with
     * @return true if both Measurements are valid AND this is strictly larger than
     *         s OR if this is valid and s is not valid
     */
    public boolean isGreaterThan(StatMeasurement compareWith)
    {
        double thisMeasurement = this.getValue();
        double compareWithMeasurement = compareWith.getValue();

        boolean thisValid = this.isValid();
        boolean compareValid = compareWith.isValid();

        // if both measurements are invalid returnes true
        if (!thisValid && !compareValid)

        {
            return true;
        }
        // returns false if this measurement is invalid
        if (!thisValid)
        {
            return false;
        }
        // returns true if compareWith measurement is invalid
        if (!compareValid)
        {
            return true;
        }
        // returns true if measurement is greater than
        return thisMeasurement > compareWithMeasurement;

    }

    /**
     * Cheaks to see if StatMeasurement is newer than the inDateTime
     * 
     * @return true if StatMeasurement is newerThan inDateTime
     * @param inDateTime
     *            date to be compared agains measurement
     */
    public boolean newerThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) == 1 ? true : false;
    }

    /**
     * Cheaks to see if StatMeasurement is older than the inDateTime
     * 
     * @return true if StatMeasurement is older inDateTime
     * @param inDateTime
     *            date to be compared agains measurement
     */
    public boolean olderThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) == -1 ? true : false;
    }

    /**
     * Compare StatMeasurements dates agains a date provided
     * 
     * @param inDateTime
     *            date of time to be compared against measurment
     * @return 1 if StatMeasurement is newerThan inDateTime, -1 if StatMeasurement
     *         is older inDateTime 0 if StatMeasurment is equal to inDateTime
     * @throws ParseException
     *             if there a problem occurs while parsing the imputed string
     */
    public int compareWithTimeString(String inDateTime) throws ParseException
    {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-mm");
        int answer = -999;
        try
        {
            Date compareDate = formatter.parse(inDateTime);
            answer = this.getDateTimeOfMeasurment().getTime().compareTo(compareDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return answer;
    }

    /**
     * @return String of StatMeasurement
     */
    @Override
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        String timeString = dateFormat.format(this.getDateTimeOfMeasurment().getTime());

        String measurementString = "";

        measurementString += measurementString.format("%5s %5s  % .4f  %3s %10s\n", paramId, statType, this.getValue(),
                stationId, timeString);

        return measurementString;
    }

}
