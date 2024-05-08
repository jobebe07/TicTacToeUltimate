package app.belinked;

import app.belinked.exception.IllegalMoveException;
import app.belinked.model.*;
import app.belinked.service.ChordHelper;
import app.belinked.service.Game;

public class Main {
    public static void main(String[] args) throws IllegalMoveException {
        GameState state = Game.start();

        GameState newState = Game.nextState(state, new Move(1, 5));
            newState = Game.nextState(newState, new Move(5, 1));
        newState = Game.nextState(newState, new Move(1, 2));
            newState = Game.nextState(newState, new Move(2, 1));
        newState = Game.nextState(newState, new Move(1, 8));
        System.out.println(Game.legalMoves(newState).size());

        System.out.println(newState.getBoard().at(1, 2));
        System.out.println(newState.getBoard().at(1, 5));
        System.out.println(newState.getBoard().at(1, 8));

        System.out.println(newState.getWinner());
    }
}