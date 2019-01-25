/*
 * William Zwart
 * Culminating Project
 * 14/01/19
 */

package culminating;
import java.util.Random;
import javax.swing.*;
/** A strange game of ' Rock, Paper, Scissors ', without the shoot.
 * @author wizwa9381
 */
public class Culminating {
    /**
     * Method to see amount of health lost/deal damage
     * @param health Player health
     * @param booster Power up status
     * @param winner Whoever won the round
     * @return the new health values
     */
    public static int[] damage(int health[], int booster, int winner) {
        //Variables
        Random healthDrop = new Random();
        int healthLoss = healthDrop.nextInt((10)+1);
        healthLoss = healthLoss + 5;
        //Checking to see if player 1 won or not, then dealing damage and possibly healing according to the boosters in play
        if (winner == 1){
            if (booster != 2 || booster != 1) {
                health[1] = health[1] - healthLoss;
                System.out.println("Damage dealt: "+healthLoss);
            } else if (booster == 1){
                health[1] = health[1] - healthLoss*2;
                System.out.println("Damage dealt: "+healthLoss+". But it's doubled! Damage dealt: "+healthLoss*2);
            } else if (booster == 2){
                health[1] = health[1] - healthLoss;
                health[0] = health[0] + 5;
                System.out.println("Damage dealt: "+healthLoss);
                System.out.println("Health gained: 5");
            }
        }
        //Checking to see if player 2 won or not, then dealing damage and possibly healing according to the boosters in play
        if (winner == 2){
            if (booster != 2 || booster != 1){
                health[0] = health[0] - healthLoss;
                System.out.println("Damage dealt: "+healthLoss);
            } else if (booster == 1){
                health[0] = health[0] - healthLoss*2;
                System.out.println("Damage dealt: "+healthLoss+". But it's doubled! Damage dealt: "+healthLoss*2);
            } else {
                health[0] = health[0] - healthLoss;
                health[1] = health[1] + 5;
                System.out.println("Damage dealt: "+healthLoss);
                System.out.println("Health gained: 5");
            }
        }
        //Returning new health values
        return health;
    }
    /**
     * Finding the winner.
     * @param userOneChoice The first user's choice
     * @param userTwoChoice The second user's choice
     * @return Returning the winner
     */
    public static int winner (int userOneChoice, int userTwoChoice) {
        //Switch statements checking for who won. 1 is user one wins, 2 is user two wins and 3 is a tie.
        switch (userOneChoice){
            case 1:
                switch (userTwoChoice){
                    case 1:
                        System.out.println("Tie. Please choose again.");
                        return 3;
                    case 2: System.out.println("User 2 wins.");
                        return 2;
                    case 3: System.out.println("User 1 wins.");
                        return 1;
                }
            case 2:
                switch (userTwoChoice){
                    case 1:
                        System.out.println("User 1 wins.");
                        return 1;
                    case 2:
                        System.out.println("Tie. Please choose again.");
                        return 3;
                    case 3: System.out.println("User 2 wins.");
                        return 2;
                }
            case 3:
                switch (userTwoChoice){
                    case 1:
                        System.out.println("User 2 wins.");
                        return 2;
                    case 2: System.out.println("User 1 wins.");
                        return 1;
                    case 3:
                        System.out.println("Tie. Please choose again.");
                        return 3;
                }
        }
        return 3; //Should never make it here. Placed to prevent a return error.
    }
    /**
     * Random number method.
     * @param booster Power up assigner.
     * @return if someone got a power up.
     */
    public static int booster(int booster) {
        //Random variable
        Random rand = new Random();
        //Randomly determines if there will be a booster assigned. 1 or 2 means a booster, 3+ means no booster
        booster = rand.nextInt((5)+1);
        return booster;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Variables
        Random healthBoost = new Random(); //Nothing to do with healing power up.
        int [] healingBoostAmount = {healthBoost.nextInt((7)+3),healthBoost.nextInt((7)+3)}; //Nothing to do with healing power up.
        int roundCounter = 0;
        int booster = 0;
        int []health = new int [2];
        health[0] = 150;
        health[1] = 150;
        int userOneChoice = 0,userTwoChoice = 0;
        int winner = 0;
        boolean valid = true;
        //Loop the program until someone wins
        do {
            System.out.println("Player one's health: "+health[0]);
            System.out.println("Player two's health: "+health[1]);
            //Method to assign power ups
            booster = booster(booster);
            if (booster == 1){
                System.out.println("Double damage powerup assigned!");
            }
            if (booster == 2){
                System.out.println("Healing power up assigned!");
            }
            if (booster >= 3){
                System.out.println("No booster assigned. Maybe next round...");
            }
            //User choices (rock, paper or scissors)
            do{
                //Catching invalid inputs
                try{
                    userOneChoice = Integer.parseInt(JOptionPane.showInputDialog("Player 1: Enter '1' for Rock, '2' for Paper or '3' for Scissors."));
                    userTwoChoice = Integer.parseInt(JOptionPane.showInputDialog("Player 2: Enter '1' for Rock, '2' for Paper or '3' for Scissors."));
                }
                //Making letters and other characters not crash my program
                catch (NumberFormatException e){
                    System.err.println("Please enter a valid input.");
                    valid = false;
                }
                //Making sure it is within the range 1-3
                if (valid == true && userOneChoice < 1 || userOneChoice > 3){ //Helped by https://stackoverflow.com/questions/16050222/put-a-range-on-the-input-of-an-array-in-java
                    System.err.println("Invalid choice.");
                    valid = false;
                }
                if (valid == true && userTwoChoice < 1 || userTwoChoice > 3){
                    System.err.println("Invalid choice.");
                    valid = false;
                }
            }while (valid == false);
            //Method for figuring out who won the round
            winner = winner(userOneChoice,userTwoChoice);
            //Round counter goes up for each round, counting ties.
            roundCounter++;
            System.out.println("Rounds until random health boost: "+(5-roundCounter));
            //When round counter hits 5, the players heal a certain amount
            if(roundCounter == 5){
                //Assigning the healing
                health[0] += healingBoostAmount[0];
                health[1] += healingBoostAmount[1];
                //Getting new healing values
                healingBoostAmount[0] = healthBoost.nextInt((7)+3);
                healingBoostAmount[1] = healthBoost.nextInt((7)+3);
                //Reseting roundCounter
                roundCounter = 0;
                //Telling the players
                System.out.println("Random healing boost assigned!");
                System.out.println("Player 1 heals: "+healingBoostAmount[0]+" health!");
                System.out.println("Player 2 heals: "+healingBoostAmount[1]+" health!");
            }
            //Damage assignement time!
            damage(health,booster,winner); //Helped by https://stackoverflow.com/questions/32534670/pass-array-to-method-and-return-the-array
            //Giving the winners some text for winning
            if (health[0] <= 0){
                System.out.println("Player 2 wins!");
            }
            if (health[1] <= 0){
                System.out.println("Player 1 wins!");
            }
        } while (health[0] >= 0 && health[1] >= 0);
    }
    
}
