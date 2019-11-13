package burrellyang.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;

public class UserGuide extends AppCompatActivity {
    private Button buttonReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);

        buttonReturn = findViewById(R.id.buttonReturn);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToCalculator();
            }
        });

        //should load functions...
    }

    public void returnToCalculator(){
        // Return to the previous activity
        super.onBackPressed();
    }

}

