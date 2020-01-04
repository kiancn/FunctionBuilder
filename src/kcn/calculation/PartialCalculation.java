package kcn.calculation;

/* Purpose of this class is to enable building of calculation object in the abstract
* , and to allow the testing of such calculations without critical exceptions being
* thrown.
* Implementation is not meant for humans, but is to allow manifold testing by
* learning agents; thus, the methods will return ints depending on results of
* tried combinations and, on implmentation, the agent should be able to interpret
* these ints and varying levels of success. */

import java.util.ArrayList;
/* NOT implemented */
public class PartialCalculation
{

    /* partial Strings for combinations */
    private ArrayList<String> calcStrings;

    /** Method adds a string to calcStrings, checking if the string is properly formatted. */
    public int addCalculationPartString(String partialCalculationString){
        return -1; // not implemented
    }

}
