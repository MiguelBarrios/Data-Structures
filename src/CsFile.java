
import java.text.ParseException;

/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 Project 2 Class that holds information about the file
 *          as well as methods to comare file against one and other.
 */
public class CsFile extends CsAbstractFile
{
    /**
     * Constructor that calls the base constructor
     * 
     * @param inFileName
     *            name of file
     */
    public CsFile(String inFileName)
    {
        super(inFileName);
    }

    /**
     * Gets the name of the file
     * 
     * @return the name of file
     */
    public String getFileName()
    {
        return this.fileName;
    }

    /**
     * Determins if file is newer than the provided string
     * 
     * @param inDateTime
     *            Date to campare agains file
     * @return true if file objects is newer than date, false otherwise     
     *         * @throws ParseException throws if there a problem occurs while
     *         parsing the imputed string
     */
    public boolean newerThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) == 1 ? true : false;
    }

    /**
     * Determins if file is older than the provided string
     * 
     * @param inDateTime
     *            Date to campare agains file
     * @return true if file objects is older than date, false otherwise     
     *         * @throws ParseException throws if there a problem occurs while
     *         parsing the imputed string
     */
    public boolean olderThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) == -1 ? true : false;
    }

    /**
     * Compare Mesonent Time Files
     * 
     * @param inDateTime
     *            Date to campare agains file yyyymmdd format
     * @return 1 if file object is newer than date, -1 if file object is older than
     *         date, and 0 if file date is the same as the date provided     
     *         * @throws ParseException throws if there a problem occurs while
     *         parsing the imputed string
     */
    public int compareWithTimeString(String inDateTime) throws ParseException
    {
        String date = this.getFileName().substring(13, 21);
        int thisDateValue = Integer.parseInt(date);

        int compareDateValue = Integer.parseInt(inDateTime);
        // if dates are equal
        if (thisDateValue == compareDateValue)
        {
            return 0;
        }
        // if object is newer than date
        else if (thisDateValue > compareDateValue)
        {
            return 1;
        }
        // if object is olderthan
        else
        {
            return -1;
        }
    }
}