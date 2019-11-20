package kcn.test;

public class Main
{
    /**
     * Den her form virker kun på få typer udtryk; det er ikke muligt at lave udtryk med 'flere
     * parenteser'; er ved at indse mangler lige nu, og skriver videre,
     * men prøv at lave nogle funktioner.
     * Differentieringen skal jeg lige tygge på, det ser slet ikke umuligt ud;
     */

    public static void main(String[] args)
    {

        Calculation someCalculation = new Calculation.CalcBuilder()
                .create()
                .plus()
                .raisedBy()
                .minus().
                        build(); //((x + y)^z)-w
        //((x + y)^z)-w
        System.out.println(someCalculation.calc(2, 3, 2, 5)); // ((2 + 3)^2)-5
        System.out.println(someCalculation.calc(4, 2, 5, 500)); // ((4 + 2)^5)-500

        // du kan self også:


        double x = 2;
        double y = 5;
        System.out.println(" (2+2)^5-2 = "+ someCalculation.calc(x, x, y, x)); // (2+2)^5-2

        // og de kan indsættes i hinanden...

        Calculation raisingFunction = new Calculation.CalcBuilder().
                create().
                pow().
                build();

        System.out.println(
                raisingFunction.calc(
                        someCalculation.calc(x, x, y, x),
                        someCalculation.calc(1, 1, x, x))
                          );

        // ... og så har jeg lavet et alternativ til builder-patternet, måske bedre til runtime;
        // en konstuktør hvor du simpelthen supplerer en liste af Operators.

        Calculation multiply = new Calculation(Operator.Multiply);

        System.out.println(multiply.calc(10,5));

        // det er måske imod hele formålet, men selvfølgelig kan de også bruges i kald
        System.out.println("Den retvinklede trekant med korte sider 2 , 3 har langside på: " + hypotenuseOfRightAngledTriangle(3,3));
        System.out.println("Den retvinklede trekant med korte sider 3 , 4 har langside på: " + hypotenuseOfRightAngledTriangle(3,4));

        // og det her har potentiale:
        System.out.println(" 2 x 2 er " + Calculation.equateIt(multiply, 2, 2));
        System.out.println(" ((x + y ) ^ z) - w ");
        System.out.println(" ((13 + 34) ^ 6) - 21 ="
                           + Calculation.equateIt(someCalculation, 13, 34, 21, 21));


    }

    public static double hypotenuseOfRightAngledTriangle(double side1, double side2){

        Calculation calculation = new Calculation.CalcBuilder().create().plus().root().build();

        return calculation.calc(side1 * side1, side2 * side2, 2);
    }

    // Read in case of trouble: remember, first number is assumed in the definition of the equation.


}

