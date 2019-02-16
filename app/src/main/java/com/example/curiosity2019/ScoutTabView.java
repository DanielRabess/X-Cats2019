package com.example.curiosity2019;

import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toolbar;

public class ScoutTabView extends AppCompatActivity implements  ScoutTab1.OnFragmentInteractionListener,
                                                                ScoutTab2.OnFragmentInteractionListener,
                                                                ScoutTab3.OnFragmentInteractionListener,
                                                                ScoutTab4.OnFragmentInteractionListener{
    //Page
    ViewPager viewPager;
    PagerAdapter adapter;


    //Data Memebers for Saving.....
    int activeAllianceColor;
    String startingPiece;
    String teamNumber;
    String sMatchNumber;
    String startingPosition;
    String startingLvl;

    //Fragment References For Messaging
    ScoutTab1 myTab1;
    ScoutTab2 myTab2;

    //Elements
    android.support.v7.widget.Toolbar myToolBar;
    TextView myToolBarTextView;
    android.support.design.widget.TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_tab_view);

        initializeDataMembers();

        //Prevents keyboard from popping up on activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Default Red
        myToolBar = findViewById(R.id.toolbar);
        myToolBar.setBackgroundColor(Color.RED);
        myToolBarTextView = findViewById(R.id.toolbarText);


        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Setup"));
        tabLayout.addTab(tabLayout.newTab().setText("Sandstorm"));
        tabLayout.addTab(tabLayout.newTab().setText("Controlled"));
        tabLayout.addTab(tabLayout.newTab().setText("Endgame"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setBackgroundColor(Color.RED);

        viewPager = (ViewPager)findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //save reference to tabs
        myTab1 = (ScoutTab1)adapter.instantiateItem(viewPager,0);

    }

    public void initializeDataMembers(){
        //Defaults.....
        activeAllianceColor = Color.RED;
        startingPiece = "Nothing";
        teamNumber = "Team : ";
        sMatchNumber = "Match # : ";
        startingPosition = "Left";
        startingLvl = "0";
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void updateAllianceColor(int color) {
        //Double check we are getting good values
        if(color == Color.RED){
            activeAllianceColor = color;
        }
        else if(color == Color.BLUE){
            activeAllianceColor = color;
        }
        else{
            Log.d("UpdateAllianceColor : ", "Invalid color submitted for alliance");
            return;
        }

        myToolBar.setBackgroundColor(activeAllianceColor);
        tabLayout.setBackgroundColor(activeAllianceColor);


        //Update Colors For All Fragments
        myTab1 = (ScoutTab1)adapter.instantiateItem(viewPager,0);
        if(myTab1 != null){
            //Update Color
            myTab1.UpdateAllianceColorForAll(color);
        }
        else{
            Log.d("Find Frag", "Could not find Frag 1");
        }
    }

    @Override
    public void updateStartingPiece(String piece) {
        startingPiece = piece;
        Log.d("Start piece", "Updating piece to " + piece);

    }

    @Override
    public void updateTab1ID(int id) {

    }

    @Override
    public void updateScoutTeam(String team) {
        teamNumber = "Team : " + team;
        myToolBarTextView.setText( "Scouting | " + sMatchNumber + " " +teamNumber);
    }

    @Override
    public void updateMatchNumber(String matchNumber) {
        sMatchNumber = "Match # : " + matchNumber;
        myToolBarTextView.setText( "Scouting | " + sMatchNumber + " " + teamNumber);
}

    @Override
    public int getAllianceColor() {
        return activeAllianceColor;
    }

    @Override
    public void updateStartingPosition(String pos) {
        startingPosition = pos;
        Log.d("Start Pos", "Updating position to " + pos);
    }

    @Override
    public void updateStartingLvl(String lvl) {
        startingLvl = lvl;
        Log.d("Start lvl", "Updating level to " + lvl);

    }
}
