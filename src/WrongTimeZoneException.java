/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 WrongTimeZoneEXception
 */
public class WrongTimeZoneException extends Exception
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Excpetion that is thown when an invalid time zone is detected
     */
    public WrongTimeZoneException()
    {
        super("Invalid time zone detected, should be UTC");
        // default implementation ignored
    }
}
