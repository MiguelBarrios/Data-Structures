import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * 
 * @author CS2334. Modified by: Rafal Jabrzemski
 *         <P>
 *         Date: 2018-02-01 <BR>
 *         Project 1
 *         <P>
 *         This class represents a summary of one time's data from a single
 *         Mesonet station.
 * @version 2018-01-01
 * 
 *
 */
public class TimeData
{
    /**
     * Holds a calendar object for the time instance
     */
    private GregorianCalendar measurementDateTimeUTC;

    /**
     * Station data was collected
     */
    private String stationID;

    /**
     * tair, ta9m and solarRadiation measurments
     */
    private HashMap<String, Measurement> measurementMap;

    /**
     * 
     * @param stationID
     *            station id were the measurement was taken
     * @param year
     *            year measurement was taken
     * @param month
     *            month measurement was taken
     * @param day
     *            day measurement was taken
     * @param minute
     *            minut measurement was taken
     * @param tair
     *            temperature at ground level
     * @param ta9m
     *            temperature 9 meters from the ground
     * @param solarRadiation
     *            amount of solar radiation
     * @param wspd
     *          wind speed
     */
    public TimeData(String stationID, int year, int month, int day, int minute, Measurement tair, Measurement ta9m,
            Measurement solarRadiation, Measurement wspd)
    {
        this.stationID = stationID;
        setDateTimeComponets(year, month, day, minute);
        setMeasurements(tair, ta9m, solarRadiation, wspd);
    }

    /**
     * 
     * @param inStationID
     *            station id were the measurement was taken
     * @param dateTime
     *            gregorian calendar contaning information of when the measurement
     *            was taken
     * @param tair
     *            temperature at ground level
     * @param ta9m
     *            temperature 9 meters form the ground
     * @param solarRadiation
     *            amount of solar radiation
     * @param wspd
     *             wind speed
     */
    public TimeData(String inStationID, GregorianCalendar dateTime, Measurement tair, Measurement ta9m,
            Measurement solarRadiation, Measurement wspd)
    {
        this.stationID = inStationID;
        this.measurementDateTimeUTC = dateTime;
        setMeasurements(tair, ta9m, solarRadiation, wspd);
    }

    /**
     * Sets the Gregorian Calendar to the date specified
     * 
     * @param year
     *            year data was collected
     * @param month
     *            month data was collected
     * @param day
     *            day data was collected
     * @param minute
     *            minute data was collected
     */
    private void setDateTimeComponets(int year, int month, int day, int minute)
    {
        this.measurementDateTimeUTC = new GregorianCalendar(year, month, day, 0, minute);
    }

    /**
     * 
     * @param temp
     *            temperature at ground level
     * @param temp9m
     *            temperature 9 meters from the ground
     * @param srad
     *            amount of solar radiation
     * @param wspd
     *            Wind speed
     */
    private void setMeasurements(Measurement temp, Measurement temp9m, Measurement srad, Measurement wspd)
    {
        measurementMap = new HashMap<String, Measurement>();
        measurementMap.put("tair", temp);
        measurementMap.put("ta9m", temp9m);
        measurementMap.put("srad", srad);
        measurementMap.put("wspd", wspd);
    }

    /**
     * @return the measurementDateTimeUTC
     */
    public GregorianCalendar getMeasurementDateTime()
    {
        return measurementDateTimeUTC;
    }

    /**
     * @return the stationID
     */
    public String getStationID()
    {
        return stationID;
    }

    /**
     * @param key
     *            String of requested measurment type
     * @return the tair
     */
    public Measurement getMeasurement(String key)
    {
        return measurementMap.get(key);
    }

    /**
     * @return the minute measurement was taken
     */
    public int getMinute()
    {
        return measurementDateTimeUTC.get(Calendar.MINUTE);
    }

    /**
     * @return the Month the measurement was taken
     */
    public int getMonth()
    {
        return measurementDateTimeUTC.get(Calendar.MONTH);
    }

    /**
     * @return day measurement was taken
     */
    public int getDay()
    {
        return measurementDateTimeUTC.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @return year measurement was taken
     */
    public int getYear()
    {
        return measurementDateTimeUTC.get(Calendar.YEAR);
    }

}
