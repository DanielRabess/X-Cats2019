package com.example.curiosity2019.MatchData;

public class CargoShip{

    public CargoShip(){
        cargoCount = 0;
        hatchesCount = 0;
    }

    int hatchesCount;

    int cargoCount;


    public void setHatchesMade(int value) { hatchesCount = value; }

    public void setCargoMade(int value) { cargoCount = value; }


    public int getHatchesMade(){return hatchesCount;}

    public int getCargoMade(){return cargoCount;}

}