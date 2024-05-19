package app.belinked;

import app.belinked.exception.IllegalMoveException;
import app.belinked.mcts.MonteCarlo;
import app.belinked.mcts.Node;
import app.belinked.model.*;
import app.belinked.service.Game;
import app.belinked.ui.Display;

public class Main {
    public static void endlessAnalysis() {
        GameState state = Game.start();

        Display.board(state);
        MonteCarlo mcts = new MonteCarlo();
        long now = System.currentTimeMillis();
        long end = now + 7200000;
        while(System.currentTimeMillis() < end) {
            mcts.runSearch(state, 60);
            System.out.println("------------------------");
            System.out.println("Node count:   " + mcts.getChildCount());
            Node bestNode = mcts.bestNode(state);
            System.out.println("------------------------");
            System.out.println("Move:   " + bestNode.getMove().hash());
            System.out.println("Plays / Wins:   " + bestNode.getPlays() + " / " + bestNode.getWins());
            System.out.println("Parent Plays / Wins:   " + bestNode.getParent().getPlays() + " / " + bestNode.getParent().getWins());
            System.out.println();
        }
        System.out.println(mcts.bestPlay(state).hash());
    }
    public static void main(String[] args) throws IllegalMoveException {
        if(args.length == 0) {
            System.out.println("error");
            return;
        }
        String hash = args[0];
        GameState initialState = GameState.fromHash(hash);
        if(initialState == null) {
            System.out.println("error");
            return;
        }
        if(initialState.getWinner() != null) {
            System.out.println("error");
            return;
        }

        int timeout = 5;
        if(args.length > 1) timeout = Integer.parseInt(args[1]);

        // Display.board(initialState);

        MonteCarlo mcts = new MonteCarlo();
        mcts.runSearch(initialState, timeout);
        System.out.println(mcts.bestPlay(initialState).hash());
    }
}