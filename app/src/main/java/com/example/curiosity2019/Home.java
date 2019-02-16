package com.example.curiosity2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Prevents keyboard from popping up on activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        button = (Button)findViewById(R.id.buttonlogout);
        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
            startActivity(new Intent(Home.this, MainActivity.class));
        }
        });

        button2 = (Button)findViewById(R.id.buttonScoutMatch);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(Home.this, ScoutTabView.class));
            }
        });
}
}