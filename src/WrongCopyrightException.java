/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 Project 2
 */
public class WrongCopyrightException extends Exception
{
    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -3352808845495117276L;

    /**
     * WrongCopyRight Exception
     */
    public WrongCopyrightException()
    {
        super("Invalid copyright detected");
    }

}
