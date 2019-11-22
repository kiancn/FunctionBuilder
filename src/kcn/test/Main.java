package kcn.test;

public class Main
{
    /**
     * Works on many types of expressions new. I'll define the limits later -
     * Live with the debug messages, or not. they saved my ass and will be removed
     * Next small goal is proper commenting of all parts of code.
     * Next BIG goal is a string parser able to interpret standard java math notation (from string) -
     * and create and the calculation object from that.
     * create
     * the
     */

    public static void main(String[] args)
    {

        Calculation someCalculation = new Calculation.CalcBuilder()
                .create()
                .plus()
                .raisedBy()
                .minus().
                        build(); //((x + y)^z)-w
        //((x + y)^z)-w
        System.out.println(someCalculation.calc(2, 3, 2, 5)); // ((2 + 3)^2)-5


        System.out.println(" ((13 + 34) ^ 6) - 21 =" + Calculation.equateIt(someCalculation, 13, 34, 6, 21));

        // Endnu vildere:

        Calculation pytagoras = new Calculation.CalcBuilder().create().
                pow(). // ((x^y)+(z^v))w'nd-root
                expression().plus().
                pow().
                expression().root().expression()
                .build();

        System.out.println("sprRoot(5^2 + 4^2) = " + pytagoras.calc(5, 2, 4, 2, 2));
        System.out.println("sprRoot(3^2 + 4^2) = " + pytagoras.calc(3, 2, 4, 2, 2));

        Calculation randCalc =
                new Calculation.CalcBuilder().create()
                        .plus() // ((x+y)+z)+((v+w)*u)
                        .plus()
                        .expression().plus().
                        plus().
                        multiply()
                        .build();
        System.out.println(randCalc.calc(1, 2, 3, 4, 5, 6));

        System.out.println("Growth formula");
        Calculation growth = new Calculation.CalcBuilder().
                create().
                expression().multiply().
                plus().
                build();

        System.out.println(growth.calc(10, -0.5, 1));

    }



    // Read in case of trouble: remember, first number is assumed in the definition of the equation.


}

