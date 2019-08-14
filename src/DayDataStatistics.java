
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 Project 2 Class that stores the max, min, average, of
 *          solar radiation and temperature at both 0 and 9 feet from the
 *          ground. ass well as total amount of solar radiation recieved
 */
public class DayDataStatistics
{
    /** Hash map that contaings Max, min, and average of the file measurements **/
    private HashMap<String, EnumMap<StatType, StatMeasurement>> paramStats;

    /** The set of data. */
    private ArrayList<TimeData> data;

    /** Total solarRadiation */
    private StatMeasurement solarRadiationTotal;
    /** Station id measurments were taken **/
    private String stationId = "nada";

    /**
     * constructor for day data statistics that storas all the information about the
     * measurement
     * 
     * @param inData
     *            arraylist of timedata to be analized
     */
    public DayDataStatistics(ArrayList<TimeData> inData)
    {
        paramStats = new HashMap<String, EnumMap<StatType, StatMeasurement>>();
        this.data = inData;
        stationId = inData.get(0).getStationID();

        calculateAirTemperatureStatistics("tair");
        calculateAirTemperatureStatistics("ta9m");
        calculateWindSpeedStatistics();
        calculateSolarRadiationStatistics();
    }

    /**
     * Calculate tair or ta9m dertermined by the perameter passed
     * 
     * @param tairName
     *            string of perameter that determins witch measurement will be
     *            calculated
     */
    private void calculateAirTemperatureStatistics(String tairName)
    {
        // These variables represent the "best so far" for min and max.
        // By setting these these to the largest and smallest possible
        // values, we ensure that the first time a valid Measurement is
        // found, it will replace these values

        // Accumulator and counter for computing average
        double sum = 0;
        int numberOfValidObservations = 0;

        // Set arbitrarily high and low:
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        // will loop threw all instances and find the max min and average of the
        // specified temp
        TimeData maxTemp = null;
        TimeData minTemp = null;
        for (int i = 0; i < this.data.size(); ++i)
        {
            Measurement instance;
            // Will find determin if the max min and average of
            // 1,5 or 9m temperature will calculated by the argument provided
            if (tairName.equalsIgnoreCase("tair"))
            {
                instance = this.data.get(i).getMeasurement("tair");
            }
            else
            {
                instance = this.data.get(i).getMeasurement("ta9m");

            }
            // if temperature is valid it will be considered for the statistics
            if (instance.isValid())
            {
                if (min > instance.getValue())
                {
                    min = instance.getValue();
                    minTemp = data.get(i);
                }
                if (max < instance.getValue())
                {
                    max = instance.getValue();
                    maxTemp = data.get(i);
                }
                ++numberOfValidObservations;
                sum += instance.getValue();
            }
        }
        // if the string argument provided was Tair the it will store the max min
        // and average calculated about in temperature variables
        if (tairName.equalsIgnoreCase("tair"))
        {
            EnumMap<StatType, StatMeasurement> measurementExtremes = new EnumMap<StatType, StatMeasurement>(
                    StatType.class);
            // creates max, min, and average stat measurments
            measurementExtremes.put(StatType.MIN, new StatMeasurement(min, minTemp.getMeasurementDateTime(),
                    minTemp.getStationID(), "TAIR", StatType.MIN));
            measurementExtremes.put(StatType.MAX, new StatMeasurement(max, maxTemp.getMeasurementDateTime(),
                    maxTemp.getStationID(), "TAIR", StatType.MAX));
            measurementExtremes.put(StatType.AVG, new StatMeasurement(sum / numberOfValidObservations, null,
                    minTemp.getStationID(), "TAIR", StatType.AVG));

            paramStats.put("tair", measurementExtremes);
        }
        // if the string argument provided was Ta9m the it will store the max min
        // and average calculated about in temperature variables
        else if (tairName.equalsIgnoreCase("Ta9m"))
        {
            EnumMap<StatType, StatMeasurement> measurementExtremes = new EnumMap<StatType, StatMeasurement>(
                    StatType.class);
            // creates max, min, and average stat measurments
            measurementExtremes.put(StatType.MIN, new StatMeasurement(min, minTemp.getMeasurementDateTime(),
                    minTemp.getStationID(), "TA9M", StatType.MIN));
            measurementExtremes.put(StatType.MAX, new StatMeasurement(max, maxTemp.getMeasurementDateTime(),
                    maxTemp.getStationID(), "TA9M", StatType.MAX));
            measurementExtremes.put(StatType.AVG, new StatMeasurement(sum / numberOfValidObservations, null,
                    minTemp.getStationID(), "TA9M", StatType.AVG));

            paramStats.put("ta9m", measurementExtremes);
        }

    }

    /**
     * Compute and fill in the solar radiation-related statistics
     * (solarRadiationMin, solarRadiationMax, solarRadiationAverage, and
     * solarRadiationTotal).
     * <P>
     * Notes:
     * <UL>
     * <LI>Only valid Measurements can be used in these computations
     * <LI>You may assume that every month has at least one valid Measurement
     * </UL>
     */
    private void calculateSolarRadiationStatistics()
    {
        EnumMap<StatType, StatMeasurement> measurementStats = new EnumMap<StatType, StatMeasurement>(StatType.class);

        // Set arbitrarily high and low:
        double minSolar = Double.POSITIVE_INFINITY;
        double maxSolar = Double.NEGATIVE_INFINITY;
        double totalSolar = 0.0;
        int numberOfValidObservations = 0;

        // will loop threw all time instances and find max and min average and total
        // solar radiation
        for (int i = 0; i < this.data.size(); ++i)
        {
            TimeData instance = this.data.get(i);
            // makes sure only valid infomation is considered
            if (instance.getMeasurement("srad").isValid())
            {
                double value = instance.getMeasurement("srad").getValue();
                // determins value could be the new minimum
                if (minSolar > value)
                {
                    minSolar = value;
                    measurementStats.put(StatType.MIN, new StatMeasurement(value, instance.getMeasurementDateTime(),
                            instance.getStationID(), "SRAD", StatType.MIN));
                }
                // determins if the value could be the new maximum
                if (maxSolar < value)
                {
                    maxSolar = value;
                    measurementStats.put(StatType.MAX, new StatMeasurement(value, instance.getMeasurementDateTime(),
                            instance.getStationID(), "SRAD", StatType.MAX));
                }
                ++numberOfValidObservations;
                totalSolar += value;
            }
        }

        Double averageSolar = totalSolar / numberOfValidObservations;
        measurementStats.put(StatType.AVG,
                new StatMeasurement(averageSolar, null, this.data.get(0).getStationID(), "SRAD", StatType.AVG));

        this.solarRadiationTotal = new StatMeasurement(totalSolar, null, this.data.get(0).getStationID(), "SRAD",
                StatType.TOT);

        paramStats.put("srad", measurementStats);
    }

    /**
     * Compute and fill in the solar radiation-related statistics
     * (solarRadiationMin, solarRadiationMax, solarRadiationAverage, and
     * solarRadiationTotal).
     * <P>
     * Notes:
     * <UL>
     * <LI>Only valid Measurements can be used in these computations
     * <LI>You may assume that every month has at least one valid Measurement
     * </UL>
     */
    private void calculateWindSpeedStatistics()
    {
        EnumMap<StatType, StatMeasurement> measurementStats = new EnumMap<StatType, StatMeasurement>(StatType.class);

        // Set arbitrarily high and low:
        double minSolar = Double.POSITIVE_INFINITY;
        double maxSolar = Double.NEGATIVE_INFINITY;

        // will loop threw all time instances and find max and min average and total
        // solar radiation
        for (int i = 0; i < this.data.size(); ++i)
        {
            TimeData instance = this.data.get(i);
            // makes sure only valid infomation is considered
            if (instance.getMeasurement("wspd").isValid())
            {
                double value = instance.getMeasurement("wspd").getValue();
                // determins value could be the new minimum
                if (minSolar > value)
                {
                    minSolar = value;
                    measurementStats.put(StatType.MIN, new StatMeasurement(value, instance.getMeasurementDateTime(),
                            instance.getStationID(), "WSPD", StatType.MIN));
                }
                // determins if the value could be the new maximum
                if (maxSolar < value)
                {
                    maxSolar = value;
                    measurementStats.put(StatType.MAX, new StatMeasurement(value, instance.getMeasurementDateTime(),
                            instance.getStationID(), "WSPD", StatType.MAX));
                }
            }
        }

        paramStats.put("wspd", measurementStats);
    }

    /**
     * Gets the total amount of solar radiation for that day
     * 
     * @return StatMeasurement of total solar
     */
    public StatMeasurement getSolarRadiationTotal()
    {
        return solarRadiationTotal;
    }

    /**
     * The station where measurment was recorded
     * 
     * @return station ID
     */
    public String getStationID()
    {
        return stationId;
    }

    /**
     * Describe DayStatistics
     * 
     * @return A string describing the statistics for the day
     */
    public String toString()
    {
        String expected = "";
        expected = expected.format("%5s %5s  %7s  %3s %15s %7s\n", "ID", "STAT", "VALUE", "STID", "DATE T TIME", "TZ");

        expected += paramStats.get("tair").get(StatType.MAX).toString()
                + paramStats.get("tair").get(StatType.MIN).toString()
                + paramStats.get("ta9m").get(StatType.MAX).toString()
                + paramStats.get("ta9m").get(StatType.MIN).toString()
                + paramStats.get("srad").get(StatType.MAX).toString()
                + paramStats.get("srad").get(StatType.MIN).toString();

        return expected;
    }

    /**
     * Gets either the tair, ta9m, or solar min that is determined by provided
     * string
     * 
     * @param name
     *            string of what measurment is to be retrived
     * @return maximum stat Measurment provied from string
     */
    public StatMeasurement getMeasurementMax(String name)
    {
        return paramStats.get(name).get(StatType.MAX);
    }

    /**
     * Gets either the tair, ta9m, or solar min that is determined by provided
     * string
     * 
     * @param name
     *            string of what measurment is to be retrived
     * @return minimum stat Measurment provied from string
     */
    public StatMeasurement getMeasurementMin(String name)
    {
        return paramStats.get(name).get(StatType.MIN);
    }

    /**
     * Gets either the tair, ta9m, or solar min that is determined by provided
     * string
     * 
     * @param name
     *            string of what measurment is to be retrived
     * @return average stat Measurment provied from string
     */
    public StatMeasurement getMeasurementAvg(String name)
    {
        return paramStats.get(name).get(StatType.AVG);
    }
}
