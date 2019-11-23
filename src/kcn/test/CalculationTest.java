package kcn.test;

import kcn.calculation.Calculation;

/*This class just mimics what I believe
testing is/can be, but I know almost nothing about that subject */
public class CalculationTest
{

    /* test non-bound, single expression (two input factors) Calculation
     * Im thinking:
     * 5 * 7 = 35
     * 20 + 5 = 25 */

    /* test non-bound, two-expression (three input factors) Calculation
     * Im thinking:
     * (10 / 2) * 4 = 20
     * 25 + 15 - 30 = 10 */


    /* test non-bound, five expression (6 input factors) Calculation
     * Im thinking:
     * 50 * 7 - 40 + 65 - 2 + 21 = 35 */
    public boolean test_6expressions_0_binding()
    {

        System.out.println("\n@@@CalculationTest.test_6expressions_0_binding");
        System.out.println("@@@Testing:" +
                           " 50 * 7 - 40 + 65 - 2 + 21 = 394");
        Calculation test_a = new Calculation.CalcBuilder().
                create().multiply().minus().plus().minus().plus().build();

        double expectedResult = 394;
        double testResult;

        test_a.logToConsole(false);

        testResult = test_a.calc(50, 7, 40, 65, 2, 21);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:        " + testResult);

        return testResult == expectedResult;

    }


    /* radius of circle: ((x-a)^2+(y-b)^2)sqrt*/
    public boolean test_radiusOfCircle(){

        double expectedResult = 5;
        double testResult;

        System.out.println("\nCalculationTest.test_radiusOfCircle");
        System.out.println("@@@ Testing: ((x-a)^2+(y-b)^2)sqrt"+
                           "\t\t((5-2)^2+(6-2)^2)root2");

        Calculation radiusOfCircle = new Calculation.CalcBuilder().create().
                minus().pow().
                expression().plus().
                minus().pow().
                expression().root().
                build();

        testResult = radiusOfCircle.calc(5,2,2,6,2,2,2);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:        " + testResult);

        return testResult == expectedResult;
    }
    /** Tests multiple complex/bound expressions;  */
    public boolean test_radiusOfCircleX2(){

        double expectedResult = 10;
        double testResult;

        System.out.println("\nCalculationTest.test_radiusOfCircleX2");
        System.out.println("@@@ Testing: (((x-a)^2+(y-b)^2)sqrt)*2)"+
                           "\t\t(((5-2)^2+(6-2)^2)root2)*2");

        Calculation radiusOfCircle = new Calculation.CalcBuilder().create().
                minus().pow().
                expression().plus().
                minus().pow().
                expression().root().
                expression().multiply().
                build();

        radiusOfCircle.logToConsole(true);

        testResult = radiusOfCircle.calc(5,2,2,6,2,2,2,2);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:          " + testResult);

        return testResult == expectedResult;
    }
}
