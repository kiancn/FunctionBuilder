package kcn.test;

import java.util.ArrayList;

/* This solution satisfies a lot of lacks of the first one, and I believe */

public class Calculation
{

    private final int numberOfExpressions;
    private final int numberOfFactors;
    // for each expression
    private ArrayList<Operator> listOfBindingOperators;
    private ArrayList<ArrayList<Operator>> listsOfExpressionOperators; // list contains lists of operators; a list

//    private int positionInFactorCount; // tracks position in

    private Calculation(CalcBuilder builder)
    {
        listOfBindingOperators = builder.listOfBindingOperators;
        listsOfExpressionOperators = builder.listsOfExpressionOperators;
        numberOfExpressions = builder.numberOfExpressions;
        numberOfFactors = builder.numberOfFactors;
    }

    public static double equateIt(Calculation calculation, double... values)
    {
        return calculation.calc(values);
    }

    /* should fail fast if number of factors does not equal the expected number. */
    public double calc(double... inputFactors)
    {
        /////////////
        //Precalculation debug chunk
        System.out.print("\nSupplied values: ");
        for(double value : inputFactors) { System.out.print("[" + value + "] "); }
        System.out.println("\nExpression operators:    " + listsOfExpressionOperators);
        System.out.println("Binding operators: " + listOfBindingOperators);
        /////

       int positionInFactorCount = 0; // resetting position for new calculation

        // do check of number of factors supplied/expected
        if(inputFactors.length != numberOfFactors)
        {
            System.out.println("From Func: Wrong number of variables input:" + inputFactors.length
                               + ". This equation requires " + numberOfFactors + " variables.");
            return -1;
        }
        /* keeps track of results of each expression */
        double[] expressionResults = new double[numberOfExpressions];

        /* this value is ++'ed every time an Expression is encountered */
        for(int expressionCount = 0; expressionCount < numberOfExpressions; expressionCount++)
        {
            expressionResults[expressionCount] =
                    calculateExpression(listsOfExpressionOperators.get(expressionCount),
                                        inputFactors,
                                        positionInFactorCount);

            positionInFactorCount += listsOfExpressionOperators.get(expressionCount).size() + 1;
        }

        /* if there is only one result, return that calculation */
        if(listOfBindingOperators.size() == 0)
        {
            return expressionResults[expressionResults.length - 1];
        }
        // else, return the calculation of the combined function/expression (it has severe limits to be
        // described; )

        return calculateExpression(listOfBindingOperators, expressionResults, 0);
    }


    private double calculateExpression(ArrayList<Operator> operators, double[] inputFactors,
                                       int positionInFactorCount)
    {
        double tempResult = inputFactors[positionInFactorCount];

        System.out.println("Calculation starting at position " + positionInFactorCount);
        System.out.print("Calculation: " + tempResult);

        for(int i = positionInFactorCount + 1, j = 0; j <= operators.size() - 1; i++, j++)
        {
            switch(operators.get(j))
            {
                case Plus:
                    tempResult += inputFactors[i];
                    break;
                case Minus:
                    tempResult -= inputFactors[i];
                    break;
                case Multiply:
                    tempResult *= inputFactors[i];
                    break;
                case Divide:
                    tempResult /= inputFactors[i];
                    break;
                case Mod:
                    tempResult %= inputFactors[i];
                    break;
                case Pow:
                    tempResult = Math.pow(tempResult, inputFactors[i]);
                    break;
                case Root:
                    tempResult = Math.pow(Math.exp(1 / inputFactors[i]), Math.log(tempResult));
                    break;
            }
            System.out.print(" " + operators.get(j) + " " + inputFactors[i]);
        }

        System.out.println(" = " + tempResult);

        return tempResult;
    }


    /* Internal class - builder pattern ... electric magic */

    public static class CalcBuilder
    {
        private int numberOfExpressions;
        private int numberOfFactors;
        private boolean newExpression = false;

        private ArrayList<Operator> listOfBindingOperators;
        private ArrayList<ArrayList<Operator>> listsOfExpressionOperators;


        public CalcBuilder create()
        {

            listOfBindingOperators = new ArrayList<>();
            listsOfExpressionOperators = new ArrayList<>();
            listsOfExpressionOperators.add(new ArrayList<>()); // newing up the first list

            numberOfExpressions = 0;
            numberOfFactors = 1;
            newExpression = false;
            return this;
        }

        public Calculation build()
        {
            numberOfFactors += numberOfExpressions;
            numberOfExpressions += 1;
            return new Calculation(this);
        }

        // tiny utility, because repetition makes me hate typing
        private CalcBuilder addOperator(Operator operator)
        {
            /* if its a new expression, the next sign must be the desired connecting operator */
            if(newExpression)
            {
                listOfBindingOperators.add(operator);

                listsOfExpressionOperators.add(new ArrayList<Operator>());
                numberOfExpressions++;
                newExpression = false;
            } else
            {
                /* add operator to the appropriate list of operators */
                listsOfExpressionOperators.get(numberOfExpressions).add(operator);
                numberOfFactors++;
            }
            return this;
        }

        public CalcBuilder expression()
        {
            newExpression = true;
            return this;
        }

        public CalcBuilder plus()
        {
            return addOperator(Operator.Plus);
        }

        public CalcBuilder minus()
        {
            return addOperator(Operator.Minus);
        }

        public CalcBuilder dividedBy()
        {
            return addOperator(Operator.Divide);
        }

        public CalcBuilder modulus()
        {
            return addOperator(Operator.Mod);
        }

        // I decided off the bat to double the potency method, because I like the second name better.
        public CalcBuilder pow()
        {
            return addOperator(Operator.Pow);
        }

        // I decided off the bat to double the potency method, because I like the second name better. It's
        // bad style, I know.
        public CalcBuilder raisedBy()
        {
            return addOperator(Operator.Pow);
        }

        public CalcBuilder root()
        {
            return addOperator(Operator.Root);
        }

        public CalcBuilder multiply()
        {
            return addOperator(Operator.Multiply);
        }
    }
}
