package kcn.test;

public class Recursion
{
    public int factorial(int base){

        if(base == 1)return 1;

        return base*factorial(base-1);
    }
}
