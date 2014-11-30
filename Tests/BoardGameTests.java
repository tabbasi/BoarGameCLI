import com.izloo.Main;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardGameTests {

    // Check if the game ends with the provided sequence of moves
    @Test
    public void gameEndCase() {
        Main main = new Main();
        main.init();
        main.startFromBowlP1(6); main.startFromBowlP2(4); main.startFromBowlP1(3);
        main.startFromBowlP2(3); main.startFromBowlP1(5); main.startFromBowlP2(2);
        main.startFromBowlP1(6); main.startFromBowlP2(6); main.startFromBowlP1(1);
        main.startFromBowlP2(5); main.startFromBowlP1(4); main.startFromBowlP2(3);
        main.startFromBowlP1(3); main.startFromBowlP2(4); main.startFromBowlP1(4);
        main.startFromBowlP2(3); main.startFromBowlP1(6); main.startFromBowlP2(2);
        main.startFromBowlP1(1); main.startFromBowlP2(1); main.startFromBowlP1(2);

        main.setVerboseMode(true); //To ommit the textual output of the game state
        assertEquals("Game should End with 1 player winning: ", true, (main.checkWinning()));

    }

    // Total number of seeds in all bowls including the trays do not exceed the maximum
    // possible number of seeds in the game
    @Test
    public void totalSeedsEqualToThirtySix(){
        Main main = new Main();
        main.init();
        for (int i = 0; i <= 100 ; ++i) {
            if (i % 2 == 0) {
                main.startFromBowlP1(main.getRandomNumber());
            } else {
                main.startFromBowlP2(main.getRandomNumber());
            }
        }
        assertEquals("Total Seeds in Game must be 36: ", 36, main.getTotalSeedsInGame());
    }

    // The state of the game set by using the configuration string is applied to the game.
    // The same can be obtained if the state is obtained in a configuration string.
    @Test
    public void configurationInputMatchesToOutput(){
        Main main = new Main();
        String testConfiguration = "5,5,4,4,2,2,0;3,3,5,2,0,1,0";
        main.init(testConfiguration);
        String currentConfiguration = main.getStringConfigurationOfCurrentGame();
        assertEquals("Game configuration matches: ", testConfiguration, currentConfiguration);
    }

    // The expected output of the game matches the real time output provided a move.
    @Test
    public void gameMoveAsExpected(){
        Main main = new Main();
        String testConfiguration = "0,3,3,3,3,3,3;0,3,3,3,3,3,3";
        main.init(testConfiguration);
        main.startFromBowlP1(6);
        String currentConfiguration = main.getStringConfigurationOfCurrentGame();
        assertEquals("Game moves as expected: ",
                "0,3,3,3,3,3,0;1,4,4,3,3,3,3",
                currentConfiguration);
    }

    // The expected output of the game matches the real time output provided a move for
    // Player 2 as well
    @Test
    public void gameMoveAsExpectedForPlayer2(){
        Main main = new Main();
        String testConfiguration = "0,3,3,3,3,3,3;0,3,3,3,3,3,3";
        main.init(testConfiguration);
        main.startFromBowlP1(6);
        main.startFromBowlP2(4);
        String currentConfiguration = main.getStringConfigurationOfCurrentGame();
        assertEquals("Game moves as expected for Player 2: ",
                "1,3,3,3,3,3,0;1,4,4,3,0,4,4",
                currentConfiguration);
    }

    // The game do not work in case of a bad configuration provided as input
    @Test
    public void gameFailsOnWrongConfiguration(){
        Main main = new Main();
        String badConfiguration = "0,3,3,3,3,3,30,3,3,3,3,3,3";
        boolean configInitStatus = main.init(badConfiguration);
        assertFalse("Configuration fails as expected on bad input", configInitStatus);
    }
}
