package kcn.test;

public class Test_SQRT
{
    private final int[] numbers = new int[]{6018, 482, 6232, 662, 8986, 7699, 5248, 2347, 572, 2531, 1448, 7229,
            264, 2353, 8974, 9537, 1926, 7680, 2178, 8967, 7857, 4152, 2887, 3179, 2446, 5377, 7729, 772,
            7558, 9539, 8876, 7812, 2444, 4323, 9875, 6536, 9417, 6689, 9316, 5722, 2770, 6266, 9030, 8400,
            2515, 3779, 199, 4076, 6389, 8757, 353, 9435, 3268, 4052, 6537, 3626, 8656, 8157, 5115, 1653, 8227,
            211, 6665, 2138, 8519, 6680, 5197, 3782, 4868, 3190, 4526, 8227, 5376, 9769, 1070, 5692, 6328, 2823,
            1546, 3745, 8818, 2582, 6095, 8913, 8576, 475, 2208, 5395, 972, 6890, 2510, 5140, 997, 6153, 9910,
            8734, 3688, 2166, 6944, 7147};

    public void startLazyTests(){



        System.out.println(FSQRT.fastSquareRoot1(25));
        System.out.println(FSQRT.fastSquareRoot2(25));
        System.out.println(FSQRT.fastSquareRoot1(100));
        System.out.println(FSQRT.fastSquareRoot2(100));

        long doubleToLong25 = Double.doubleToLongBits(25);
        System.out.println(doubleToLong25);
        long bsMagic52 = 1L << 52;
        long bsMagic61 = 1L << 61;
        System.out.println(" 1L << 52 = " + bsMagic52);
        System.out.println(" 1L << 61 = " + bsMagic61);

//        System.out.print("[");
//        for(int i = 0; i < 100; i++)
//        {
//            System.out.print((int)(Math.random() * 10000) + ",");
//        }
//        System.out.print("]");


        Test_SQRT test_sqrt = new Test_SQRT();


        for(int i = 0; i < 10; i++)
        {

            double sqrt = test_sqrt.testMathSqrtMethod_Speedtest(1000000);
            double fsqrt1 = test_sqrt.testFastSqrtMethod1_Speedtest(1000000);
            double fsqrt2 = test_sqrt.testFastSqrt2Method_Speedtest(1000000);


            System.out.println("Test of stnrd sqrt-method: " + sqrt);
            System.out.println("Test of fresh sqrt-method 1: " + fsqrt1);
            System.out.println("Test of fresh sqrt-method 2: " + fsqrt2);
        }


        System.out.println();
    }

    private double testReport(int numberOfTests, long startTime, double accum)
    {
        long endTime = System.nanoTime();

        long testDuration = endTime - startTime;

        System.out.println(String.format("Accumulated total:                 %8.2f", accum));
        System.out.println(String.format("Blind average based on %8d tests is:%8.2f", numberOfTests,
                                         accum / numberOfTests));

        return (double)testDuration/10000000;
    }

    public double testFastSqrt2Method_Speedtest(int numberOfTests)
    {
        FSQRT fsqrt = new FSQRT();

        long startTime = System.nanoTime();

        double accum = 0D;

        for(int i = numberOfTests; i > 0; i--)
        {

            for(int n : numbers)
            {
                accum += FSQRT.fastSquareRoot2((n));
            }
        }

        System.out.println("Test_SQRT.testFastSqrt2Method_Speedtest");
        return testReport(numberOfTests, startTime, accum);
    }



    public double testFastSqrtMethod1_Speedtest(int numberOfTests)
    {

        long startTime = System.nanoTime();

        double accum = 0D;

        for(int i = numberOfTests; i > 0; i--)
        {

            for(int n : numbers)
            {
                accum += FSQRT.fastSquareRoot1(n);
//                accum += fsqrt.fastSquareRoot1((n));
            }
        }

        System.out.println("Test_SQRT.testFastSqrtMethod1_Speedtest");
        return testReport(numberOfTests, startTime, accum);
    }

    public double testMathSqrtMethod_Speedtest(int numberOfTests)
    {

        double accum = 0D;

        long startTime = System.nanoTime();

        for(int i = numberOfTests; i > 0; i--)
        {
            for(int n : numbers)
            {
                accum += Math.sqrt(n);
            }
        }
        System.out.println("Test_SQRT.testMathSqrtMethod_Speedtest");
        return testReport(numberOfTests, startTime, accum);
    }
}
