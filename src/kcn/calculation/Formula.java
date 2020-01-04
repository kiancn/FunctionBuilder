package kcn.calculation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>Instances of Formula class is able to do  between 0 and n series calculations on input factors
 * supplied as Double arrays.</p>
 *
 * <p>A description should be made, but I can't be bothered right now:</p>
 * <p>Ex.</p>
 * <p>Formula formulaEx = new Formula("(n*n)^(n+1)");</p>
 * <p>double[] results = formulaEx.computeSeries(</p>
 * <p>                        new Double[]{2d,3d,4d}, // factor n (1st n)</p>
 * <p>                        new Double[]{2d,3d,4d}, // factor n (2nd n)</p>
 * <p>                        new Double[]{2d,3d,4d}); // factor n (3rd n);</p>
 * <p></p>
 * <p>If you need to do a single calculation at a time, use a Calculation class object instead.</p>
 *
 * <b>There are certain special rules on how calculations can be put together: see Calculation class doc.
 * For rules on how to form a calculation properly, see TextCalculation class doc
 *  (aka the top of those classes).</b>
 */
public class Formula
{
    Calculation calculation;            /* Calculation object to perform calculations.. */
    TextCalculation textCalculation;    /* Text-calculation object brought along to allow massive mutability */

    boolean logToConsole; /* unimplemented */
    /**
     * Initializes a formula object through interpreting supplied string as a math formula.
     */
    public Formula(String calculationString)
    {
        setCalculation(calculationString);
    }

    public void setCalculation(String calculationString)
    {
        textCalculation = new TextCalculation(calculationString);
        calculation = textCalculation.getCalculation();
    }

    public double[] computeSeries(Double[]... factorArrays)
    {
        /* Check for bad input */
        /* 1. check that number of arrays supplied matches number of input factors expected */
        if(calculation.getNumberOfInputFactorsExpected() != factorArrays.length)
        {
            System.out.println("Wrong number of series (arrays) supplied, " +
                               calculation.getNumberOfInputFactorsExpected() + " expected.");
            return new double[]{-1};
        }

        /* 2. Compare lengths of supplied array; they must be equal */
        boolean equalNumbersDetected = true;

        int factorCount = factorArrays[0].length;

        for(int i = 0; i < factorArrays.length; i++)
        {
            /* if there is just one factor, this is compared to itself, hardly any harm is done, and it
            works to detect badly formed factorArrays in general. */
            if(factorArrays[i].length != factorCount){equalNumbersDetected = false;}
        }
        /* Refuse to work if bad input is given. */
        if(!equalNumbersDetected)
        {
            System.out.println("Debug: Bad length of supplied factor arrays.\n" +
                               "Supplied arrays were not equally long.");
            return new double[]{-1};
        }

        /* Actual rounds of calculations */
        /* transferring the double[]s to an easier to-work-with environment */
        ArrayList<Double[]> factorAAList = new ArrayList<>();
        factorAAList.addAll(Arrays.asList(factorArrays));

        /* one result for length of supplied series */
        double[] results = new double[factorArrays[0].length];

        /*go through length of first series */
        for(int i = 0, j = 0; i < factorAAList.get(0).length; i++, j = 0)
        {
            double[] singleCalcFactors = new double[factorAAList.size()];
            /* for each iteration get number in parallel series */
            for(; j < factorAAList.size(); j++)
            {
                /* get factors specific to round i (j's are the specifics)*/
                singleCalcFactors[j] = factorAAList.get(j)[i];
            }
            /* do calc with parallel numbers (a set of factors), saving each result in a spot in results[] */
            results[i] = calculation.calc(singleCalcFactors);
        }
        return results;
    }

    @Override
    public String toString()
    {
        return textCalculation.toString();
    }
}
