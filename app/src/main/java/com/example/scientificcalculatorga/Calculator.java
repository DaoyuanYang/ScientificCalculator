package com.example.scientificcalculatorga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    EditText myText;

    private List<Button> buttonList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        myText = findViewById(R.id.myText);
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

        buttonList = Arrays.asList(buttonEqual,button0,button1,button2,button3,button4,
                button5,button6,button7,button8,button9,buttonDclPt);


        for (final Button butt : buttonList){
            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appendOnExpression((String) butt.getText());
                }
            });
        }
    }

    public void appendOnExpression(String str){
        if (str == "+" || str == "-" || str == "*" ||str == "/") {
            myText.setText(myText.getText().toString() + str);
        }
        else{
            myText.setText(str);
        }
    }
}
