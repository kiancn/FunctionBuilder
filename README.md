# FunctionBuilder - more like Calculation builder.
 
<b>The Calculation class </b>allows the creation of objects able to perform complex calculations.

    Example, constructing a Calculation using a String:
        // may you want to calculate hypotenuses  - sprt(a^2+b^2) = hypotenuse
    Calculation pythagoras = new Calculation("(n^2)+(n^2)r(2)"); 
    
        // you'd do a calculation like this;
    double someHypotenuse = pythagoras.calc(3,4);       

<p><b>Formula class</b> holds a Calculation object and instances of <b>Formula class</b> is able to do  between 0 and n-length series calculations on input factors
supplied as Double arrays.</p>
<p>A description should be made, but I can't be bothered right now, so here is an example:</p>

    Example:
    
    Formula formulaEx = new Formula("(n*n)^(n+1)");
    double[] results = formulaEx.computeSeries(
                            new Double[]{2d,3d,4d}, // factor n (1st n)
                            new Double[]{2d,3d,4d}, // factor n (2nd n)
                            new Double[]{2d,3d,4d}); // factor n (3rd n);

<p>If you need to do a single calculation at a time, use a Calculation class object instead.</p>


<b>There are certain special rules on how calculations can be put together: read on (or see class).
For rules on how to form a calculation properly, see the top of that class - or read on).</b>
 
<p>You can check out the examples in the Test_TextCalculation-class 
or the examples of how to use the builder pattern in CalculationTest-class.<p></p>

    /*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~*\ 
    
  #<p><H3><b>Syntax for constructing calculation strings:</b></H3></p>
  
     First character must be a '('. Last character must be a ')'.
  
  <p>Second char of a calc string must be either a number (marking a constant),
  or an n (marking a variable input, which can then be supplied when running calc(double...)
  to do a calculation).</p>
  <p>In general a calculation can consist of factors (constants or input variables) and operators;
  </p>
  
     Implemented operators are: / , * , - , + , r (root), p (pow), m (mod)
  
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
                                
                                    Declaration string:
  
          TextCalculation syntax:   4*~5/5          "(4*~5/5)"
          Orinary math syntax:      ((4*-5)/5)      
          
          TextCalculation syntax:   1+3*5/6         "(1+3*5/6)"
          Orinary math syntax:      (((1+3)*5)/6)
          
          TextCalculation syntax:   4*5-6*5p2+1     "(4*5-6*5p2+1)"
          Orinary math syntax:      ((((4*5)-6)*5)pow2)+1)
          
          TextCalculation syntax:   n^2)+(n^2)r(2   "(n^2)+(n^2)r(2)"   
          Orinary math syntax:      sqrt((a^2)+(b^2))
          
  <p><i>Be aware that in all examples, factors that you need to be input/
  method parameter variables are written as ' n '. </i></p>
  <p></p>  
  <p></p>
  <p><b>Binding operators</b> is my term of art for operators placed <i>outside parenthesis</i>:
  a binding operator is any operator mentioned above; like above, each operator is resolved
  strictly going from left to right. A binding operator acts on the two evaluated expressions
  immediately adjacent to it.</p>
  
      A binding operator is written as ')' 'operator' '('
      
      Pattern :  ) [/*-+rm] ( 
      
      TextCalculation syntax:   5+6*5)*(5/5         <- here )*( is a binding operator
      Orinary math syntax:      ((5+6)*5)*(5/5)     
      
  
  <p>You can get the pythagorean sentence to calculate the hypotenuse of a right angled triangle like: </p>
  
          Calculation hypotenuse = new Calculation("(n^2)+(n^2)r(2)");          
            // Or %-increase from A to B:
          Calculation increase = new Calculation("(n/n-1*100)");

<p>
 
        .\~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/.
        

#<p><H3><b>Unfinished Calculation Syntax intro:</b></H3></p>

<p><i>Definitions:</i></p>
<p></p>
<p>A) A <b>single expression</b> consist of factors (numbers) and operators; the term is here used so that
an expression is any set of factors and operators that can be evaluated/calculated going from left to
right (see rules).</p>
<p>B) A <b>binding operator is any operator <i>+, -, /, *, mod, pow </i>or<i> root</i></b> acting on
two expressions that could be written in seperate parenthesis, like <i>(2) * (5+7)</i> where * is a binding operator.</p>
<p>Ba) Binding operators bind evaluated expressions through its operation upon them (and the produced result)</p>
 
     
     
<p></p>     

     // To turn an operater into a binding operator, call expression() before calling the <operator>() 
     // Declaring a + operator in a calculation as a binding operator is done like this:
     
     CalcBuilder...().expression().plus(). ..      // string-constructor:   )+(
           
     // And just to hammer home the principle; a - can be declared as binding like:
     
     ...().expression().minus(). ..     // string:   )-(      
     
<p><i>Evaluation of calculation in short:</i></p>
<p>A. First all (single) expressions are resolved (binding operators are <i>not</i>).</p>
<p>B. Second the 'un-bound' expressions are interpreted as belonging to one single expression consisting
of the results
of the previous calculations (first round) AND the binding operators; it is resolved/calculated, and there is your
answer...</p>

     A possibly helpful example:
     Imagine a Calculation object declared like this:
     Calculation pyt = new Calculation("(nr2)+(n/2)"); 
    

<p><i>Tip: To get a view of how the Calculation works internally, call logToConsole(true) on a Calculation
 object, and it will print its process to the console (when calling calc(double...)). </i></p>

<p></p>
<p><H4>Calculations are evaluated in the following sequence of steps:</H4></p>

![There are rules](https://raw.githubusercontent.com/kiancn/FunctionBuilder/master/ThereAreNoRules.png)
<p><b>There are a few rules:</b></p>
<p><i>1. Expressions are evaluated left to right:</i>
<p><i>Operators <b>act</b> on the running total/result of current expression </i>with the next
single factor (for a binding operator, this is the next whole expression)):

    [next runningResult]   =  <\runningResult \operator \nextFactor>
     
<b>Practical example:</b>
<p>This is a possible single expression: ((4+6)/5)*2</p>

    // expression can be declared as a Calculation like this:
    Calculation calcula = new Calculation.CalcBuilder().create().
                                                        plus().
                                                        divideBy().
                                                        multiply().
                                                        build();
        // or, using constructor taking a string:
    Calculation calculb = new Calculation("(4+6/5*2)");

<p>NOT a possible single expression: (2*((4+6)/5)) (it <i>is</i> two)</p>
    
    // it can be/would need to be declared like following:
    Calculation calculc = new Calculation.CalcBuilder().create().
                                                        expression().multiply().
                                                        plus.
                                                        divideBy().
                                                        build();
        // or, using constructor taking a string:
    Calculation calculd = new Calculation("(2)*(4+6/5)");
                                                  
    // Explanation: (2*((4+6)/5)) is not a single expression, but it is very possible 
    // to declare it, as immidiately above, using the expression() method to indicate
    // the separation of the two calculations.
    
    // SO, (2*((4+6)/5)), expressed through the Calculation class is two expressions, not one.
    // And that calculation can be declared using string "(2)*(4+6/5)".
     
<p></p>

<p><b><i>2. A calculation with binding operators can have nested binding operators ONLY IF</i></b>
the nests can be resolved with the second calculation round (see above) being made
strictly going left to right. (Internally, the 'second round' of calculation - where the binding
operators get to operate on the expressions evaluated in the 'first round') </p>

<p><b><i>3. If an ' expression().<'binding operator'>() ' is placed first</i></b> in the declaration
of a calculation, this it is interpreted to mean that the binding operator's left operand is a single
number/factor, and that the right operand is an (evaluated) expression.</p>
<p></p>

#<p><H3>Using CalcBuilder() to create Calculations</H3></p>
 
 If you decide to work directly with the CalcBuilder
 this is how you might declare+initialize the most famous theorem of Pythagoras as a Calculation:

        // WITHOUT constants:        
        // Length of hypotenuse: length = ((x^y)+(z^v))root(w)  
         
        Calculation pytagoras = new Calculation.CalcBuilder().
                create(). // performs baseline initialization on builder-object
                pow().                  // analogous to:  "(n^n
                expression().plus().    // analogous to:        )+(
                pow().                  // analogous to:           n^n
                expression().root()     // analogous to:              )root(n)"
                .build(); // returns the finished Calculation object

        System.out.println("sprRoot(5^2 + 4^2) = " + pytagoras.calc(5, 2, 4, 2, 2));        
        System.out.println("sprRoot(3^2 + 4^2) = " + pytagoras.calc(3, 2, 4, 2, 2));
        
        
        // WITH constants:
         // Length of hypotenuse: length = ((x^2)+(y^2))root(2)
         
        Calculation pytagoras = new Calculation.CalcBuilder().
                create(). // performs baseline initialization on builder-object
                pow().constant(2)                      "(n^2
                expression().plus().                         )+(
                pow().constant(2)                               n^2
                expression().root().constant(2)                    )root(2)"
                .build(); // returns the finished Calculation object

         // which is then called with only two arguments, so:
        System.out.println("sprRoot(5^2 + 4^2) = " + pytagoras.calc(5, 4));        
        System.out.println("sprRoot(3^2 + 4^2) = " + pytagoras.calc(3, 4));
        
        // Note: 
        // There is no square root, but the general root-function;
        // so ' square root of x ', is pseudo-written like ' x root 2 ').
<p><i>There are probably a few more rules that I didn't realize yet, sorry. This is a work in
progress and I'm not a mathematician.</i></p>


#<p><b>Goals:<b></p>

        .\~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/.
        
        The major goal for next iteration is the integration of all relevant
        operators from java.lang.Math into Calculation/CalcBuilder/TextCalculation classes.
        Which means the introduction of unary operators.
        
        .\~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/*~:~* \~/.
        
<p><b>All comments and feedback is welcome; this is work in progress, and I'd love great ideas.</b></p>
