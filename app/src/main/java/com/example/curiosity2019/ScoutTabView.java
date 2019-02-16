package com.example.curiosity2019;

import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.support.v4.app.Fragment;

public class ScoutTabView extends AppCompatActivity implements  ScoutTab1.OnFragmentInteractionListener,
                                                                ScoutTab2.OnFragmentInteractionListener,
                                                                ScoutTab3.OnFragmentInteractionListener,
                                                                ScoutTab4.OnFragmentInteractionListener{

    //Data Memebers for Saving.....
    int activeAllianceColor;
    String startingPiece;

    ScoutTab1 myTab1;
    ScoutTab2 myTab2;

    String tab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_tab_view);

        initializeDataMembers();

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Setup"));
        tabLayout.addTab(tabLayout.newTab().setText("Sandstorm"));
        tabLayout.addTab(tabLayout.newTab().setText("Controlled"));
        tabLayout.addTab(tabLayout.newTab().setText("Endgame"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
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
        activeAllianceColor = Color.WHITE;
        startingPiece = "";
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

        //Update Colors For All Fragments
        //ScoutTab1 scout1 = (ScoutTab1)getSupportFragmentManager().findFragmentByTag(tab1);
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

    }

    @Override
    public void updateTab1ID(int id) {

    }
}
