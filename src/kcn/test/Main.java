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

        Equation function = new Equation.FuncBuilder()
                .create()
                .plus()
                .raisedBy()
                .minus().
                        build(); //((x + y)^z)-w
        //((x + y)^z)-w
        System.out.println(function.calc(2, 3, 2, 5)); // ((2 + 3)^2)-5
        System.out.println(function.calc(4, 2, 5, 500)); // ((4 + 2)^5)-500

        // du kan self også:


        double x = 2;
        double y = 5;
        System.out.println(" (2+2)^5-2 = "+ function.calc(x, x, y, x)); // (2+2)^5-2

        // og de kan indsættes i hinanden...

        Equation raisingFunction = new Equation.FuncBuilder().
                create().
                pow().
                build();

        System.out.println(
                raisingFunction.calc(function.calc(x, x, y, x),
                        function.calc(1, 1, x, x)));

        // ... og så har jeg lavet et alternativ til builder-patternet, måske bedre til runtime;
        // en konstuktør hvor du simpelthen supplerer en liste af Operators.

        Equation multiply = new Equation(Operator.Multiply);

        System.out.println(multiply.calc(10,5));

        // det er måske imod hele formålet, men selvfølgelig kan de også bruges i kald
        System.out.println("Den retvinklede trekant med korte sider 2,3 har langside på: " + hypotenuseOfRightAngledTriangle(3,3));
        System.out.println("Den retvinklede trekant med korte sider 2,3 har langside på: " + hypotenuseOfRightAngledTriangle(3,4));

        // og det her har potentiale:
        System.out.println(" 2 x 2 er " + equateIt(multiply, 2,2));
        System.out.println(" ((x + y ) ^ z) - w ");
        System.out.println(" ((13 + 34) ^ 6) - 21 " + equateIt(function,13,34,21,21));

    }

    public static double hypotenuseOfRightAngledTriangle(double side1, double side2){

        Equation equation = new Equation.FuncBuilder().create().plus().root().build();

        return equation.calc(side1*side1,side2*side2,2);
    }

    // Read in case of trouble: remember, first number is assumed in the definition of the equation.
    public static double equateIt(Equation equation, double... values){
        return equation.calc(values);
    }

}

