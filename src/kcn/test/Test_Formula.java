package kcn.test;

import kcn.calculation.Formula;

import java.util.ArrayList;

/* Code must speak for itself, because there is no introduction yet.
 * Test of Formula class */
public class Test_Formula
{
    public static void main(String[] args)
    {


        Formula growthF = new Formula("(n^2)+(n^2)r(2)");

        System.out.println(growthF);

        growthF.getCalculation().logToConsole(true);

        double[] grownNumbers = growthF.computeSeries(new Double[]{4d, 50d, 9d},
                                                      new Double[]{5d, 150d, 14d});

        for(double result : grownNumbers) { System.out.println(result); }


        System.out.println();
        growthF = new Formula("(n/n*100)");
        System.out.println();
        grownNumbers = growthF.computeSeries(new Double[]{100d, 150d, 200d}, new Double[]{50d, 50d, 50d});
        for(double result : grownNumbers) { System.out.println(result); }


        System.out.println();
        growthF = new Formula("(n/n*100)");
        System.out.println(growthF);
        grownNumbers = growthF.computeSeries(new Double[]{100d, 150d, 200d});
        for(double result : grownNumbers) { System.out.println(result); }


        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(2.3d);
        doubles.add(3.3d);
        doubles.add(25.3d);
        doubles.add(221.3d);
        Double[] doublesArray = doubles.toArray(new Double[4]);

        Formula _1stdegreeFormula = new Formula("(n*2-5)");

        double[] results1 = _1stdegreeFormula.computeSeries(doublesArray);

        int c = 0;
        for(double n : results1)
        {
            System.out.println("\t" + doubles.get(c++) + "*2-5) --->\t" + n);
//            System.out.println("\t" + doubles.get(c++) + "\t--> (n*2-5) --->\t" + n);
        }
    }
}
