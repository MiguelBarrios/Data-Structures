
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 Project 2 Class that holds the max, min, and average
 *          about all the files provided
 */
public class DaysStatistics extends StatisticsAbstract
{
    /** arraylist of all the files */
    private ArrayList<String> files;
    /** arrayList of all tairMinStats */
    private ArrayList<StatMeasurement> tairMinStats;
    /** arrayList of all tairAvgStats */
    private ArrayList<StatMeasurement> tairAvgStats;
    /** arrayList of all tairMaxStats */
    private ArrayList<StatMeasurement> tairMaxStats;
    /** arrayList of all ta9mMinStats */
    private ArrayList<StatMeasurement> ta9mMinStats;
    /** arrayList of all ta9mAvgStats */
    private ArrayList<StatMeasurement> ta9mAvgStats;
    /** arrayList of all ta9mMaxStats */
    private ArrayList<StatMeasurement> ta9mMaxStats;
    /** arrayList of all sradMinStats */
    private ArrayList<StatMeasurement> sradMinStats;
    /** arrayList of all sradAvgStats */
    private ArrayList<StatMeasurement> sradAvgStats;
    /** arrayList of all sradMaxStats */
    private ArrayList<StatMeasurement> sradMaxStats;
    /** arrayList of all sradTotalStats */
    private ArrayList<StatMeasurement> sradTotalStats;
    /** arrayList of max windSpeed */
    private ArrayList<StatMeasurement> wspdMaxStats;
    /** arrayList of min windSpeeed */
    private ArrayList<StatMeasurement> wspdMinStats;

    /**
     * Constructor for days statistics
     * 
     * @param files
     *            array of timeData files to be analized
     */
    public DaysStatistics(String[] files)
    {

        this.files = new ArrayList<String>(Arrays.asList(files));

        tairMinStats = new ArrayList<StatMeasurement>();
        tairAvgStats = new ArrayList<StatMeasurement>();
        tairMaxStats = new ArrayList<StatMeasurement>();
        ta9mMinStats = new ArrayList<StatMeasurement>();
        ta9mAvgStats = new ArrayList<StatMeasurement>();
        ta9mMaxStats = new ArrayList<StatMeasurement>();
        sradMinStats = new ArrayList<StatMeasurement>();
        sradAvgStats = new ArrayList<StatMeasurement>();
        sradMaxStats = new ArrayList<StatMeasurement>();
        sradTotalStats = new ArrayList<StatMeasurement>();
        wspdMaxStats = new ArrayList<StatMeasurement>();
        wspdMinStats = new ArrayList<StatMeasurement>();
    }

    /**
     * Finds the statistics about the files inputted
     * 
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     * @throws ParseException
     *             if there a problem occurs while parsing the imputed string
     */
    public void findStatistics() throws IOException, WrongCopyrightException, ParseException
    {
        for (String fileName : files)
        {
            MesonetTimeFile mtsFile = new MesonetTimeFile(fileName);
            mtsFile.parseFile();
            ArrayList<TimeData> data = mtsFile.parseFile();
            DayDataStatistics dataStats = new DayDataStatistics(data);

            assignStats(dataStats);
        }
    }

    /**
     * finds the max, min, average and total of all the relevent measurments in each
     * file and stores it in the relevant array
     * 
     * @param dataStats
     *            statistics for a single day
     * @throws ParseException
     *             if there a problem occurs while parsing the imputed string
     */
    private void assignStats(DayDataStatistics dataStats) throws ParseException
    {
        tairMinStats.add(dataStats.getMeasurementMin("tair"));
        tairMaxStats.add(dataStats.getMeasurementMax("tair"));
        tairAvgStats.add(dataStats.getMeasurementAvg("tair"));
        ta9mMinStats.add(dataStats.getMeasurementMin("ta9m"));
        ta9mAvgStats.add(dataStats.getMeasurementAvg("ta9m"));
        ta9mMaxStats.add(dataStats.getMeasurementMax("ta9m"));
        sradMinStats.add(dataStats.getMeasurementMin("srad"));
        sradAvgStats.add(dataStats.getMeasurementAvg("srad"));
        sradMaxStats.add(dataStats.getMeasurementMax("srad"));
        sradTotalStats.add(dataStats.getSolarRadiationTotal());
        wspdMinStats.add(dataStats.getMeasurementMin("wspd"));
        wspdMaxStats.add(dataStats.getMeasurementMax("wspd"));

    }

    /**
     * Method that finds the Minimum measurment
     * 
     * @param inParamId
     *            string of the type of measurment to be retrived
     * @return a statMeasurment of the minimum measurment saved in the arraylist
     * @throws WrongParameterIdException
     *             if invalid parameter is passed
     */
    @Override
    public StatMeasurement getMinimumDay(String inParamId) throws WrongParameterIdException
    {
        ArrayList<ArrayList<StatMeasurement>> list = new ArrayList<ArrayList<StatMeasurement>>();
        list.add(tairMinStats);
        list.add(ta9mMinStats);
        list.add(sradMinStats);
        list.add(wspdMinStats);

        StatMeasurement minMeasurement = new StatMeasurement();
        for (int i = 0; i < list.size(); ++i)
        {
            ArrayList<StatMeasurement> statList = list.get(i);
            // checks each array list to see unitll the one that matches the
            // paramId is found
            if (inParamId.equalsIgnoreCase(statList.get(0).getParamId()))
            {
                minMeasurement = statList.get(0);
                // loopes through correct arraylist
                for (int index = 1; index < statList.size(); ++index)
                {
                    // finds absulute minimum in arraylist
                    if (minMeasurement.value > statList.get(index).value)
                    {
                        minMeasurement = statList.get(index);
                    }
                }
            }
        }
        return minMeasurement;
    }

    /**
     * Method that finds the Minimum measurment
     * 
     * @param inParamId
     *            string of the type of measurment to be retrived
     * @return a statMeasurment of the minimum measurment saved in the arraylist
     * @throws WrongParameterIdException
     *             if invalid parameter is passed
     */
    @Override
    public StatMeasurement getMaximumDay(String inParamId) throws WrongParameterIdException
    {
        ArrayList<ArrayList<StatMeasurement>> list = new ArrayList<ArrayList<StatMeasurement>>();
        list.add(tairMaxStats);
        list.add(ta9mMaxStats);
        list.add(sradMaxStats);
        list.add(wspdMaxStats);

        StatMeasurement maxMeasurement = new StatMeasurement();
        for (int i = 0; i < list.size(); ++i)
        {
            ArrayList<StatMeasurement> statList = list.get(i);
            // checks each array list to see unitll the one that matches the
            // paramId is found
            if (inParamId.equalsIgnoreCase(statList.get(0).getParamId()))
            {
                maxMeasurement = statList.get(0);
                // loopes through correct arraylist
                for (int index = 1; index < statList.size(); ++index)
                {
                    // finds absulute maximum in arraylist
                    if (maxMeasurement.value < statList.get(index).value)
                    {
                        maxMeasurement = statList.get(index);
                    }
                }
            }
        }
        return maxMeasurement;
    }

    /**
     * Combines max and min statistics into a string
     * 
     * @param paramId
     *            String discription of what type of measurment will be retrived
     * @return String of the Maximum and minimum measurment combined
     * @throws WrongParameterIdException
     *             if invalid parameter is passed
     */
    public String combineMinMaxStatistics(String paramId) throws WrongParameterIdException
    {
        StatMeasurement maximumDay = getMaximumDay(paramId);
        StatMeasurement miniumuDay = getMinimumDay(paramId);
        return maximumDay.toString() + "\n" + miniumuDay.toString() + "\n";
    }

    /**
     * @return String of the statMeasurment
     */
    public String toString()
    {
        String expected = "";
        expected = expected.format("%5s %5s  %7s  %3s %15s %7s\n", "ID", "STAT", "VALUE", "STID", "DATE T TIME", "TZ");
        expected += "__________________________________________________\n";
        try
        {
            expected += combineMinMaxStatistics("tair");
            expected += combineMinMaxStatistics("ta9m");
            expected += combineMinMaxStatistics("srad");
        }
        catch (WrongParameterIdException e)
        {
            e.printStackTrace();
        }
        return expected;
    }

}