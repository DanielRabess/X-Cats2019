package com.example.curiosity2019.MatchData;

public class ScoutMatchData {

    public ScoutMatchData(){
        event = "D";
        username = "";
        scoutedTeam = 100;
        matchNumber = 5000;

        startingData = new StartingData();
        sandStorm = new SandStorm();
        controlled = new Controlled();
        endGame = new EndGame();
    }


    String event;
    String username;
    int scoutedTeam;
    int matchNumber;

    StartingData startingData;
    SandStorm sandStorm;
    Controlled controlled;
    EndGame endGame;

    public void setEvent(String value) { event = value; }
    public void setUsername(String value) { username = value; }
    public void setScoutedTeam(int value) { scoutedTeam = value; }
    public void setMatchNumber(int value) { matchNumber = value; }

    public void setStartingData(StartingData sd) { startingData = sd; }
    public void setSandStorm(SandStorm ss) { sandStorm = ss; }
    public void setControlled(Controlled c) { controlled = c; }
    public void setEndgame(EndGame ed) { endGame = ed; }

    public StartingData getStartingData() { return startingData;}
    public SandStorm getSandStorm() { return sandStorm;}
    public Controlled getControlled() { return controlled;}
    public EndGame getEndGame() {return  endGame;}

    public String getEvent(){return event;}
    public int getScoutedTeam(){return scoutedTeam;}
    public String getUsername(){return username;}
    public int getMatchNumber(){return matchNumber;}
}
