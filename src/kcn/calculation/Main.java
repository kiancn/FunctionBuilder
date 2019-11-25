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
        // Length of hypotenuse: length = ((x^2)+(z^2))root(2)

        Calculation pytagoras = new Calculation.CalcBuilder().
                create(). // create() performs baseline initialization on builder-object

                pow().constant(2). // analogous to:                 ((x^2
                expression().plus(). // analogous to:               )+(
                pow().constant(2). // analogous to:                 z^2
                expression().root().constant(2) // analogous to:    ))root(2)

                .build(); // returns the finished Calculation object

        System.out.println("sprRoot(4^2 + 3^2) = " + pytagoras.calc(4, 3));

        /** An test class with examples :) */
        CalculationTest testCalculationA = new CalculationTest();

        System.out.println(testCalculationA.test_6expressions_0_binding());
        System.out.println(testCalculationA.test_radiusOfCircle());
        System.out.println(testCalculationA.test_diameterOfCircle());
        System.out.println(testCalculationA.test_PythagorasWithConstants());
        System.out.println(testCalculationA.test_Growth());
        System.out.println(testCalculationA.test_GrowthWithConstant());
        System.out.println(testCalculationA.test_ModulusWithConstant());

        System.out.println(testCalculationA.test_radiusOfCircleWithConstants());
    }
    // Read in case of trouble: remember, first number is assumed in the definition of the equation.


}

