package kcn;

import kcn.calculation.Calculation;

import java.util.Map;

public class RandomCoding
{
    static Calculation salary = new Calculation("(n*12)*(n)");

    public static void main(String[] args)
    {
        double baseIncome = 32000;
        double incomeCap = 1000000;

        double monthlyIncombeIncrease = 5000;

        salary.logToConsole(true);

        for(; ; baseIncome += monthlyIncombeIncrease)
        {
            double reverseTaxRate = approxTaxRate(baseIncome);
            double yearlySalary = salary.calc(baseIncome, reverseTaxRate);

            System.out.println("månedlig indkomst:" + String.format("%10f", baseIncome) +
                               "\t~årlig inkomst " + yearlySalary + "\t ved cirka " + ((reverseTaxRate-1)*-1) +
                               "% trækning" );
            if(yearlySalary >incomeCap)
            {
                break;
            }
        }

    }

    private static double approxTaxRate(double monthlyPayRate)
    {
        double yearlyPay = 12 * monthlyPayRate;

        if(yearlyPay > 600000)
        {
            return 0.48d; // the inverse of the ~paid 52
        }
        return 0.55;
    }

}
