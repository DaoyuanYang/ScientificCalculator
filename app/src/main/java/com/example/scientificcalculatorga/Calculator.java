package com.example.scientificcalculatorga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends AppCompatActivity {
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
    EditText myText = findViewById(R.id.myText);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

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


    }

    public void appendOnExpression(char ch){
        if (ch == '+' || ch == '-' || ch == '*' ||ch == '/') {
            myText.setText(myText.getText().toString() + ch);
        }
        else{
            myText.setText(ch);
        }
    }
}
