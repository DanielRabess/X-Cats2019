package com.example.curiosity2019.MatchData;

public class SandStorm{

    public SandStorm(){
        movement="camera";

        cargoShip = new CargoShip();
        rocketShip = new RocketShip();
    }

    String movement;
    CargoShip cargoShip;
    RocketShip rocketShip;

    public void setMovement(String value) { movement = value; }
    public void setCargoShip(CargoShip cs) { cargoShip = cs; }
    public void setRocketShip(RocketShip rs) { rocketShip = rs; }

    //Dont think needed, but... just in case
    public CargoShip getCargoShip() { return cargoShip; }
    public RocketShip getRocketShip() { return rocketShip; }
}
