package com.izloo;

import com.izloo.Model.Bowl;
import java.util.Date;
import java.util.Random;

public class Main {

    // Create and initialize arrays to hold bowls for 2 players.
    // Bowl[0] will hold the Tray. Others will hold the normal bowl references.
    static Bowl[] bowls = new Bowl[7];
    static Bowl[] bowls2 = new Bowl[7];

    // To hold the id of the bowl that was last in any move.
    // This helps in identifying the next move for the same player if and in case.
    static int lastBowlId = -1;

    // To indicate current Verbose mode of the program
    static boolean verboseMode = true;

    // The function to set the Verbose mode of the game.
    // If verboseMode is set to false. The code will not print any output.
    // Useful with test cases.
    public static void setVerboseMode(boolean mode){
        verboseMode = mode;
    }

    // The function to support randomizing the game moves.
    public static int getRandomNumber(){
        //Get a random number of the bowl from where to start the moves
        Random rand = new Random();
        int  n = rand.nextInt(6) + 1;
        return n;
    }

    // The staring point of the game.
    public static void main(String[] args) {
        if (args.length < 1)
        {
            init();
            prettyPrintGameConfiguration(); //print out initial state
        }
        else if (args.length > 0){
            if (!init(args[0])){
                System.out.println("Bad input. Check input configuration");
                return;
            }
            prettyPrintGameConfiguration();
        }

        startFromBowlP1(6); prettyPrintGameConfiguration();
        startFromBowlP2(4); prettyPrintGameConfiguration();
        startFromBowlP1(3); prettyPrintGameConfiguration();
        startFromBowlP2(3); prettyPrintGameConfiguration();
        startFromBowlP1(5); prettyPrintGameConfiguration();
        startFromBowlP2(2); prettyPrintGameConfiguration();
        startFromBowlP1(6); prettyPrintGameConfiguration();
        startFromBowlP2(6); prettyPrintGameConfiguration();
        startFromBowlP1(1); prettyPrintGameConfiguration();
        startFromBowlP2(5); prettyPrintGameConfiguration();
        startFromBowlP1(4); prettyPrintGameConfiguration();
        startFromBowlP2(3); prettyPrintGameConfiguration();
        startFromBowlP1(3); prettyPrintGameConfiguration();
        startFromBowlP2(4); prettyPrintGameConfiguration();
        startFromBowlP1(4); prettyPrintGameConfiguration();
        startFromBowlP2(3); prettyPrintGameConfiguration();
        startFromBowlP1(6); prettyPrintGameConfiguration();
        startFromBowlP2(2); prettyPrintGameConfiguration();
        startFromBowlP1(1); prettyPrintGameConfiguration();
        startFromBowlP2(1); prettyPrintGameConfiguration();
        boolean gameFinished = startFromBowlP1(2); prettyPrintGameConfiguration();
        if (gameFinished)
        {
            if (verboseMode)
            System.out.println("Good Bye");
        }
    }

    // The function to get the total number of seeds currently in the game.
    // The display allows to indicate any erroneous states when and if the
    // number of total seeds exceeds the maximum.
    public static int getTotalSeedsInGame(){
        int sum = 0;
        for (Bowl b : bowls){
            sum += b.getSeeds();
        }
        for (Bowl b : bowls2){
            sum += b.getSeeds();
        }
        return sum;
    }

    // The function to support printing the output of the current state
    // of the game. This function is called after every move to have a
    // look at the state of the game
    public static void prettyPrintGameConfiguration(){
        if (!verboseMode) return;
        System.out.println("");System.out.println("State @ " + new Date().toString());
        for(Bowl b: bowls){
            if (b.getId() == 0)
                continue;
            System.out.print(String.format("%d<|%d|> ", b.getId(), b.getSeeds()));
        }
        System.out.println("");
        System.out.print(String.format("T1<|%d|>                           T2<|%d|>",
                bowls[0].getSeeds(),
                bowls2[0].getSeeds()) );
        System.out.println("");
        /*for(Bowl b: bowls2){if (b.getId() == 0)continue;
            System.out.print(String.format("%d<|%d|> ", b.getId(), b.getSeeds()));}*/
        for(int i = 6; i>=0; --i){
            if (bowls2[i].getId() == 0)
                continue;
            System.out.print(String.format("%d<|%d|> ", bowls2[i].getId(), bowls2[i].getSeeds()));
        }
        System.out.println("");
        System.out.println(String.format("Total Seeds: %d", getTotalSeedsInGame()));
    }

    // Get the current state of the game as a string representation
    // The format is described in the documentation.
    public static String getStringConfigurationOfCurrentGame(){
        String config = "";

        for (Bowl b: bowls){
            config += b.getSeeds();
            config += ",";
        }
        config = config.substring(0,config.length()-1);
        config += ";";
        for (Bowl b: bowls2){
            config += b.getSeeds();
            config += ",";
        }
        config = config.substring(0,config.length()-1);

        return config;
    }

    // The function to initialize the starting state of the game from
    // the provided configuration as string
    public static boolean init(String configuration){
        String[] config = configuration.split(";");
        if (config.length != 2) {
            if (!verboseMode)
                System.out.println("Configuration Parse error. Too many or too less player configs");
            return false;
        }

        String[] player1Config = config[0].split(",");
        String[] player2Config = config[1].split(",");

        int playerId = 1;
        for (int i = 6; i >= 0; --i){
            Bowl bowl = new Bowl();
            bowl.setId(i);
            if (i == 6){
                bowl.setSeeds(Integer.parseInt(player1Config[i]));
            }
            else if (i < 6 && i > 0){
                bowl.setSeeds(Integer.parseInt(player1Config[i]));
                bowl.setNextBowl(bowls[i+1]);
            }
            else{
                bowl.setSeeds(Integer.parseInt(player1Config[i]));
                bowl.setNextBowl(bowls[i+1]);
            }
            bowl.setPlayerId(playerId);
            bowls[i] = bowl;
        }

        playerId = 2;
        for (int i = 6; i >= 0; --i){
            Bowl bowl = new Bowl();
            bowl.setId(i);
            if (i == 6){
                bowl.setSeeds(Integer.parseInt(player2Config[i]));
            }
            else if (i < 6 && i > 0){
                bowl.setSeeds(Integer.parseInt(player2Config[i]));
                bowl.setNextBowl(bowls2[i+1]);
            }
            else{
                bowl.setSeeds(Integer.parseInt(player2Config[i]));
                bowl.setNextBowl(bowls2[i+1]);
            }
            bowl.setPlayerId(playerId);
            bowls2[i] = bowl;
        }

        bowls[6].setNextBowl(bowls2[0]);
        bowls2[6].setNextBowl(bowls[0]);

        setOppositeBowls();

        return true;
    }

    // The function to initialize the starting state of the game.
    public static void init() {

        // Initiating the Player 1's array which is "bowls"
        int playerId = 1;
        for (int i = 6; i >= 0; --i){
            Bowl bowl = new Bowl();
            bowl.setId(i);
            // for the last bowl set the seeds to 3
            if (i == 6){
                bowl.setSeeds(3);
            }
            // For all other bowls set the bowls to 3
            // And connect it to the next bowl
            else if (i < 6 && i > 0){
                bowl.setSeeds(3);
                bowl.setNextBowl(bowls[i+1]);
            }
            // In case of tray set the seeds to 0
            else{
                bowl.setSeeds(0);
                bowl.setNextBowl(bowls[i+1]);
            }
            bowl.setPlayerId(playerId);
            // Store current bowl in the list of bowls for the current player
            bowls[i] = bowl;
        }

        // Initiating the Player 2's array which is "bowls2"
        playerId = 2;
        for (int i = 6; i >= 0; --i){
            Bowl bowl = new Bowl();
            bowl.setId(i);
            if (i == 6){
                bowl.setSeeds(3);
            }
            else if (i < 6 && i > 0){
                bowl.setSeeds(3);
                bowl.setNextBowl(bowls2[i+1]);
            }
            else{
                bowl.setSeeds(0);
                bowl.setNextBowl(bowls2[i+1]);
            }
            bowl.setPlayerId(playerId);
            bowls2[i] = bowl;
        }

        // Connect the two array at both end points.
        bowls[6].setNextBowl(bowls2[0]);
        bowls2[6].setNextBowl(bowls[0]);

        // Initialize the references to teh opposite bowls for each player
        setOppositeBowls();
    }

    // The function to set the reference to opposite bowls belongs to the other player.
    private static void setOppositeBowls(){
        int max = 7;
        for (int i = 1; i <= 6; ++i){
            // Connect current bowl to the opposite bowl on the
            // Other side in the other player's bowls list
            bowls[i].setOppositeBowl(bowls2[max - i]);
            bowls2[i].setOppositeBowl(bowls[max - i]);
        }
    }

    // The recursive function to enable X number of increments starting
    // from a bowl b. Increments represents the number of moves needed.
    // The increments decreases by every call.
    // InitiatingPlayer denotes the current player. This is required in order to
    // check that the last move is on the same player's bowl or not.
    private static boolean incrementBowl(Bowl b, int increments, int initiatingPlayer){
        if (increments < 1 && b.getPlayerId() == initiatingPlayer) {
            lastBowlId = b.getId();
            if (b.getSeeds() == 0) {
                int seedsInOppositeBowl = b.getOppositeBowl().getSeeds();
                if (b.getPlayerId() == 1) {
                    bowls[0].setSeeds(bowls[0].getSeeds() + seedsInOppositeBowl + 1);
                    b.getOppositeBowl().setSeeds(0);
                }
                else if (b.getPlayerId() == 2) {
                    bowls2[0].setSeeds(bowls2[0].getSeeds() + seedsInOppositeBowl + 1);
                    b.getOppositeBowl().setSeeds(0);
                }
                return false; //In case of bonus, to stop further moves by the same player
            }
            return true; //To continue more moves by same player
        }

        // In case of tray no need to check any player or the number of seeds in the bowl.
        if (b.getId() == 0) b.setSeeds(b.getSeeds() + 1);

        // In case of bowl is on the other player's side, just increment. No checks needed.
        else if (b.getPlayerId() != initiatingPlayer) b.setSeeds(b.getSeeds() + 1);

        // If the bowl is on the initiating player's side and the current bowl is the
        // last bowl in the move and the seeds are more than 0. Just increment
        else if (increments > 1 || b.getSeeds() > 0) b.setSeeds(b.getSeeds() + 1);

        // In other cases: No increment needed.

        //decrement the increments variable to support the recursive call to stop at
        //some point
        increments --;
        if (increments == 0 ){
            if ( b.getPlayerId() == initiatingPlayer)
            {
                lastBowlId = b.getId();
                if (b.getSeeds() == 0) {
                    //b.setSeeds(b.getSeeds() - 1);
                    int seedsInOppositeBowl = b.getOppositeBowl().getSeeds();
                    // Apply bonus to Player 1
                    if (b.getPlayerId() == 1) {
                        bowls[0].setSeeds(bowls[0].getSeeds() + seedsInOppositeBowl + 1);
                        b.getOppositeBowl().setSeeds(0);
                    } // Apply bonus to Player 2
                    else if (b.getPlayerId() == 2) {
                        bowls2[0].setSeeds(bowls2[0].getSeeds() + seedsInOppositeBowl + 1);
                        b.getOppositeBowl().setSeeds(0);
                    }
                    return false;//In case of bonus, to stop further moves by the same player
                }
                return true;//To continue more moves by same player
            }
            else return false; //No more further moves by the same player required
        }
        //Current bowl is done. Let's move to the next bowl by following the nextBowl reference
        incrementBowl(b.getNextBowl(), increments, initiatingPlayer);  //recursively move
        return false; //No more moves by the same player are required.
    }

    // The function which enables the move for Player 1.
    // The bowlNumber parameter holds the number of the bowl from where
    // to start the moves. The number of moves depends of the seeds in the current bowl.
    // The bowlNumber can be from 0 to 6
    public static boolean startFromBowlP1(int bowlNumber){
        Bowl currentBowl = bowls[bowlNumber];
        int increments = currentBowl.getSeeds();
        if (increments < 1) return false;
        currentBowl.setSeeds(0);
        boolean lastMoveInInitiatingPlayerArea = incrementBowl(currentBowl.getNextBowl(), increments, 1);

        // If the last move was on the same side as the initiating player
        // Give him another move. And continue until this is false.
        while (lastMoveInInitiatingPlayerArea == true){
            currentBowl = bowls[lastBowlId];
            increments = currentBowl.getSeeds();
            currentBowl.setSeeds(0);
            lastMoveInInitiatingPlayerArea = incrementBowl(currentBowl.getNextBowl(), increments, 1);
        }

        // After every move, check if the current state of the game is a winning state
        if (checkWinning())
        {
            if (verboseMode) return true;
            System.out.println("");
            System.out.println("Game Finished");
            return true;
        }
        return false;
    }

    // The function which enables the move for Player 2.
    // The bowlNumber parameter holds the number of the bowl from where
    // to start the moves. The number of moves depends of the seeds in the current bowl.
    // The bowlNumber can be from 0 to 6
    public static boolean startFromBowlP2(int bowlNumber){
        Bowl currentBowl = bowls2[bowlNumber];
        int increments = currentBowl.getSeeds();
        if (increments < 1) return false;
        currentBowl.setSeeds(0);
        boolean lastMoveInInitiatingPlayerArea = incrementBowl(currentBowl.getNextBowl(), increments, 2);
        while (lastMoveInInitiatingPlayerArea == true){
            currentBowl = bowls2[lastBowlId];
            increments = currentBowl.getSeeds();
            currentBowl.setSeeds(0);
            lastMoveInInitiatingPlayerArea = incrementBowl(currentBowl.getNextBowl(), increments, 2);
        }
        if (checkWinning())
        {
            if (verboseMode)  return true;
            System.out.println("");
            System.out.println("Game Finished");
            return true;
        }
        return false;
    }

    // Check the winning state of the game.
    // Check if the P1 wins, P2 wins or the game ties.
    public static Boolean checkWinning(){
        // If any of the player's all bowls are empty. The game is finished.
        // Announce the winner of the game tie state.
        if (check1Empty() || check2Empty())
        {
            if (!verboseMode) return true;
            if (bowls[0].getSeeds() > bowls2[0].getSeeds()){
                System.out.println("");
                System.out.println("Player 1 wins");
            }
            else if (bowls[0].getSeeds() < bowls2[0].getSeeds()){
                System.out.println("");
                System.out.println("Player 2 wins");
            }
            else {
                System.out.println("");
                System.out.println("Game Tied");
            }
            return true;
        }
        return false;
    }

    // Check if all the bowls belonging to the Player 1 are empty or not?
    public static Boolean check1Empty(){
        // Assume all bowls are empty.
        Boolean allBowlsAreEmpty = true;

        for(Bowl b: bowls){
            if (b.getId() == 0) continue;
            if (b.getSeeds() == 0) continue;
            else {
                // In case a bowl is found with more than 0 seeds.
                // Break the loop and return.
                allBowlsAreEmpty = false;
                break;
            }
        }
        return allBowlsAreEmpty;
    }

    // Check if all the bowls belonging to the Player 2 are empty or not?
    public static Boolean check2Empty(){
        Boolean allBowlsAreEmpty = true;
        for (Bowl b:bowls2){
            if (b.getId() == 0) continue;
            if (b.getSeeds() == 0) continue;
            else {
                allBowlsAreEmpty = false;
                break;
            }
        }
        return allBowlsAreEmpty;
    }
}
