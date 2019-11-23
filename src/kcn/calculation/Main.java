package kcn.calculation;

import kcn.test.CalculationTest;

public class Main
{
    /**
     * Works on many types of expressions new. I'll define the limits later -
     * Live with the debug messages, or not. they saved my ass and will be removed
     * Next small goal is proper commenting of all parts of code.
     * Next medium goal is making it possible to define a constant at deklaration time
     * Next BIG goal is a string parser able to interpret standard java math notation (from string) -
     * and create and the calculation object from that.
     */

    public static void main(String[] args)
    {
        Calculation pytagoras = new Calculation.CalcBuilder().create().
                pow(). // ((x^y)+(z^v))w'nd-root
                expression().plus().
                pow().
                expression().root()
                .build();

        System.out.println("sprRoot(5^2 + 4^2) = " + pytagoras.calc(5, 2, 4, 2, 2));
        System.out.println("sprRoot(3^2 + 4^2) = " + pytagoras.calc(3, 2, 4, 2, 2));


        System.out.println("Growth formula: ");
        Calculation growth = new Calculation.CalcBuilder().
                create().
                expression().multiply().
                plus().
                build();

        System.out.println(growth.calc(10, -0.5, 1));

        /** An test class with examples :) */
        CalculationTest testCalculationA = new CalculationTest();



        System.out.println(testCalculationA.test_6expressions_0_binding());
        System.out.println(testCalculationA.test_radiusOfCircle());
        System.out.println(testCalculationA.test_radiusOfCircleX2());
    }
    // Read in case of trouble: remember, first number is assumed in the definition of the equation.


}

