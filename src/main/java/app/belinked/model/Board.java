package app.belinked.model;

import app.belinked.service.ChordHelper;

import java.util.Arrays;

public class Board implements Winnable {
    private final MiniBoard[][] miniBoards;

    // COPY CONSTRUCTOR
    public Board(Board b) {
        this.miniBoards = new MiniBoard[3][3];
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                this.miniBoards[row][col] = new MiniBoard(b.getMini(row, col));
            }
        }
    }

    public Board() {
        this.miniBoards = new MiniBoard[3][3];
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                this.miniBoards[row][col] = new MiniBoard();
            }
        }
    }

    public void set(FullChords chords, Player player) {
        this.miniBoards[chords.getRow()][chords.getColumn()].set(chords.getFieldPart(), player);
    }

    public void set(int field, int num, Player player) {
        FullChords chords = new FullChords(ChordHelper.numToChords(field), ChordHelper.numToChords(num));
        this.miniBoards[chords.getRow()][chords.getColumn()].set(chords.getFieldPart(), player);
    }

    public MiniBoard getMini(Chords chords) {
        return this.miniBoards[chords.getRow()][chords.getCol()];
    }
    public MiniBoard getMini(int num) {
        Chords chords = ChordHelper.numToChords(num);
        return this.miniBoards[chords.getRow()][chords.getCol()];
    }
    public MiniBoard getMini(int row, int col) {
        return this.miniBoards[row][col];
    }

    public Player at(FullChords chords) {
        return this.miniBoards[chords.getRow()][chords.getColumn()].at(chords.getFieldPart());
    }

    public Player at(int field, int num) {
        FullChords chords = new FullChords(ChordHelper.numToChords(field), ChordHelper.numToChords(num));

        return this.miniBoards[chords.getRow()][chords.getColumn()].at(chords.getFieldPart());
    }

    public Player at(int row, int col, int fieldRow, int fieldCol) {
        return this.miniBoards[row][col].at(fieldRow, fieldCol);
    }

    public Player getWinner() {
        MiniBoard mini = new MiniBoard();
        for(int i = 1; i < 10; i++) {
            mini.set(i, this.getMini(i).getWinner());
        }
        return mini.getWinner();
    }
}
