package com.example.curiosity2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoutMatch1 extends AppCompatActivity {

    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match1);

        button = (Button)findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(ScoutMatch1.this, Home.class));
            }
        });

        button = (Button)findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ScoutMatch1.this, ScoutMatch2.class));

            }

        });


    }



}
