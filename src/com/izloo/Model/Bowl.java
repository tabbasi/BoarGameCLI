package com.izloo.Model;

public class Bowl {

    // The ID, a number to indicate the bowl. 0 is the tray. 1 - 6 are normal bowls
    private int id;

    // Number of seeds in the bowl at any given state
    private int seeds;

    // Reference to the opposite bowl. This will be used in case of the Bonus move.
    private Bowl oppositeBowl;

    // Reference to the next bowl in sequence. The last bowl for Player X connects to the first
    // bowl of Player Y
    private Bowl nextBowl;

    // The ID of player to identify the player this bowl belongs to
    private int playerId;

    //Standard Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public Bowl getOppositeBowl() {
        return oppositeBowl;
    }

    public void setOppositeBowl(Bowl oppositeBowl) {
        this.oppositeBowl = oppositeBowl;
    }

    public Bowl getNextBowl() {
        return nextBowl;
    }

    public void setNextBowl(Bowl nextBowl) {
        this.nextBowl = nextBowl;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }




}
