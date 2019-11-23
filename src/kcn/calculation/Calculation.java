package kcn.calculation;

import java.util.ArrayList;

/* This solution satisfies a lot of lacks of the first one, and I believe */

/**
 * The Calculation class allows the creation of objects able to perform complex calculations.
 * <p>You can check out the examples in the CalculationTest-class.</p>
 * <p></p>
 * <p><b>Calculations are evaluated in the following sequence of steps:</b></p>
 * <p>a. first all (single) expressions are resolved
 * (binding operators are <i>not</i>).</p>
 * <p>b. second the 'un-bound' expressions are interpreted as belonging to one single expression consisting
 * of the results
 * of the previous calculations AND the binding operators (:a binding operators bind evaluated expressions
 * through its operation upon them (and the result)); it is resolved/calculated, and there is your
 * answer...</p>
 * <p></p>
 * <p> <b>A single expression</b> consist of factors (numbers) and operators; the term is here used so that
 * an expression is any set of factors and operators that can be evaluated/calculated going from left to
 * right (see rules).</p>
 * <p></p>
 * <p>There are a few rules:</p>
 * <p><i><b>1. Expressions are evaluated left to right, </b></i>
 * <p>a. A <i><b>single</b></i> expression must have non-binding operators mentioned
 * before the mention of binding operators: </p>
 * <b>Practical example:</b>
 * <p>This is a possible expression: (((4+6)/5)*2)</p>
 * <p>NOT a possible expression: (2*((4+6)/5))</p>
 * <p>b. <i>Operators <b>act</b> on the running total/result of current expression </i>with the next
 * single factor (for a binding operator, this is the next whole expression)):
 * <\runningResult \operator \nextFactor></runningResult></b></p>
 * <p></p>
 * <p><b><i>2. A calculation with binding operators can have nested binding operators ONLY IF</i></b>
 * the nests can be resolved with the second calculation round (see above) being made
 * strictly going left to right. (Internally, the 'second round' of calculation - where the binding
 * operators get to operate on the expressions evaluated in the 'first round') </p>
 * <p><b><i>3. If an ' expression().<\binding operator>() ' is placed first</i></b> in the declaration
 * of a calculation, this it is interpreted to mean the the binding operator left operand is a single
 * number/factor, and the the right operand is an (evaluated) expression.</p>
 * <p></p>
 * <p><i>There are probably a few more rules that I didn't realize yet, sorry. This is a work in
 * progress and I'm not a mathematician.</i></p>
 * <p>* There is no square-root, but there is a root(),
 * which is the general root function ('sqrRoot of x' is 'x root 2')</p>
 * <p>* </p>
 */
public class Calculation
{

    private final int numberOfExpressions;
    private final int numberOfFactors;

    private ArrayList<Operator> listOfBindingOperators;
    private ArrayList<ArrayList<Operator>> listsOfExpressionOperators; // list contains lists of operators; a list

    //    private int positionInFactorCount; // tracks position in
    private boolean logCalculationToConsole; // if true, Calculation object will log procress to console.

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

    /**
     * Calll method with to turn on logging of calculation process and calculation stats on
     */
    public void logToConsole(boolean logCalculationToConsole)
    {
        this.logCalculationToConsole = logCalculationToConsole;
    }

    /* should fail fast if number of factors does not equal the expected number. */
    public double calc(double... inputFactors)
    {
        if(logCalculationToConsole)
        {
            System.out.print("\nSupplied values: ");
            for(double value : inputFactors) { System.out.print("[" + value + "] "); }
            System.out.println("\nExpression operators:    " + listsOfExpressionOperators);
            System.out.println("Binding operators: " + listOfBindingOperators);
        }

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

        /* Calculating 'first level' of expressions (expressions between binding operators) */
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
        // else, return the calculation of the combined function/expressions
        return calculateExpression(listOfBindingOperators, expressionResults, 0);
    }


    private double calculateExpression(ArrayList<Operator> operators, double[] inputFactors,
                                       int positionInFactorCount)
    {
        double tempResult = inputFactors[positionInFactorCount];

        if(logCalculationToConsole)
        {
            System.out.println("Calculation starting at position " + positionInFactorCount);
            System.out.print("Calculation: " + tempResult);
        }

        /* ' i ' is set up like it is because the traversal of actual input-Factors
         happens across the calculation of multiple expressions (the first round of calculations
         go though user input arguments/numbers (and soon 'constants'), and the second go through the
         results of the first round of calculations.*/
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
            if(logCalculationToConsole){System.out.print(" " + operators.get(j) + " " + inputFactors[i]);}
        }

        if(logCalculationToConsole){System.out.println(" = " + tempResult);}

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

        /**
         * Conceptually expression() indicates the separation of two expression
         * AND that next mentioned operator is a binding operator: this means
         * <p>a) that <i>that next operator</i> will act on all previously
         * calculated expressions,</p>
         * <p>and b) the operator(-method) after the binding operator starts a new expression</p>
         * <p>Important: if expression() is placed before any other operator, like this;
         * <p><p><i>create().expression().<\operator>()>. ... </i></p></p>
         * the first factor in the calculation (when calling calc()) will be acted upon
         * by the operator defined by the call following the expression()-call), so;</p>
         * <p><p><i>create().expression().<\OPERATOR>()>. ... </i></p></p>
         * <p> <>See examples is test class for clarification for now. I'm out of juice.<> </p>
         *
         */
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

        public CalcBuilder divideBy()
        {
            return addOperator(Operator.Divide);
        }

        public CalcBuilder modulus()
        {
            return addOperator(Operator.Mod);
        }

        // I decided off the bat to double the potency method, because I like the second name better. It's
        // bad style, I know. Functionality is identical; pow == raisedBy = true ....
        public CalcBuilder pow()
        {
            return addOperator(Operator.Pow);
        }

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
