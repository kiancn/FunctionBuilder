import kcn.calculation.Calculation;
import kcn.test.CalculationTest;
import kcn.test.FSQRT;
import kcn.test.Test_SQRT;

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

        // Or simply, by supplying a string;
        // Length of hypotenuse: length = ((x^2)+(z^2))root(2)
        Calculation pyt = new Calculation("(n^2)+(n^2)r(2)");
        System.out.println("Pytagoras again from string: input 3,4: " +pyt.calc(3,4));

        /** An test class with examples :) */
        CalculationTest calculationTest = new CalculationTest();
        calculationTest.run_allTests();

    }
    // Read in case of trouble: remember, first number is assumed in the definition of the equation.


}

