/**
 * Inspired by video : https://www.youtube.com/watch?v=EpP6KgJtHTk&t=504s
 *
 */
package com.example.scientificcalculatorga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator extends AppCompatActivity {
    private Boolean isEvaluated = false;

    private Button buttonDclPt;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonEqual;
    private Button buttonFunction;
    private Button buttonAC;

    private Button buttonDiv;
    private Button buttonMul;
    private Button buttonSub;
    private Button buttonAdd;

    EditText myText;
    TextView resultText;

    private List<Button> buttonList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        myText = findViewById(R.id.myText);
        resultText = findViewById(R.id.resultText);

        buttonDclPt = findViewById(R.id.buttonDclPt);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonFunction = findViewById(R.id.buttonFunction);
        buttonAC = findViewById(R.id.buttonAC);


        buttonDiv = findViewById(R.id.buttonDiv);
        buttonMul = findViewById(R.id.buttonMul);
        buttonSub = findViewById(R.id.buttonSub);
        buttonAdd = findViewById(R.id.buttonAdd);

        /**ButtonList contains all the input keys
         * Used for FUNC.appendOnExpression to decide whether the content needs to be cleared
         */
        buttonList = Arrays.asList(buttonEqual,button0,button1,button2,button3,button4,
                button5,button6,button7,button8,button9,buttonDclPt, buttonAC,
                buttonDiv, buttonMul, buttonSub, buttonAdd);

        //Set button clickListeners
        buttonFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToFunction();
            }
        });

        /**
         * Apply all input keys FUNC.appendOnExpression
         */
        for (final Button butt : buttonList){
            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appendOnExpression(butt.getText().toString());
                }
            });
        }
    }

    /**
     * To decide whether to clear the content of calculator text
     * @param str is keyPress value
     */
    public void appendOnExpression(String str){
        if (str.equals("=")) {
            isEvaluated = true;
            resultText.setText("ZERO");
        }
        else if (str.equals("AC")) myText.setText("");
        else {
            if (isEvaluated){
                isEvaluated = false;
                myText.setText("");
            }
            myText.setText(myText.getText().toString() + str);
        }
//        if (str == "+" || str == "-" || str == "*" ||str == "/") {
//            myText.setText(myText.getText().toString() + str);
//        }
//        else{
//            myText.setText(str);
//        }
    }

    public void jumpToFunction(){
        Intent intent = new Intent(this, FunctionDefining.class)
                .setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    public double calculateResult(){
        return 1.2;
    }
}
