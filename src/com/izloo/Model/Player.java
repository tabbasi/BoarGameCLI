package com.izloo.Model;

public class Player {

    // The ID, a number to indicate the current player
    private int id;

    // The string to contain the Name of the players
    private String name;

    // The list of bowls belonging to this player
    private Bowl[] bowls;

    // The reference to the Bowl which is acting as TRAY for the player
    private Bowl tray;

    //Standard Getters and Setters

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bowl[] getBowls() {
        return bowls;
    }

    public void setBowls(Bowl[] bowls) {
        this.bowls = bowls;
    }

    public Bowl getTray() {
        return tray;
    }

    public void setTray(Bowl tray) {
        this.tray = tray;
    }


}
