package com.example.scientificcalculatorga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FunctionDefining extends AppCompatActivity {
    private Button buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_defining);

        buttonReturn = findViewById(R.id.buttonReturn);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        returnToCalculator();
                    }
                });
    }

    public void returnToCalculator(){
        finishActivity(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
    }
}
