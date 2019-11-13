package burrellyang.calculatorapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CalculatorTest {

    @Rule
    public ActivityTestRule<Calculator> calcActivityTestRule = new ActivityTestRule<Calculator>(Calculator.class);
    private Calculator calcActivity = null;

    @Before
    public void setUp() {
        calcActivity = calcActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void testLaunch(){
        Button button0 = calcActivity.findViewById(R.id.button0);
        assertNotNull(button0);
        EditText myText = calcActivity.findViewById(R.id.myText);
        assertNotNull(myText);
    }

    @Test
    @UiThreadTest
    public void testButtons(){
        Button buttonDclPt = calcActivity.findViewById(R.id.buttonDclPt);
        Button button0 = calcActivity.findViewById(R.id.button0);
        Button button1 = calcActivity.findViewById(R.id.button1);
        Button button2 = calcActivity.findViewById(R.id.button2);
        Button button3 = calcActivity.findViewById(R.id.button3);
        Button button4 = calcActivity.findViewById(R.id.button4);
        Button button5 = calcActivity.findViewById(R.id.button5);
        Button button6 = calcActivity.findViewById(R.id.button6);
        Button button7 = calcActivity.findViewById(R.id.button7);
        Button button8 = calcActivity.findViewById(R.id.button8);
        Button button9 = calcActivity.findViewById(R.id.button9);
        Button buttonEqual = calcActivity.findViewById(R.id.buttonEqual);
        Button buttonAC = calcActivity.findViewById(R.id.buttonAC);
        Button buttonDiv = calcActivity.findViewById(R.id.buttonDiv);
        Button buttonMul = calcActivity.findViewById(R.id.buttonMul);
        Button buttonSub = calcActivity.findViewById(R.id.buttonSub);
        Button buttonAdd = calcActivity.findViewById(R.id.buttonAdd);
        Button buttonComma = calcActivity.findViewById(R.id.buttonComma);
        Button buttonX = calcActivity.findViewById(R.id.buttonParameterX);
        Button buttonY = calcActivity.findViewById(R.id.buttonParameterY);
        Button buttonZ = calcActivity.findViewById(R.id.buttonParameterZ);
        Button buttonLeftArrow = calcActivity.findViewById(R.id.buttonLeftArrow);
        Button buttonRightArrow = calcActivity.findViewById(R.id.buttonRightArrow);
        Button buttonBackspace = calcActivity.findViewById(R.id.buttonBackspace);
        Button buttonLBracket = calcActivity.findViewById(R.id.buttonLeftBracket);
        Button buttonPi = calcActivity.findViewById(R.id.buttonPi);
        Button buttonRBracket = calcActivity.findViewById(R.id.buttonRightBracket);
        Button buttonANS = calcActivity.findViewById(R.id.buttonAnswer);

        EditText myText = calcActivity.findViewById(R.id.myText);
        TextView resultText = calcActivity.findViewById(R.id.resultText);

        List<Button> buttonList1 = Arrays.asList(button0, button1, button2, button3, button4,
                button5, button6, button7, button8, button9, buttonDclPt, buttonDiv, buttonMul, buttonSub, buttonAdd,
                buttonX, buttonY, buttonZ, buttonComma, buttonPi, buttonANS, buttonLBracket, buttonRBracket);

        for(Button button : buttonList1){
            button.performClick();
        }

        assertEquals("0123456789./*-+xyz,piANS()", myText.getText().toString());

        buttonBackspace.performClick();
        buttonLeftArrow.performClick();
        button0.performClick();
        buttonRightArrow.performClick();
        button1.performClick();

        assertEquals("0123456789./*-+xyz,piANS0(1", myText.getText().toString());

        buttonEqual.performClick();

        assertEquals("Input Error", resultText.getText().toString());

        buttonAC.performClick();

        assertEquals("", myText.getText().toString());
    }

    @After
    public void tearDown() {
        calcActivity = null;
    }
}