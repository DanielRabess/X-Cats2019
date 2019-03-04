package com.example.curiosity2019.MatchData;

public class EndGame{

    public EndGame(){
        level="one";
        climbing="unassisted";
        malfunctioned="";
        malfunctionedText="" ;
        points=0;
        atbs=0;
        result="lose";
    }

    String level;
    String climbing;
    String malfunctioned;
    String malfunctionedText;
    String result;
    int points;
    int atbs;

    public void setLevel(String value) { level = value; }
    public void setClimbing(String value) { climbing = value; }
    public void setMalfunctioned(String value) { malfunctioned = value; }
    public void setMalfunctionedText(String value) { malfunctionedText = value; }
    public void setPoints(int value) { points = value; }
    public void setATBS(int value) { atbs = value; }
    public void setResult(String value) { result = value; }

    public String getResult() { return result;}
}
