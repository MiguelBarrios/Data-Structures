
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;

/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 Project 2 abstract Class that holds information about
 *          the file like the date, dateFormat and the file itself
 */
public abstract class CsAbstractFile implements TimeComparable
{
    /** The file that is being used */
    protected File file;
    /** format of date */
    protected DateFormat dateFormat;
    /** time format used for strings */
    protected static String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss z";
    /** name of the file */
    protected String fileName;

    /**
     * Constructor that stores the name of the file being used
     * 
     * @param inFileName
     *            name of file
     */
    protected CsAbstractFile(String inFileName)
    {
        this.fileName = inFileName;
        this.file = new File(inFileName);
    }

    /**
     * Checks to see if the file exists
     * 
     * @return return true if the file exist, false otherwise
     */
    public boolean exists()
    {
        return file.exists();
    }

    /**
     * Checks when the file was last modified
     * 
     * @return the date the file was last modified
     */
    public long getDateModified()
    {
        return file.lastModified();
    }

    /**
     * Gets the name of the file
     * 
     * @return name of the file
     */
    @Override
    public String toString()
    {
        return fileName;
    }

    /**
     * Compare's object with a date provided by the user
     * 
     * @param inDateTimeStr
     *            Date that the user wishes to compare against object
     * @return 0 if object is equal to the date provided. -1 if object comes before
     *         date 1 if object comes after date
     * @throws ParseException
     *             throws if there a problem occurs while parsing the imputed string
     */
    public abstract int compareWithTimeString(String inDateTimeStr) throws ParseException;
}
