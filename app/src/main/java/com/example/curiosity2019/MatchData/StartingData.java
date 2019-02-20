package com.example.curiosity2019.MatchData;

public class StartingData{

    public StartingData(){
        position = "left";
        color = "red";
        level = "one";
        piece = "";
    }


    String position;
    String color;
    String level;
    String piece;

    public void setPosition(String value) { position = value; }
    public void setColor(String value) { color = value; }
    public void setLevel(String value) { level = value; }
    public void setPiece(String value) { piece = value; }
}