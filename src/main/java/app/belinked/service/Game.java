package app.belinked.service;

import app.belinked.exception.IllegalMoveException;
import app.belinked.model.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static GameState start() {
        return new GameState(Player.CROSS);
    }

    public static GameState nextState(GameState gameState, Move move) throws IllegalMoveException {
        // * CHECK IF MOVE IS LEGAL
        Game.legalMoves(gameState).stream()
                .filter(searchMove -> move.hash().equals(searchMove.hash()))
                .findAny()
                .orElseThrow(() -> new IllegalMoveException("Move [" + move.hash() + "] is illegal for this state (" + gameState.getNext() + ")"));

        List<Move> newHistory = new ArrayList<>(gameState.getMoveHistory());
        newHistory.add(move);

        Board newBoard = new Board(gameState.getBoard());
        newBoard.set(move.getChords(), gameState.getCurrentPlayer());

        MiniBoard clickedBoard = newBoard.getMini(move.getChords().getBoardPart());
        if(clickedBoard.getWinner() != null || clickedBoard.isFull()) {
            clickedBoard.lock();
        }

        Player newPlayer = (gameState.getCurrentPlayer() == Player.CROSS ? Player.CIRCLE : Player.CROSS);

        return new GameState(newHistory, newBoard, newPlayer);
    }

    public static List<Move> legalMoves(GameState gameState) {
        Board board = gameState.getBoard();
        int next = gameState.getNext();
        Player player = gameState.getCurrentPlayer();

        List<Move> legalMoves = new ArrayList<>();

        for(int i = 1; i < 10; i++) {
            if(next != 0 && next != i) continue;
            MiniBoard mini = board.getMini(i);
            for(int row = 0; row < 3; row++) {
                for(int col = 0; col < 3; col++) {
                    // continue if field is not empty
                    if(mini.at(row, col) != Player.NONE) continue;

                    legalMoves.add(
                            new Move(new FullChords(
                                    ChordHelper.numToChords(i),
                                    new Chords(row, col)
                            ))
                    );

                }
            }
        }

        return legalMoves;
    }

    public static boolean isLegal(GameState state, Move move) {
        return Game.legalMoves(state).stream()
                .anyMatch(searchMove -> move.hash().equals(searchMove.hash()));
    }
}
