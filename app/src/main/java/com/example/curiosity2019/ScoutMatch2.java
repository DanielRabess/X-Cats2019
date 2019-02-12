package com.example.curiosity2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoutMatch2 extends AppCompatActivity {

    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match2);


        button = (Button)findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(ScoutMatch2.this, ScoutMatch1.class));
            }
        });

        button2 = (Button)findViewById(R.id.nextButton);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ScoutMatch2.this, ScoutMatch3.class));

            }

        });
    }
}
