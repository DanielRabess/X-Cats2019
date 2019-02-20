package com.example.curiosity2019.MatchData;

public class Controlled{

    public Controlled(){
        cargoShip = new CargoShip();
        rocketShip = new RocketShip();
    }

    CargoShip cargoShip;
    RocketShip rocketShip;

    public void setCargoShip(CargoShip cs) { cargoShip = cs; }
    public void setRocketShip(RocketShip rs) { rocketShip = rs; }

    //Dont think needed, but... just in case
    public CargoShip getCargoShip() { return cargoShip; }
    public RocketShip getRocketShip() { return rocketShip; }
}