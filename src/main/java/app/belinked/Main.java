package app.belinked;

import app.belinked.exception.IllegalMoveException;
import app.belinked.mcts.MonteCarlo;
import app.belinked.model.*;
import app.belinked.service.Game;
import app.belinked.ui.Display;

public class Main {
    public static void main(String[] args) throws IllegalMoveException {
        MonteCarlo mcts = new MonteCarlo();

        GameState state = Game.start();
        Player winner = state.getWinner();

        while (winner == Player.NONE) {

            mcts.runSearch(state, 1);

            Move play = mcts.bestPlay(state);

            state = Game.nextState(state, play);
            winner = state.getWinner();
            Display.board(state.getBoard());
        }

        System.out.println(winner);

    }
}