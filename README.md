# FunctionBuilder - more like Calculation builder.
 
The Calculation class allows the creation of objects able to perform complex calculations.
<p>You can check out the examples in the CalculationTest-class.</p>

<p>
 
         /* This is how you might declare the most famous theorem of Pythagoras as a Calculation*/
         
        // Length of hypotenuse: length = ((x^y)+(z^v))root(w)
        
        // WITHOUT constants:
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
        Calculation pytagoras = new Calculation.CalcBuilder().
                create(). // performs baseline initialization on builder-object
                pow().constant(2)                       ((x^2
                expression().plus().                         )+(
                pow().constant(2)                               y^2
                expression().root().constant(2)                    ))root(2)
                .build(); // returns the finished Calculation object

         // which is then called with only two arguments, so:
        System.out.println("sprRoot(5^2 + 4^2) = " + pytagoras.calc(5, 4,));        
        System.out.println("sprRoot(3^2 + 4^2) = " + pytagoras.calc(3, 4));
        
        // Note: 
        // There is no square root, but the general root-function;
        // so ' square root of x ', is pseudo-written like ' x root 2 ').
        // And yes, y, v, and w are all the constant 2.
        // Constants are first on my list of upcoming improvements.......
<p></p>

<p></p>
<p><b>More soft practical introduction upcoming .. soon. </b></p>
<p></p>
<p>*/******/*</p>
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
<\runningResult \operator \nextFactor></runningResult></b></p>
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
