package com.example.curiosity2019.MatchData;

public class CargoShip{

    public CargoShip(){
        cargoMade = 0;
        cargoAttempted = 0;
        hatchesMade = 0;
        hatchesAttempted = 0;
    }

    int hatchesMade;
    int hatchesAttempted;
    int cargoMade;
    int cargoAttempted;

    public void setHatchesMade(int value) { hatchesMade = value; }
    public void setHatchesAttempted(int value) { hatchesAttempted = value; }
    public void setCargoMade(int value) { cargoMade = value; }
    public void setCargoAttempted(int value) { cargoAttempted = value; }
}