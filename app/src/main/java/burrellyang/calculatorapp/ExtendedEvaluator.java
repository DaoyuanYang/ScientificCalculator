package burrellyang.calculatorapp;


import android.widget.TextView;

import java.util.Iterator;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Parameters;
import com.fathzer.soft.javaluator.StaticVariableSet;

/** A subclass of DoubleEvaluator that supports SQRT function.
 */
public class ExtendedEvaluator extends DoubleEvaluator {
    /** Defines the new function (square root).*/
    static String sqrtName = Character.toString(Calculator.SquareRoot);
    static String cbrtName = Character.toString(Calculator.CubeRoot);
    static String logName = "log";
    static String modName = "mod";
    static String asineName = "asin";
    static String acosName = "acos";
    static String atanName = "atan";
    private static String f0Name = "f0";
    private static String f1Name = "f1";
    private static String f2Name = "f2";
    private static String f3Name = "f3";
    private static String f4Name = "f4";
    private static String f5Name = "f5";

    private static final Function SQRT = new Function(sqrtName, 1);
    private static final Function CBRT = new Function(cbrtName, 1);
    private static final Function LOG = new Function(logName, 2);
    private static final Function MOD = new Function(modName, 2);
    private static final Function ASINE = new Function(asineName, 1);
    private static final Function ACOS = new Function(acosName, 1);
    private static final Function ATAN = new Function(atanName, 1); //this to atanName
    private static final Function F0 = new Function(f0Name, 0,3);
    private static final Function F1 = new Function(f1Name, 0,3);
    private static final Function F2 = new Function(f2Name, 0,3);
    private static final Function F3 = new Function(f3Name, 0,3);
    private static final Function F4 = new Function(f4Name, 0,3);
    private static final Function F5 = new Function(f5Name, 0,3);



    final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();


    private static final Parameters PARAMS;

    static {
        // Gets the default DoubleEvaluator's parameters
        PARAMS = DoubleEvaluator.getDefaultParameters();

        // add the new functions to these parameters
        PARAMS.add(SQRT);
        PARAMS.add(CBRT);
        PARAMS.add(LOG);
        PARAMS.add(MOD);
        PARAMS.add(ASINE);
        PARAMS.add(ACOS);
        PARAMS.add(ATAN);
        PARAMS.add(F0);
        PARAMS.add(F1);
        PARAMS.add(F2);
        PARAMS.add(F3);
        PARAMS.add(F4);
        PARAMS.add(F5);
    }

    public ExtendedEvaluator() {
        super(PARAMS);
    }

    @Override
    protected Double evaluate(Function function, Iterator<Double> arguments, Object evaluationContext) {
        if (function == SQRT) {
            // Implements the new function
            return Math.sqrt(arguments.next());
        }

        else
        if (function == CBRT){
            return Math.cbrt(arguments.next());
        }

        else
        if (function == LOG){
            // logx(y) = lg(y)/lg(x)

            Double arg1 = arguments.next();
            Double arg2 = arguments.next();

            return Math.log(arg2) / Math.log(arg1);
        }

        else
        if (function == MOD){
            return arguments.next() % arguments.next();
        }

        else
        if (function == ASINE){
            if(Calculator.isDegrees){
                return Math.asin(arguments.next())*180.0/Math.PI; //return angle in degrees
            }
            else{
                return Math.asin(arguments.next()); //return radians
            }
        }

        else
        if (function == ACOS){
            if(Calculator.isDegrees){
                return Math.acos(arguments.next())*180.0/Math.PI; //return angle in degrees
            }
            else{
                return Math.acos(arguments.next()); //return radians
            }
        }

        else
        if (function == ATAN){
            if(Calculator.isDegrees){
                return Math.atan(arguments.next())*180.0/Math.PI; //return angle in degrees
            }
            else{
                return Math.atan(arguments.next()); //return radians
            }
        }

        else
        if (function == SINE){
            if (Calculator.isDegrees){
                return Math.sin(arguments.next()*Math.PI/180.0); //return angle in degrees
            }
            else{
                return Math.sin(arguments.next()); //return radians
            }
        }

        else
        if (function == COSINE){
            if (Calculator.isDegrees){
                return Math.cos(arguments.next()*Math.PI/180.0); //return angle in degrees
            }
            else{
                return Math.cos(arguments.next()); //return radians
            }
        }

        else
        if (function == TANGENT){
            if (Calculator.isDegrees){
                return Math.tan(arguments.next()*Math.PI/180.0); //return angle in degrees
            }
            else{
                return Math.tan(arguments.next()); //return radians
            }
        }

        else
            if (function == F0 || function == F1 || function == F2 || function == F3 || function == F4 || function == F5){
                int userFuncCalled = Integer.parseInt(""+function.getName().charAt(1));
                String userFuncRaw = UserFunctions.userFuncs[userFuncCalled];
                String userFunc = userFuncRaw.replace(function.getName()+": ","");
                int numVariables = UserFunctions.countVariables(userFunc);
                for(int i=0; i<=numVariables; i++){
                    try {
                    if(i==1){
                        variables.set("x",arguments.next());
                    }
                    if(i==2){
                        variables.set("y",arguments.next());
                    }
                    if(i==3){
                        variables.set("z",arguments.next());
                    }}
                    catch(Exception e){
                        //do nothing
                }

                }
                if(arguments.hasNext()){
                    throw new IllegalArgumentException();
                }
                return evaluate(userFunc, variables);

            }
        else {
            // If it's another function, pass it to DoubleEvaluator
            return super.evaluate(function, arguments, evaluationContext);
        }
    }
}


