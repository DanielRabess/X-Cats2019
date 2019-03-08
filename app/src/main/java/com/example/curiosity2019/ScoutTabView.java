package com.example.curiosity2019;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
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

import com.example.curiosity2019.MatchData.ScoutMatchData;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScoutTabView extends AppCompatActivity implements  ScoutTab1.OnFragmentInteractionListener,
                                                                ScoutTab2.OnFragmentInteractionListener,
                                                                ScoutTab3.OnFragmentInteractionListener,
                                                                ScoutTab4.OnFragmentInteractionListener{
    //Page
    ViewPager viewPager;
    PagerAdapter adapter;

    //MatchDada
    //This gets exported to JSON object at end
    ScoutMatchData scoutmatchdata;


    SharedPreferences sharedPreferences;

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
    ScoutTab4 myTab4;

    //Elements
    android.support.v7.widget.Toolbar myToolBar;
    TextView myToolBarTextView;
    android.support.design.widget.TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_tab_view);

        initializeDataMembers();

        scoutmatchdata = new ScoutMatchData();

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

        //exportDataToJSON();
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

    public String exportDataToJSON(){
        Gson myData = new Gson();
        String jsonString = myData.toJson(scoutmatchdata);

        Log.d("Json", jsonString);
        return jsonString;
    }

    //Interface functions
    //Tab fragments use these to communicate

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void updateConLevelThreeCargo(boolean value) {
        scoutmatchdata.getControlled().getRocketShip().setLevelThreeCargoMade(value);
    }

    @Override
    public void updateConLevelThreeHatch(boolean value) {
        scoutmatchdata.getControlled().getRocketShip().setLevelThreeHatchMade(value);
    }

    @Override
    public void updateConLevelTwoCargo(boolean value) {
        scoutmatchdata.getControlled().getRocketShip().setLevelTwoCargoMade(value);
    }

    @Override
    public void updateConLevelTwoHatch(boolean value) {
        scoutmatchdata.getControlled().getRocketShip().setLevelTwoHatchMade(value);
    }

    @Override
    public void updateConLevelOneCargo(boolean value) {
        scoutmatchdata.getControlled().getRocketShip().setLevelOneCargohMade(value);
    }

    @Override
    public void updateConLevelOneHatch(boolean value) {
        scoutmatchdata.getControlled().getRocketShip().setLevelOneHatchMade(value);
    }

    @Override
    public void updateConRsMisses(int value) {
        scoutmatchdata.getControlled().getRocketShip().setMisses(value);
    }

    @Override
    public void updateConCargoShipHatchMake(int value) {
        scoutmatchdata.getControlled().getCargoShip().setHatchesMade(value);
    }

    @Override
    public void updateConCargoShipHatchAtt(int value) {
        scoutmatchdata.getControlled().getCargoShip().setHatchesAttempted(value);
    }

    @Override
    public void updateConCargoShipCargoMake(int value) {
        scoutmatchdata.getControlled().getCargoShip().setCargoMade(value);
    }

    @Override
    public void updateConCargoShipCargoAtt(int value) {
        scoutmatchdata.getControlled().getCargoShip().setCargoAttempted(value);
    }

    @Override
    public int getSSCargoShipHatchMake() {
        return scoutmatchdata.getSandStorm().getCargoShip().getHatchesMade();
    }

    @Override
    public int getSSCargoShipHatchAttempted() {
        return scoutmatchdata.getSandStorm().getCargoShip().getHatchesAttempted();
    }

    @Override
    public int getSSCargoShipCargoMake() {
        return scoutmatchdata.getSandStorm().getCargoShip().getCargoMade();
    }

    @Override
    public int getSSCargoShipCargoAttempted() {
        return scoutmatchdata.getSandStorm().getCargoShip().getCargoAttempted();
    }

    @Override
    public void updateSSLevelThreeCargo(boolean value) {
        scoutmatchdata.getSandStorm().getRocketShip().setLevelThreeCargoMade(value);
    }

    @Override
    public void updateSSLevelThreeHatch(boolean value) {
        scoutmatchdata.getSandStorm().getRocketShip().setLevelThreeHatchMade(value);
    }

    @Override
    public void updateSSLevelTwoCargo(boolean value) {
        scoutmatchdata.getSandStorm().getRocketShip().setLevelTwoCargoMade(value);
    }

    @Override
    public void updateSSLevelTwoHatch(boolean value) {
        scoutmatchdata.getSandStorm().getRocketShip().setLevelTwoHatchMade(value);
    }

    @Override
    public void updateSSLevelOneCargo(boolean value) {
        scoutmatchdata.getSandStorm().getRocketShip().setLevelOneCargohMade(value);
    }

    @Override
    public void updateSSLevelOneHatch(boolean value) {
        scoutmatchdata.getSandStorm().getRocketShip().setLevelOneHatchMade(value);
    }

    @Override
    public void updateSSRsMisses(int value) {
        scoutmatchdata.getSandStorm().getRocketShip().setMisses(value);
    }

    @Override
    public int getConCargoShipHatchMake() {
        return scoutmatchdata.getControlled().getCargoShip().getHatchesMade();
    }

    @Override
    public int getConCargoShipHatchAttempted() {
        return scoutmatchdata.getControlled().getCargoShip().getHatchesAttempted();
    }

    @Override
    public int getConCargoShipCargoMake() {
        return scoutmatchdata.getControlled().getCargoShip().getCargoMade();
    }

    @Override
    public int getConCargoShipCargoAttempted() {
        return scoutmatchdata.getControlled().getCargoShip().getCargoAttempted();
    }

    @Override
    public void updateSSCargoShipHatchMake(int value) {
        scoutmatchdata.getSandStorm().getCargoShip().setHatchesMade(value);
    }

    @Override
    public void updateSSCargoShipHatchAtt(int value) {
        scoutmatchdata.getSandStorm().getCargoShip().setHatchesAttempted(value);
    }

    @Override
    public void updateSSCargoShipCargoMake(int value) {
        scoutmatchdata.getSandStorm().getCargoShip().setCargoMade(value);
    }

    @Override
    public void updateSSCargoShipCargoAtt(int value) {
        scoutmatchdata.getSandStorm().getCargoShip().setCargoAttempted(value);
    }

    @Override
    public void updateAllianceColor(int color) {
        //Double check we are getting good values
        if(color == Color.RED){
            activeAllianceColor = color;
            scoutmatchdata.getStartingData().setColor("red");
        }
        else if(color == Color.BLUE){
            activeAllianceColor = color;
            scoutmatchdata.getStartingData().setColor("blue");
        }
        else{
            Log.d("UpdateAllianceColor : ", "Invalid color submitted for alliance");
            return;
        }

        myToolBar.setBackgroundColor(activeAllianceColor);
        tabLayout.setBackgroundColor(activeAllianceColor);

        //Update Colors For All Fragments
        myTab1 = (ScoutTab1)adapter.instantiateItem(viewPager,0);
        myTab2 = (ScoutTab2) adapter.instantiateItem(viewPager,1);
        //myTab3 = (ScoutTab1)adapter.instantiateItem(viewPager,2);
        myTab4 = (ScoutTab4)adapter.instantiateItem(viewPager,3);



        if(myTab1 != null){
            //Update Color
            myTab1.UpdateAllianceColorForAll(color);
        }
        else{
            Log.d("Find Frag", "Could not find Frag 1");
        }
        if(myTab2 != null){
            //Update Color
            myTab2.UpdateAllianceColorForAll(color);
        }
        else{
            Log.d("Find Frag", "Could not find Frag 1");
        }
        if(myTab4 != null){
            //Update Color
            //myTab4.UpdateAllianceColorForAll(color);
        }
        else{
            Log.d("Find Frag", "Could not find Frag 1");
        }
    }

    @Override
    public void updateStartingPiece(String piece) {
        startingPiece = piece;
        scoutmatchdata.getStartingData().setPiece(piece);
        Log.d("Start piece", "Updating piece to " + piece);

    }

    @Override
    public void updateTab1ID(int id) {

    }

    @Override
    public void updateScoutTeam(String team) {
        try {
            scoutmatchdata.setScoutedTeam(Integer.parseInt(team));
        }
        catch(NumberFormatException e){
            Log.d("Scout Team", "Number format exception for Scout Team" + team);
        }
        teamNumber = "Team : " + team;
        myToolBarTextView.setText( "Scouting | " + sMatchNumber + " " +teamNumber);
    }

    @Override
    public void updateMatchNumber(String matchNumber) {
        try {
            scoutmatchdata.setMatchNumber(Integer.parseInt(matchNumber));
        }
        catch(NumberFormatException e){
            Log.d("Match Number", "Number format exception :" + matchNumber);
        }
        sMatchNumber = "Match # : " + matchNumber;
        myToolBarTextView.setText( "Scouting | " + sMatchNumber + " " + teamNumber);
}

    @Override
    public int getAllianceColor() {
        return activeAllianceColor;
    }

    @Override
    public void updateSSMovement(String movement) {
        scoutmatchdata.getSandStorm().setMovement(movement);
        Log.d("Movement Update :", movement);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void exportMatchToFile() {
        String dataToWrite = exportDataToJSON();

        //ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},43);

        sharedPreferences = this.getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);

        String thisevent = sharedPreferences.getString("selectedEvent","defevent");
        thisevent = thisevent.trim();
        thisevent = thisevent.replaceAll(" ","");

        scoutmatchdata.setEvent(thisevent);
        scoutmatchdata.setUsername(sharedPreferences.getString("username","defuser"));

        String uniqueName = scoutmatchdata.getEvent() + "." + scoutmatchdata.getScoutedTeam() + "." + scoutmatchdata.getMatchNumber() + "-";
        String matchName = "match.txt";
        File file = new File(Environment.getDataDirectory().getName(), uniqueName + matchName);
        try(FileOutputStream stream = new FileOutputStream(file, true)){
            stream.write(dataToWrite.getBytes());
        }
        catch (IOException e){
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @Override
    public void updateClimbedLevel(String level) {
        scoutmatchdata.getEndGame().setClimbing(level);
        Log.d("Climbing Level", "Updating level to " + level);
    }

    @Override
    public void updateClimbingMethod(String method) {
        scoutmatchdata.getEndGame().setClimbing(method);
    }

    @Override
    public void updateMalfunction(String mal) {
        scoutmatchdata.getEndGame().setMalfunctioned(mal);
    }

    @Override
    public void updateMalfunctionComment(String comment) {
        scoutmatchdata.getEndGame().setMalfunctionedText(comment);
    }

    @Override
    public void updatePoints(String points) {
        int res = 0;
        try{
            res = Integer.parseInt(points.trim());
        }
        catch(Exception e){

        }
        scoutmatchdata.getEndGame().setPoints(res);
    }

    @Override
    public String getResult() {
        return scoutmatchdata.getEndGame().getResult();
    }

    @Override
    public void updateResults(String res) {
        scoutmatchdata.getEndGame().setResult(res);
    }

    @Override
    public void updateStartingPosition(String pos) {
        startingPosition = pos;
        scoutmatchdata.getStartingData().setPosition(pos);
        Log.d("Start Pos", "Updating position to " + pos);
    }

    @Override
    public void updateStartingLvl(String lvl) {
        startingLvl = lvl;
        scoutmatchdata.getStartingData().setLevel(lvl);
        Log.d("Start lvl", "Updating level to " + lvl);

    }
}
