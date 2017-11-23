package lthopoly;

import lthopoly.spaces.BoardSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by Tank on 4/17/2016.
 */
public class GameBoard {

    /**
     * Menu constants
     */
    public static final int THROW_DICE = 0;
    public static final int DRAW_CARD = 1;
    public static final int BUY_PROPERTY = 2;
    public static final int PAY_RENT = 3;
    public static final int END_TURN = 4;
    public static final int DEFAULT_VIEW = 5;
    public static final int SHOW_BOARD = 6;
    public static final int EXIT_GAME = 7;


    /**
     * Attributes
     */
    private ArrayList<BoardSpace> spaces;
    private List<Player> players;
    public  Player currentPlayer;
    private boolean currentPlayerHasThrown;

    private int throwDice() {
        //return java.util.Random.nextInt(6) + 1;
        return (int)(Math.random() * 5 + 1);

        // 1 - 6
    }


    /**
     * Creates a new board ready to play
     */
    public GameBoard(List<Player> players) {
        this.players = players;
    }

    /**
     * Returns an int array containing possible game actions.
     * A game action can be any of the static constants in GameBoard
     */
    public int[] getPossibleActions() {
        if (!currentPlayerHasThrown){
            int[] possibleActions = {THROW_DICE, DEFAULT_VIEW, SHOW_BOARD, EXIT_GAME};
            return possibleActions;
        } else {
            int[] possibleActions = getCurrentBoardSpace().getPossibleActions(this);
            return possibleActions;
        }
    }

    /**
     * Checks whether the game is over or not
     */
    public boolean isGameOver() {
        //kolla om någon av this.players har 0 sek
        if (currentPlayer.getMoney() == 0) return true;
        else {
            return false;
        }
    }

    /**
     * Returns the player with the most money
     */
    public Player getRichestPlayer() {
        Player player = new Player("", 0, 0);

        for (int i = 0; i < players.size(); i++){
          if (player.getMoney() < this.players.get(i).getMoney()){
            player = players.get(i);
          }
        }

        return player;
    }
    
    /**
     * Returns a list of all players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Returns a list of all BoardSpaces
     */
    public List<BoardSpace> getBoardSpaces() {
        return this.spaces;
    }

    /**
     * Performs an action for the current player
     */
    public void doAction(int action) {
    }

    /**
     * Returns the currently active player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    /**
     * Returns the BoardSpace corresponding to the position of the current player.
     */
  public BoardSpace getCurrentBoardSpace() {
        return spaces.get(currentPlayer.getPosition());
    }

    /**
     * Moves the currently active player adjustments spaces forward.
     * Negative adjustment moves the player backwards.
     */
    public void moveCurrentPlayer(int adjustment) {
        currentPlayer.setPosition(currentPlayer.getPosition() + adjustment);
    }

    /**
     * Returns an ArrayList<Integer> where each element contains
     * the total sum of all players' money at the end of a round.
     * E.g. list.get(0) is the total amount of money in the game after the first round.
     */
     public ArrayList<Integer> getStatistics() {
        ArrayList<Integer> sum = new ArrayList<Integer>();
    	int summ = 0;
        
         for(int i = 0; i < players.size(); i++) {
    		 summ = summ + players.get(i).getMoney();
    		 sum.add(i, summ);
    		 
    	}
    
    return sum;
    }

    /**
     * Returns a string representation of this GameBoard
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Rutans Namn [Ägare] (Pris/Hyra) (Spelare, Pengar)*\n");
        out.append("--------------------------------------------------\n");
        for (int i = 0; i < spaces.size(); i++) {

            out.append(spaces.get(i).toString() + " ");

            for (int j = 0; j < players.size(); j++)
                if (players.get(j).getPosition() == i)
                    out.append("(" + players.get(j).toString() + "," + players.get(j).getMoney() + ")");//add name to row

            out.append("\n");
        }
        return out.toString();
    }
}

