package lthopoly.spaces;

import lthopoly.GameBoard;
import lthopoly.cards.MoveCard;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Tank on 4/17/2016.
 */
public class MoveSpace extends BoardSpace {
    MoveCard[] cards;
    MoveCard card;

    /**
     * Creates a new MoveSpace. When landing on this space a card from the card array will be drawn
     */
    public MoveSpace(MoveCard[] cards) {
        this.cards = cards;
    }

    /**
     * Returns an array of possible game actions permitted by this space
     */
    @Override
    public int[] getPossibleActions(GameBoard board) {
        int[] houseActions = {board.DRAW_CARD, board.DEFAULT_VIEW, board.SHOW_BOARD, board.EXIT_GAME};
        return houseActions;
    }

    /**
     * Performs a game action available while on this space
     */
    @Override
    public void action(GameBoard board, int action) {
        if (action == board.DRAW_CARD){
            card = cards[ThreadLocalRandom.current().nextInt(0, cards.length)];
            board.moveCurrentPlayer(card.getPositionAdjustment());
            System.out.println(card.getDescription());

          }else{
            board.doAction(action);
        }
    }

    /**
     * Returns a string representation of this MoveSpace
     */
    @Override
    public String toString() {
        return "Gå-Korthögen har " + cards.length + " kort";
    }
}
