package app.belinked.mcts;

import app.belinked.model.GameState;
import app.belinked.model.Move;
import app.belinked.service.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Node {
    private final Move move;
    private final GameState gameState;
    private final Node parent;

    private int plays;
    private int wins;

    private Map<String, Node> children;

    private List<Move> unexpandedMoves;

    public Node(Node parent, Move move, GameState state, List<Move> unexpandedMoves) {
        this.parent = parent;
        this.move = move;
        this.gameState = state;

        this.plays = 0;
        this.wins = 0;

        this.unexpandedMoves = unexpandedMoves;
        this.children = new HashMap<>();
        for(Move m : unexpandedMoves) {
            this.children.put(m.hash(), null);
        }
    }

    public Node childNode(Move move) {
        if (!Game.isLegal(this.getGameState(), move)) throw new Error("No such play!");
        Node child = this.children.get(move.hash());
        if (child == null) {
            throw new Error("Child not expanded!");
        }
        return child;
    }
    public Node expand(Move move, GameState childState, List<Move> unexpandedMoves) {
        if (!this.children.containsKey(move.hash())) throw new Error("No such play!");
        Node childNode = new Node(this, move, childState, unexpandedMoves);
        this.children.put(move.hash(), childNode);

        Move theUnMove = this.unexpandedMoves.stream().filter(unMove -> unMove.hash().equals(move.hash())).findAny().orElse(null);
        if(theUnMove != null) {
            this.unexpandedMoves.remove(theUnMove);
        }

        return childNode;
    }
    public List<Move> getMoves() {
        List<Move> list = new ArrayList<>();
        for(Node child : children.values()) {
            list.add(child.getMove());
        }
        return Stream.concat(list.stream(), this.unexpandedMoves.stream()).toList();
    }
    public List<Move> getUnexpandedMoves() {
        return unexpandedMoves;
    }
    public boolean isFullyExpanded() {
        return this.unexpandedMoves.isEmpty();
    }
    public boolean isLeaf() {
        return this.children.isEmpty();
    }
    public double getScore(int biasParameter) {
        return ((double) this.wins / this.plays) + Math.sqrt(biasParameter * Math.log(this.parent.getPlays()) / this.plays);
    }




    public Node getParent() {
        return parent;
    }
    public Move getMove() {
        return move;
    }
    public GameState getGameState() {
        return gameState;
    }
    public int getPlays() {
        return plays;
    }
    public int getWins() {
        return wins;
    }
    public void setPlays(int plays) {
        this.plays = plays;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
}
