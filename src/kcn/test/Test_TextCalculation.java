package kcn.test;

import kcn.calculation.Calculation;
import kcn.calculation.TextCalculation;


public class Test_TextCalculation
{
    public static void main(String... args)
    {

        Calculation pytagoras = new Calculation("(n^2)+(n^2)r(2)");
        pytagoras.logToConsole(true);
        System.out.println("Pythagoras on 3 and 4 = " + pytagoras.calc(3, 4));

        double doubleResult = new Calculation("(n*n/3)").calc(3,3);
        System.out.println("\n\t"+doubleResult);

        double sqrt25 = Calculation.equateIt(("(25r2)"));
        System.out.println("\n\t"+sqrt25);


        Calculation distance = new Calculation("(n-n^2)+(n-n^2)+(n-n^2)r(2)");
        System.out.println("\n\t"+distance.calc(0,7,2,2,0,-1));

        Calculation increase = new Calculation("(n/n-1*100)");
        System.out.println(increase);

        System.out.println("\nIncrease from 100 to 150 is a %-increase of: " + increase.calc(150,100));
        System.out.println("Increase from 100 to 200 is a %-increase of: " + increase.calc(200,100));
        System.out.println("Increase from 99 to 150 is a %-increase of: " + increase.calc(150,99));

        TextCalculation calcE = new TextCalculation("(n+n)^(5)");
        System.out.println(calcE);
        System.out.println(calcE.getCalculation().calc(2,5));
    }

}
