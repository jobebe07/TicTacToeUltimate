package app.belinked.model;

import app.belinked.service.ChordHelper;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final List<Move> moveHistory;
    private final Player currentPlayer;
    private final Board board;

    public GameState(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.board = new Board();
        this.moveHistory = new ArrayList<>();
    }

    public GameState(List<Move> moveHistory, Board board, Player currentPlayer) {
        this.moveHistory = moveHistory;
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    public List<Move> getMoveHistory() {
        return this.moveHistory;
    }
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    public Board getBoard() {
        return this.board;
    }
    public String hash() {
        return this.moveHistory.toString();
    }

    public int getNext() {
        if(this.moveHistory.isEmpty()) return 0;

        Move lastMove = this.moveHistory.get(this.moveHistory.size() - 1);
        int num = ChordHelper.chordsToNum(lastMove.getChords().getFieldPart());

        if(this.getBoard().getMini(num).isLocked()) return 0;
        return num;
    }

    public Player getWinner() {
        return this.getBoard().getWinner();
    }
}
