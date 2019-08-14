import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Driver
{

    public static void main(String[] args)
            throws IOException, WrongCopyrightException, ParseException, WrongParameterIdException
    {

        String[] files = { "data/mesonet/20180102stil.mts", "data/mesonet/20180102okcn.mts",
                "data/mesonet/20180102okce.mts" };

        try
        {
            DaysStatistics stats = new DaysStatistics(files);
            stats.findStatistics();
            System.out.println(stats.toString());

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        MesonetFrame frame = new MesonetFrame();

    }

}
