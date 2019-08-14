/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 Project 2 Wrong parameterID exception
 */
public class WrongParameterIdException extends Exception
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7394973112258653626L;

    /**
     * Exception that is thrown when an invalid peramter id is detected
     */
    public WrongParameterIdException()
    {
        super("Invalid parameterID detected");
    }

    /**
     * Exception that is thrown when an invalid peramter
     * 
     * @param msg
     *            message to be thrown
     */
    public WrongParameterIdException(String msg)
    {
        super(msg + " Invalid parameterID detected");
    }
}
