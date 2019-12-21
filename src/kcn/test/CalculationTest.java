package kcn.test;

import kcn.calculation.Calculation;

/*This class just mimics what I believe
testing is/can be, but I know almost nothing about that subject */
public class CalculationTest
{

    public void run_allTests()
    {
        System.out.println(test_6expressions_0_binding());
        System.out.println(test_radiusOfCircle());
        System.out.println(test_diameterOfCircle());
        System.out.println(test_PythagorasWithConstants());
        System.out.println(test_Growth());
        System.out.println(test_GrowthWithConstant());
        System.out.println(test_ModulusWithConstant());

        System.out.println(test_radiusOfCircleWithConstants());
    }


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
    public boolean test_radiusOfCircle()
    {

        double expectedResult = 5;
        double testResult;

        System.out.println("\nCalculationTest.test_radiusOfCircle");
        System.out.println("@@@ Testing: ((x-a)^k+(y-b)^k)sqrt" +
                           "\t\t((5-2)^2+(6-2)^2)root2");

        Calculation radiusOfCircle = new Calculation.CalcBuilder().create().
                minus().pow().
                expression().plus().
                minus().pow().
                expression().root().
                build();

        testResult = radiusOfCircle.calc(5, 2, 2, 6, 2, 2, 2);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:        " + testResult);

        return testResult == expectedResult;
    }

    /* radius of circle: ((x-a)^2+(y-b)^2)sqrt*/
    public boolean test_radiusOfCircleWithConstants()
    {

        double expectedResult = 5;
        double testResult;

        System.out.println("\nCalculationTest.test_radiusOfCircle");
        System.out.println("@@@ Testing: ((x-a)^2+(y-b)^2)sqrt" +
                           "\n\t\t((5-2)^2+(6-2)^2)root2");

        Calculation radiusOfCircle = new Calculation.CalcBuilder().create().
                minus().pow().constant(2).
                expression().plus().
                minus().pow().constant(2).
                expression().root().constant(2).
                build();


//        Calculation radiusOfCircle = new Calculation.CalcBuilder().create().
//                minus().pow().constant(2).
//                expression().plus().
//                minus().pow().constant(2).
//                expression().root().constant(2).
//                build();

        radiusOfCircle.logToConsole(true);

        testResult = radiusOfCircle.calc(5, 2, 6, 2);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:        " + testResult);

        return testResult == expectedResult;
    }

    /**
     * Tests multiple complex/bound expressions;
     */
    public boolean test_diameterOfCircle()
    {

        double expectedResult = 10;
        double testResult;
        System.out.println("\nCalculationTest.test_diameterOfCircle");
        System.out.println("@@@ Testing: (((x-a)^2+(y-b)^2)sqrt)*2)" +
                           "\n\t\t(((5-2)^2+(6-2)^2)root2)*2");

        Calculation radiusOfCircle = new Calculation.CalcBuilder().create().
                minus().pow().
                expression().plus().
                minus().pow().
                expression().root().
                expression().multiply().
                build();

        radiusOfCircle.logToConsole(true);

        testResult = radiusOfCircle.calc(5, 2, 2, 6, 2, 2, 2, 2);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:          " + testResult);

        return testResult == expectedResult;
    }

    /**
     * Testing if the pythagorean theorem about the long side in a right angled triangle serves
     * as a test to see it the insertion of constants work
     **/
    public boolean test_PythagorasWithConstants()
    {

        double expectedResult = 5;
        double testResult;

        Calculation pytTheorem = new Calculation.CalcBuilder().create().
                pow().constant(2).
                expression().plus().
                pow().constant(2).
                expression().root().constant(2).
                build();

        pytTheorem.logToConsole(true);

        testResult = pytTheorem.calc(3, 4);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:          " + testResult);
        return (testResult == expectedResult);
    }

    public boolean test_Growth()
    {
        System.out.println("CalculationTest.test_Growth");
        double expectedResult = 5;
        double testResult;

        Calculation growth = new Calculation.CalcBuilder().
                create().
                expression().multiply().
                plus().
                build();

        growth.logToConsole(true);

        testResult = growth.calc(10, 1, -0.5D);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:          " + testResult);
        return (testResult == expectedResult);
    }

    public boolean test_GrowthWithConstant()
    {
        System.out.println("CalculationTest.test_GrowthWithConstant");
        double expectedResult = 5;
        double testResult;

        Calculation growth = new Calculation.CalcBuilder().
                create().
                expression().multiply().
                constant(1).plus().
                build();

        growth.logToConsole(true);

        testResult = growth.calc(10, -0.5D);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:          " + testResult);
        return (testResult == expectedResult);
    }

    public boolean test_ModulusWithConstant()
    {
        System.out.println("CalculationTest.test_ModulusWithConstant");

        double expectedResult = 1;
        double testResult;

        Calculation isNumberEqualCalculation = new Calculation.CalcBuilder().create().
                modulus().constant(2).
                build();

        isNumberEqualCalculation.logToConsole(true);

        testResult = isNumberEqualCalculation.calc(7);

        System.out.println("@@@ Expected answer was : " + expectedResult
                           + "\n@@@ Test result:          " + testResult);
        return (testResult == expectedResult);

    }
}
