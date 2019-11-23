package kcn.calculation;

import kcn.test.CalculationTest;

public class Main
{
    /**
     * Works on many types of expressions now. I'll define the limits later -
     * Live with the debug messages, or not. they saved my ass and will be removed
     * Next small goal is proper commenting of all parts of code.
     * Next medium goal is making it possible to define a constant at declaration time.
     * Next BIG goal is a string parser able to interpret standard java math notation (from string) -
     * and create the calculation object from that.
     */

    public static void main(String[] args)
    {
        // A few examples:

        /* This is how you might express the most famous theorem of Pythagoras (without constants :( ))*/
        // Length of hypotenuse: length = ((x^y)+(z^v))root(w)
        // (there is no square root, but the general rootfunction:
        // so square root of x, is pseudo-written like ' x root 2 ')
        // and yes, w is the constant 2. constants are on my list of upcoming improvements.......

        Calculation pytagoras = new Calculation.CalcBuilder().
                create(). // create() performs baseline initialization on builder-object

                pow(). // analogous to:                 ((x^y
                expression().plus(). // analogous to:   )+(
                pow(). // analogous to:                 z^v
                expression().root() // analogous to:    ))root(w)

                .build(); // returns the finished Calculation object


        System.out.println("sprRoot(5^2 + 4^2) = " + pytagoras.calc(5, 2, 4, 2, 2));
        System.out.println("sprRoot(3^2 + 4^2) = " + pytagoras.calc(3, 2, 4, 2, 2));



        System.out.println("\nGrowth formula: ");
        System.out.println("(Starting value * (" + // analogous to ' expression().multiply(). '
                           "1 + rate of change))" + // analogous to ' plus().
                           " = end value "); // no analogy in declaration
        Calculation growth = new Calculation.CalcBuilder().
                create().
                expression().multiply().
                plus().
                build();

        System.out.println("(42*(1+(-0.5))) = " + growth.calc(42, 1, -0.5));



        /** An test class with examples :) */
        CalculationTest testCalculationA = new CalculationTest();

        System.out.println(testCalculationA.test_6expressions_0_binding());
        System.out.println(testCalculationA.test_radiusOfCircle());
        System.out.println(testCalculationA.test_radiusOfCircleX2());
    }
    // Read in case of trouble: remember, first number is assumed in the definition of the equation.


}

