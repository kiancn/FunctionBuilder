# FunctionBuilder - more like Calculation builder.
 
 
The Calculation class allows the creation of objects able to perform complex calculations.
<p>You can check out the examples in the Test_TextCalculation-class 
or the examples of how to use the builder pattern in CalculationTest-class.<p></p>

    Basic use:
        // may you want to calculate hypotenuse's  - sprt(a^2+b^2)
    Calculation pythagoras = new Calculation("(n^2)+(n^2)r(2)"); 
    
        // you'd do a calculation like this;
    double someHypotenuse = pythagoras.calc(3,4);       

  <p><b>Syntax for constructing calculation strings:</b></p>
  
     First character must be a '('. Last character must be a ')'.
  
  <p>Second char of a calc string must be either a number (marking a constant),
  or an n (marking a variable input, which can then be supplied when running calc(double...)
  to do a calculation).</p>
  <p>In general a calculation can consist of factors (constants or input variables) and operators;
  </p>
  <p>Implemented operators are: <b>/ , * , - , + , r (root), p (pow), m (mod)</b></p>
  <p>All implemented operators are accept two factors and return a double.</p>
  <p></p>
  <p><b>Single expressions</b> are written between parenthesis; a single expression
  is any combination of factors and operators that can be resolved going strictly left
  to right.</p>
  <p>Negative constants are prefixed with ' ~ ' (and NOT - ) </p>
  <p>Unlike normal java math notation, this is a possible single expression: (n-n*n)</p>
  In customary math notation you'd write that like: (n-n)*n
  <p></p>
  <p>A few examples:
  
          TextCalculation syntax:   4*~5/5
          Orinary math syntax:      ((4*-5)/5)
          
          TextCalculation syntax:   1+3*5/6
          Orinary math syntax:      (((1+3)*5)/6)
          
          TextCalculation syntax:   4*5-6*5p2+1
          Orinary math syntax:      ((((4*5)-6)*5)pow2)+1)
          
  <p><i>Be aware that in all examples, factors that you need to be input/
  method parameter variables are written as ' n '. </i></p>
  <p></p>
  </p>
  <p></p>
  <p><b>Binding operators</b> is my term of art for operators placed <i>outside parenthesis</i>:
  a binding operator is any operator mentioned above; like above, each operator is resolved
  strictly going from left to right. A binding operator acts on the two evaluated expressions
  immediately adjacent to it.</p>
  
      A binding operator is written as ')' 'operator' '('
      
      Pattern :  )[/*-+rm] ( 
      
      TextCalculation syntax:   5+6*5)*(5/5         <- here )*( is a binding operator
      Orinary math syntax:      ((5+6)*5)*(5/5)     
      
  
  <p>You can get the pythagorean sentence to calculate the hypotenuse of a right angled triangle like: </p>
  
          Calculation hypotenuse = new Calculation("(n^2)+(n^2)r(2)");          
            // Or %-increase from A to B:
          Calculation increase = new Calculation("(n/n-1*100)");

<p>
 
         /* If you decide to work directly with the CalcBuilder
          this is how you might declare the most famous theorem of Pythagoras as a Calculation*/

        // WITHOUT constants:        
        // Length of hypotenuse: length = ((x^y)+(z^v))root(w)  
         
        Calculation pytagoras = new Calculation.CalcBuilder().
                create(). // performs baseline initialization on builder-object
                pow().                  // analogous to:   ((x^y
                expression().plus().    // analogous to:        )+(
                pow().                  // analogous to:           z^v
                expression().root()     // analogous to:              ))root(w)
                .build(); // returns the finished Calculation object

        System.out.println("sprRoot(5^2 + 4^2) = " + pytagoras.calc(5, 2, 4, 2, 2));        
        System.out.println("sprRoot(3^2 + 4^2) = " + pytagoras.calc(3, 2, 4, 2, 2));
        
        
        // WITH constants:
         // Length of hypotenuse: length = ((x^2)+(y^2))root(2)
         
        Calculation pytagoras = new Calculation.CalcBuilder().
                create(). // performs baseline initialization on builder-object
                pow().constant(2)                       ((x^2
                expression().plus().                         )+(
                pow().constant(2)                               y^2
                expression().root().constant(2)                    ))root(2)
                .build(); // returns the finished Calculation object

         // which is then called with only two arguments, so:
        System.out.println("sprRoot(5^2 + 4^2) = " + pytagoras.calc(5, 4));        
        System.out.println("sprRoot(3^2 + 4^2) = " + pytagoras.calc(3, 4));
        
        // Note: 
        // There is no square root, but the general root-function;
        // so ' square root of x ', is pseudo-written like ' x root 2 ').
        
<p></p>

<p></p>
<p><b>More soft practical introduction upcoming .. soon. </b></p>
<p></p>
<p>*/******/*</p>

        */***I realize this sections contains a bit of linguistic malfunction ***/*
        */***I'll fix it. But it it's readable/decipherable. ***/*

<p><b>Unfinished technical intro:</b></p>
<p></p>
<p><b>Calculations are evaluated in the following sequence of steps:</b></p>
<p>a. first all (single) expressions are resolved
(binding operators are <i>not</i>).</p>
<p>b. second the 'un-bound' expressions are interpreted as belonging to one single expression consisting
of the results
of the previous calculations AND the binding operators (:a binding operators bind evaluated expressions
through its operation upon them (and the result)); it is resolved/calculated, and there is your
answer...</p>
<p></p>
<p>A <b>single expression</b> consist of factors (numbers) and operators; the term is here used so that
an expression is any set of factors and operators that can be evaluated/calculated going from left to
right (see rules).</p>
<p>A <b>binding operator is any operator <i>+, -, /, *, mod, pow </i>or<i> root</i></b> acting on
two expressions that could be written in seperate parenthesis, like <i>(2) * (5+7)</i> where * is a binding operator.</p>

     // To turn an operater into a binding operator, call expression() before calling the <operator>() 
     // Declaring a + operator in a calculation as a binding operator is done like this:
     
     ...().expression().plus(). ..
           
     // And just to hammer home the principle; a - can be declared as binding like:
     
     ...().expression().minus(). ..           
     

<p></p>

![There are rules](https://raw.githubusercontent.com/kiancn/FunctionBuilder/master/ThereAreNoRules.png)
<p><b>There are a few rules:</b></p>
<p><i><b>1. Expressions are evaluated left to right: </b></i>
<p>b. <i>Operators <b>act</b> on the running total/result of current expression </i>with the next
single factor (for a binding operator, this is the next whole expression)):
<b> <\runningResult \operator \nextFactor> [runningResult] </b></p>
<b>Practical example:</b>
<p>This is a possible single expression: ((4+6)/5)*2</p>

    // expression can be declared as a Calculation like this:
    Calculation calcula = new Calculation.CalcBuilder().create().
                                                        plus().
                                                        divideBy().
                                                        multiply().
                                                        build();

<p>NOT a possible single expression: (2*((4+6)/5))</p>
    
    // it would need to be declared like following:
    Calculation calcula = new Calculation.CalcBuilder().create().
                                                        expression().multiply().
                                                        plus.
                                                        divideBy().
                                                        build();
                                                  
     // Explanation: (2*((4+6)/5)) is not a single expression, but it is very possible 
     // to declare it, as immidiately above, using the expression() method indicate
     // the separation of the two calculations.
     
<p></p>
<p><b><i>2. A calculation with binding operators can have nested binding operators ONLY IF</i></b>
the nests can be resolved with the second calculation round (see above) being made
strictly going left to right. (Internally, the 'second round' of calculation - where the binding
operators get to operate on the expressions evaluated in the 'first round') </p>
<p><b><i>3. If an ' expression().<\binding operator>() ' is placed first</i></b> in the declaration
of a calculation, this it is interpreted to mean the the binding operator left operand is a single
number/factor, and the the right operand is an (evaluated) expression.</p>
<p></p>
<p><i>There are probably a few more rules that I didn't realize yet, sorry. This is a work in
progress and I'm not a mathematician.</i></p>
<p></p><p></p>
<p><b>All comments and feedback is welcome; this is work in progress, and I'd love great ideas.1</b></p>
