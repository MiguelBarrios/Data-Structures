import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * @author Miguel Barrios Davila
 * @version 27 Martch 2018 Project 2 Class that stores all the information about
 *          the Mesonet file
 */
public class MesonetTimeFile extends CsFile
{
    /** TimeData information for every instance in the file */
    private ArrayList<TimeData> data;
    /** Array of the paramIDs in the file */
    /** Information about the file header */
    private HeaderDateTime headerDateTime;
    /** number of parameters in file */
    private static final int NUMBER_OF_PARAMETERS = 0;
    /** Year file was created */
    private static final int YEAR = 1;
    /** Month file was created */
    private static final int MONTH = 2;
    /** Day file was created */
    private static final int DAY = 3;
    /** Hour file was created */
    private static final int HOUR = 4;
    /** Minute file was created */
    private static final int MINUTE = 5;
    /** Second file was created */
    private static final int SECOND = 6;

    /** String of peramID */
    private static final String TAIR = "TAIR";
    /** String of peramID */
    private static final String TA9M = "TA9M";
    /** String of peramID */
    private static final String SRAD = "SRAD";
    /** String of peramID */
    private static final String TIME = "TIME";
    /** String of peramID */
    private static final String STID = "STID";
    /** String of peramID */
    private static final String WSPD = "WSPD";
    /** array position of tair column in file */
    private int tairPosition = -1;
    /** array position of ta9m in column */
    private int ta9mPosition = -1;
    /** array position of srad column */
    private int sradPosition = -1;
    /** array position of minute column in file */
    private int minutePosition = -1;
    /** array position of stid column in file */
    private int stidPosition = -1;
    /** array position of wspd column in file */
    private int wspdPosition = -1;
    /** Date information about the file */
    private GregorianCalendar dateTime;

    /**
     * @author Miguel Barrios Davila
     * @version 27 Martch 2018 Class that hold file header information
     */
    class HeaderDateTime
    {
        /** Year file was created */
        public int year;
        /** Month file was created */
        public int month;
        /** Day file was created */
        public int day;
        /** Minute file was created */
        public int minute;

        /**
         * Constructor for headerDateTime
         * 
         * @param inYear
         *            year file was made
         * @param inMonth
         *            month file was made
         * @param inDay
         *            day file was made
         * @param inMinute
         *            minute file was made
         */
        HeaderDateTime(int inYear, int inMonth, int inDay, int inMinute)
        {
            year = inYear;
            month = inMonth;
            day = inDay;
            minute = inMinute;
        }
    }

    /**
     * Constructor for time file
     * 
     * @param inFileName
     *            name of the file
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     */
    MesonetTimeFile(String inFileName) throws IOException, WrongCopyrightException
    {
        super(inFileName);
        BufferedReader br = new BufferedReader(new FileReader(inFileName));
        copyrightIsCorrect(br.readLine());
        String header = br.readLine();
        parseDateTimeHeader(header);
        parseParamHeader(br.readLine()); // Parses param header in fie

        br.close();
    }

    /**
     * Parses the into time Data measurments
     * 
     * @return Arraylist of timeData measurments for every instance in the file
     * @throws IOException
     *             if there is a problem with the input or output
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     */
    public ArrayList<TimeData> parseFile() throws IOException, WrongCopyrightException
    {
        data = new ArrayList<TimeData>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        // Discards first three lines that were parsed in the construcotr
        br.readLine();
        br.readLine();
        br.readLine();

        String nextLine = br.readLine();
        // parses every line of the file
        while (nextLine != null)
        {
            parseData(nextLine);
            nextLine = br.readLine();
        }
        br.close();
        return data;
    }

    /**
     * parses line of data and creates measurments
     * 
     * @param line
     *            line of data
     */
    private void parseData(String line)
    {
        if (line != null)
        {
            String[] input = line.trim().split("\\s+");

            TimeData values = new TimeData(input[stidPosition], headerDateTime.year, headerDateTime.month,
                    headerDateTime.day, Integer.parseInt(input[minutePosition]),
                    new Measurement(Double.parseDouble(input[tairPosition])),
                    new Measurement(Double.parseDouble(input[ta9mPosition])),
                    new Measurement(Double.parseDouble(input[sradPosition])),
                    new Measurement(Double.parseDouble(input[wspdPosition])));
            data.add(values);
        }
    }

    /**
     * parese the header of the file
     * 
     * @param inParamStr
     *            String containg what each kind of data each value contains
     */
    private void parseParamHeader(String inParamStr)
    {
        String[] input = inParamStr.trim().split("\\s+");
        ArrayList<String> paramIds = new ArrayList<String>();
        for (String inputData : input)
        {
            paramIds.add(inputData);
        }
        /// verifiy implemetaion of this next part
        this.stidPosition = paramIds.indexOf(STID);
        this.minutePosition = paramIds.indexOf(TIME);
        this.tairPosition = paramIds.indexOf(TAIR);
        this.ta9mPosition = paramIds.indexOf(TA9M);
        this.sradPosition = paramIds.indexOf(SRAD);
        this.wspdPosition = paramIds.indexOf(WSPD);
    }

    /**
     * Checks to see if copyright is correct
     * 
     * @param inCopyrightStr
     *            file copyright info
     * @throws WrongCopyrightException
     *             if invalid copyright is detected
     */
    private void copyrightIsCorrect(String inCopyrightStr) throws WrongCopyrightException
    {
        String[] input = inCopyrightStr.trim().split("\\s+");
        int copyRight = Integer.parseInt(input[NUMBER_OF_PARAMETERS]);
        if (copyRight != 101)
        {
            throw new WrongCopyrightException();
        }
    }

    /**
     * Parses the date and time in the header and stores the info
     * 
     * @param inHeader
     *            date information about the file
     */
    void parseDateTimeHeader(String inHeader)
    {
        String[] input = inHeader.trim().split("\\s+");
        int year = Integer.valueOf(input[YEAR]);
        int month = Integer.valueOf(input[MONTH]);
        int day = Integer.valueOf(input[DAY]);
        int hour = Integer.valueOf(input[HOUR]);
        int minute = Integer.valueOf(input[MINUTE]);
        int second = Integer.valueOf(input[SECOND]);

        this.dateTime = new GregorianCalendar(year, month, day, hour, minute, second);
        this.headerDateTime = new HeaderDateTime(year, month, day, minute);
    }

    /**
     * Gets the date of the file
     * 
     * @return start time of the file provided
     */
    String getStarDateTimeStringFromFile()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);

        String dateString = dateFormat.format(dateTime.getTime());
        return dateString; // sample of output 2018-02-02T00:00:00 CST
    }

    /**
     * Gets the current date
     * 
     * @return date and time of the file
     */
    String getDateTimeString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        GregorianCalendar currentDay = new GregorianCalendar();
        String dateString = dateFormat.format(currentDay.getTime());
        return dateString;
    }

}
