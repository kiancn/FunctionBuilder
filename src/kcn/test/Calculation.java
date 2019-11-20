package kcn.test;

import java.util.ArrayList;

public class Calculation
{
    private final Operator[] operators; // operators will operate on the factors

    private Calculation(CalcBuilder builder)
    {
        operators = new Operator[builder.operators.size()];

        int operatorCount = 0;
        for(Operator operator : builder.operators)
        {
            operators[operatorCount] = operator;
            operatorCount++;
        }
    }

    public Calculation(Operator... operatorList)
    {
        operators = new Operator[operatorList.length];
        for(int i = 0; i < operatorList.length; i++)
        {
            operators[i] = operatorList[i];
        }
    }

    private int getNumberOfExpectedVariables(){ return operators.length + 1; }

    private Operator[] getOperators(){ return operators; }

    /* should fail fast if number of factors does not equal the expected number. */
    public double calc(double... inputFactors)
    {
        // do check of number of factors supplied/expected
        if(inputFactors.length != operators.length + 1)
        {
            System.out.println("From Func: Wrong number of variables input:" + inputFactors.length
                               + ". This equation requires " + operators.length + 1 + " variables.");
            return -1;
        }

        double runningTotal = inputFactors[0];

        // do all the calculatitions
        for(int i = 1; i < inputFactors.length; i++)
        {
            switch(operators[i - 1])
            {
                case Plus:
                    runningTotal += inputFactors[i];
                    break;
                case Minus:
                    runningTotal -= inputFactors[i];
                    break;
                case Multiply:
                    runningTotal *= inputFactors[i];
                    break;
                case Divide:
                    runningTotal /= inputFactors[i];
                    break;
                case Mod:
                    runningTotal %= inputFactors[i];
                    break;
                case Pow:
                    runningTotal = Math.pow(runningTotal, inputFactors[i]);
                    break;
                case Root:
                    runningTotal = Math.pow(Math.exp (1/inputFactors[i]),Math.log(runningTotal));
                    break;
            }
        }

        return runningTotal; // no implementado
    }

//    enum Operator
//    {
//        Divide, Minus, Mod, Multiply, Plus, Pot, SqrRoot
//    }

    public static class CalcBuilder
    {
        private ArrayList<Operator> operators;

        public CalcBuilder create()
        {
            operators = new ArrayList<>();
            return this;
        }

        public CalcBuilder plus()
        {
            operators.add(Operator.Plus);
            return this;
        }

        public CalcBuilder minus()
        {
            operators.add(Operator.Minus);
            return this;
        }

        public CalcBuilder dividedBy()
        {
            operators.add(Operator.Divide);
            return this;
        }

        public CalcBuilder modulus()
        {
            operators.add(Operator.Mod);
            return this;
        }

        // I decided off the bat to double the potency method, because I like the second name better.
        public CalcBuilder pow()
        {
            operators.add(Operator.Pow);
            return this;
        }
        // I decided off the bat to double the potency method, because I like the second name better. It's
        // bad style, I know.
        public CalcBuilder raisedBy()
        {
            operators.add(Operator.Pow);
            return this;
        }

        public CalcBuilder root()
        {
            operators.add(Operator.Root);
            return this;
        }

        public CalcBuilder multiplyBy()
        {
            operators.add(Operator.Multiply);
            return this;
        }

        public Calculation build()
        {
            return new Calculation(this);
        }
    }

    public static double equateIt(Calculation calculation, double... values){
        return calculation.calc(values);
    }
}
