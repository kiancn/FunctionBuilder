package kcn.test;

public class FSQRT
{
    public static double fastSquareRoot1(double x)
    {
        return Double.longBitsToDouble((Double.doubleToLongBits(x) - (1L << 52) >> 1) + (1L << 61));
    }

    public static double fastSquareRoot2(double x)
    {
//        double sqrt = Double.longBitsToDouble((Double.doubleToLongBits(x) - (1L << 52) >> 1) + (1L << 61));
        double sqrt =
                Double.longBitsToDouble((Double.doubleToLongBits(x) - (4503599627370496L) >> 1) + (2305843009213693952L));
        return 0.5 * (sqrt + (x / sqrt));
    }

    public double squareRoot(double x){
        return Math.sqrt(x);
    }



}
