package com.example.curiosity2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ScoutMatch4 extends AppCompatActivity {

    Button button;
    Button button2;

    ImageView red1;
    ImageView red2;
    ImageView red3;
    ImageView blue1;
    ImageView blue2;
    ImageView blue3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_match4);

        button = (Button)findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ScoutMatch4.this, Home.class));

            }

        });

        button2 = (Button)findViewById(R.id.backButton);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ScoutMatch4.this, ScoutMatch3.class));

            }

        });

    }

}
