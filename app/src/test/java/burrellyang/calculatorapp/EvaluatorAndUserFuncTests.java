package burrellyang.calculatorapp;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

import junit.extensions.ActiveTestSuite;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class EvaluatorAndUserFuncTests {
    public static final char SquareRoot = (char) 8730;
//    List<String> functionList = Arrays.asList("sin", "cos", "tan", "abs", "asin", "acos", "atan");
    List<Double> numList = Arrays.asList(1.32, 12.0, 120.0, -45.7);

    ExtendedEvaluator evaluator = new ExtendedEvaluator();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testTriangleFuncs(){
        String testContent = "sin()";
        for (double num : numList) {
            assertEquals(Double.toString(sin(num)), evaluator.evaluate("sin(" + num + ")").toString());
            assertEquals(Double.toString(cos(num)), evaluator.evaluate("cos(" + num + ")").toString());
            assertEquals(Double.toString(tan(num)), evaluator.evaluate("tan(" + num + ")").toString());
            assertEquals(Double.toString(asin(num)), evaluator.evaluate("asin(" + num + ")").toString());
            assertEquals(Double.toString(acos(num)), evaluator.evaluate("acos(" + num + ")").toString());
            assertEquals(Double.toString(atan(num)), evaluator.evaluate("atan(" + num + ")").toString());
            assertEquals(Double.toString(abs(num)), evaluator.evaluate("abs(" + num + ")").toString());
        }
    }


    @Test
    public void testCalculator(){
        String testContent = "log(2,3) - (3^(2) + ln(8) * 12)/(" + SquareRoot + "(5)) ";
        assertEquals("-13.599414200617844", evaluator.evaluate(testContent).toString());
    }

    @Test
    public void userFunctionsTest(){

        assertEquals(0,UserFunctions.countVariables(""));
        assertEquals(1,UserFunctions.countVariables("x"));
        assertEquals(2,UserFunctions.countVariables("x+y"));
        assertEquals(3,UserFunctions.countVariables("x+y+z"));

        assertEquals("x", UserFunctions.replaceVariables("x"));
        assertEquals("x+y", UserFunctions.replaceVariables("x+z"));
        assertEquals("x+y", UserFunctions.replaceVariables("y+z"));
        assertEquals("x+y", UserFunctions.replaceVariables("x+y"));
        assertEquals("x", UserFunctions.replaceVariables("z"));
        assertEquals("x", UserFunctions.replaceVariables("y"));
        assertEquals("x+y+z", UserFunctions.replaceVariables("x+y+z"));
        assertEquals("", UserFunctions.replaceVariables(""));

        boolean[] myBooleanArray = new boolean[3];
        for(int i=0; i<myBooleanArray.length; i++){
            myBooleanArray[i] = true;
        }
        assertEquals(myBooleanArray[0],UserFunctions.detectVariables("x")[0]);

        assertNull(UserFunctions.getAllUserFunctions());
    }




}