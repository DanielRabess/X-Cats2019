package com.example.curiosity2019.MatchData;

public class RocketShip{

    public RocketShip(){
        levelOneHatchMade = false;
        levelTwoHatchMade = false;
        levelThreeHatchMade = false;
        levelOneCargoMade = false;
        levelTwoCargoMade = false;
        levelThreeCargoMade = false;
        misses = 0;
    }

    boolean levelOneHatchMade;
    boolean levelTwoHatchMade;
    boolean levelThreeHatchMade;
    boolean levelOneCargoMade;
    boolean levelTwoCargoMade;
    boolean levelThreeCargoMade;
    int misses;

    int levelOneHatchCount;
    int levelOneCargoCount;
    int levelTwoHatchCount;
    int levelTwoCargoCount;
    int levelThreeHatchCount;
    int levelThreeCargoCount;

    // new setters
    public void setLevelOneHatchCount(int value) { levelOneHatchCount = value; }
    public void setLevelOneCargoCount(int value) { levelOneCargoCount = value; }
    public void setLevelTwoHatchCount(int value) { levelTwoHatchCount = value; }
    public void setLevelTwoCargoCount(int value) { levelTwoCargoCount = value; }
    public void setLevelThreeHatchCount(int value) { levelThreeHatchCount = value; }
    public void setLevelThreeCargoCount(int value) { levelThreeCargoCount = value; }

    // new getters
    public int getLevelOneHatchCount() { return levelOneHatchCount; }
    public int getLevelOneCargoCount() { return levelOneCargoCount; }
    public int getLevelTwoHatchCount() { return levelTwoHatchCount; }
    public int getLevelTwoCargoCount() { return levelTwoCargoCount; }
    public int getLevelThreeHatchCount() { return levelThreeHatchCount; }
    public int getLevelThreeCargoCount() { return levelThreeCargoCount; }

    // OLD - we have switched to integers
    public void setLevelOneHatchMade(boolean value) { levelOneHatchMade = value; }
    public void setLevelTwoHatchMade(boolean value) { levelTwoHatchMade = value; }
    public void setLevelThreeHatchMade(boolean value) { levelThreeHatchMade = value; }
    public void setLevelOneCargoMade(boolean value) { levelOneCargoMade = value; }
    public void setLevelTwoCargoMade(boolean value) { levelTwoCargoMade = value; }
    public void setLevelThreeCargoMade(boolean value) { levelThreeCargoMade = value; }
    public void setMisses(int value) { misses = value; }



}