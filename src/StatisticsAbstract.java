/**
 * @author Miguel Barrios Davila
 * @version 26 March 2018 Project 2 Abstract class
 */
public abstract class StatisticsAbstract
{
    /**
     * Abstract method for getting the mimimum day
     * 
     * @param inParamId
     *            The type of measurement that is to be looked for
     * @return StatMeasurment for the minimumDay following the inParamID
     * @throws WrongParameterIdException
     *             if invalid parameter is passed
     */
    public abstract StatMeasurement getMinimumDay(String inParamId) throws WrongParameterIdException;

    /**
     * Abstract method for getting the Maximum day
     * 
     * @param inParamId
     *            The type of measurement that is to be looked for
     * @return StatMeasurment for the minimumDay following the inParamID
     * @throws WrongParameterIdException
     *             if invalid parameter is passed
     */
    public abstract StatMeasurement getMaximumDay(String inParamId) throws WrongParameterIdException;

}
