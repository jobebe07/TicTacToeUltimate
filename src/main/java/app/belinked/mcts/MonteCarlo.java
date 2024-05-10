package app.belinked.mcts;

import app.belinked.exception.IllegalMoveException;
import app.belinked.model.GameState;
import app.belinked.model.Move;
import app.belinked.model.Player;
import app.belinked.service.Game;
import app.belinked.ui.Display;

import java.util.*;

public class MonteCarlo {
    private Map<String, Node> nodes;
    private int exploreParameter;

    public MonteCarlo(int exploreParameter) {
        this.nodes = new HashMap<>(); // state hash => node
        this.exploreParameter = exploreParameter;
    }
    public MonteCarlo() {
        this(2);
    }
    
    public void makeNode(GameState state) {
        if(!this.nodes.containsKey(state.hash())) {
            List<Move> unexpandedMoves = new ArrayList<>(Game.legalMoves(state));
            Node node = new Node(null, null, state, unexpandedMoves);
            this.nodes.put(state.hash(), node);
        }
    }

    public void runSearch(GameState state, int timeout) {
        this.makeNode(state);
        long end = System.currentTimeMillis() + timeout * 1000L;
        while (System.currentTimeMillis() < end) {
            Node node = this.select(state);
            Player winner = node.getGameState().getWinner();
            if (!node.isLeaf() && winner == null) {
                node = this.expand(node);
                winner = this.simulate(node);
            }
            this.backpropagate(node, winner);
        }
    }

    public Move bestPlay(GameState state) {
        this.makeNode(state);
        // If not all children are expanded, not enough information
        if (!this.nodes.get(state.hash()).isFullyExpanded())
            throw new Error("Not enough information!");
        Node node = this.nodes.get(state.hash());
        List<Move> allPlays = node.getMoves();
        Move bestPlay = null;
        double max = Double.NEGATIVE_INFINITY;
        for (Move play : allPlays) {
            Node childNode = node.childNode(play);
            if (childNode.getPlays() > max) {
                bestPlay = play;
                max = childNode.getPlays();
            }
        }
        return bestPlay;
    }

    public Node select(GameState state) {
        Node node = this.nodes.get(state.hash());
        while(node.isFullyExpanded() && !node.isLeaf()) {
            List<Move> plays = node.getMoves();
            if(plays.isEmpty()) throw new Error("No legal move from node");
            Move bestPlay = null;
            double bestUCB1 = Double.NEGATIVE_INFINITY;
            for (Move play : plays) {
                double childUCB1 = node.childNode(play)
                        .getScore(this.exploreParameter);
                if (childUCB1 > bestUCB1) {
                    bestPlay = play;
                    bestUCB1 = childUCB1;
                }
            }
            if(bestPlay == null) {
                Display.board(node.getGameState());
                for(Move play : plays) {
                    System.out.println(play.hash());
                    Node childNode = node.childNode(play);
                    System.out.println(node.getPlays() + ":" + childNode.getPlays());
                }
                System.out.println(node.getParent().getPlays());
            }
            node = node.childNode(bestPlay);
        }
        return node;
    }
    public Node expand(Node node) {
        List<Move> plays = node.getUnexpandedMoves();
        int index = (int) Math.floor(Math.random() * plays.size());
        Move play = plays.get(index);
        GameState childState = null;
        try {
            childState = Game.nextState(node.getGameState(), play);
        } catch (IllegalMoveException e) {
            throw new RuntimeException(e);
        }
        List<Move> childUnexpandedPlays = Game.legalMoves(childState);
        Node childNode = node.expand(play, childState, childUnexpandedPlays);
        this.nodes.put(childState.hash(), childNode);
        return childNode;
    }

    public Player simulate(Node node) {
        GameState state = node.getGameState();
        Player winner = state.getWinner();
        while (winner == null) {
            List<Move> plays = Game.legalMoves(state);
            if(plays.isEmpty()) break;

            int index = (int) Math.floor(Math.random() * plays.size());
            Move play = plays.get(index);
            try {
                state = Game.nextState(state, play);
            } catch (IllegalMoveException e) {
                throw new RuntimeException(e);
            }
            winner = state.getWinner();
        }
        return winner;
    }
    public void backpropagate(Node node, Player winner) {

        while (node != null) {
            node.setPlays(node.getPlays()+1);
            // Parent's choice
            if (node.getGameState().getCurrentPlayer() != winner && winner != null) {
                node.setWins(node.getWins()+1);
            }
            node = node.getParent();
        }
    }
}
