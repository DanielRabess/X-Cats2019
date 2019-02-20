package com.example.curiosity2019.MatchData;

public class RocketShip{

    public RocketShip(){
        levelOneHatchMade = false;
        levelTwoHatchMade = false;
        levelThreeHatchMade = false;
        levelOneCarg0Made = false;
        levelTwoCargoMade = false;
        levelThreeCargoMade = false;
        misses = 0;
    }

    boolean levelOneHatchMade;
    boolean levelTwoHatchMade;
    boolean levelThreeHatchMade;
    boolean levelOneCarg0Made;
    boolean levelTwoCargoMade;
    boolean levelThreeCargoMade;
    int misses;


    public void setLevelOneHatchMade(boolean value) { levelOneHatchMade = value; }
    public void setLevelTwoHatchMade(boolean value) { levelTwoHatchMade = value; }
    public void setLevelThreeHatchMade(boolean value) { levelThreeHatchMade = value; }
    public void setLevelOneCargohMade(boolean value) { levelOneCarg0Made = value; }
    public void setLevelTwoCargoMade(boolean value) { levelTwoCargoMade = value; }
    public void setLevelThreeCargoMade(boolean value) { levelThreeCargoMade = value; }
    public void setMisses(int value) { misses = value; }
}