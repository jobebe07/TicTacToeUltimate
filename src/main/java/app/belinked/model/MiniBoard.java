package app.belinked.model;

import app.belinked.service.ChordHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MiniBoard implements Winnable{
    private Player[][] fields;
    private boolean isLocked;

    // COPY CONSTRUCTOR
    public MiniBoard(MiniBoard board) {
        this();
        this.isLocked = board.isLocked();
        for(int i = 1; i < 10; i++) {
            this.set(i, board.at(i));
        }
    }

    public MiniBoard() {
        this.fields = new Player[3][3];
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                this.fields[row][col] = Player.NONE;
            }
        }

        this.isLocked = false;
    }

    public Player getWinner() {
        // BEGIN HORIZONTAL CHECKS
        if(this.at(1) == this.at(2) && this.at(1) == this.at(3) && this.at(1) != Player.NONE) {
            return this.at(1);
        } else if(this.at(4) == this.at(5) && this.at(4) == this.at(6) && this.at(4) != Player.NONE) {
            return this.at(4);
        } else if(this.at(7) == this.at(8) && this.at(7) == this.at(9) && this.at(7) != Player.NONE) {
            return this.at(7);
        } // BEGIN VERTICAL CHECKS
        else if(this.at(1) == this.at(4) && this.at(1) == this.at(7) && this.at(1) != Player.NONE) {
            return this.at(1);
        } else if(this.at(2) == this.at(5) && this.at(1) == this.at(8) && this.at(2) != Player.NONE) {
            return this.at(2);
        } else if(this.at(3) == this.at(6) && this.at(1) == this.at(9) && this.at(3) != Player.NONE) {
            return this.at(3);
        } // BEGIN DIAGONAL CHECKS
        else if(this.at(1) == this.at(5) && this.at(1) == this.at(9) && this.at(1) != Player.NONE) {
            return this.at(1);
        } else if(this.at(3) == this.at(5) && this.at(3) == this.at(7) && this.at(3) != Player.NONE) {
            return this.at(3);
        }
        // TODO algo impl
        return Player.NONE;
    }

    public void set(Chords chords, Player player) {
        this.fields[chords.getRow()][chords.getCol()] = player;
    }
    public void set(int num, Player player) {
        Chords chords = ChordHelper.numToChords(num);
        this.fields[chords.getRow()][chords.getCol()] = player;
    }

    public void lock() {
        this.isLocked = true;
    }

    public void unlock() {
        this.isLocked = false;
    }

    public Player at(int row, int col) {
        return this.fields[row][col];
    }

    public Player at(Chords chords) {
        return this.fields[chords.getRow()][chords.getCol()];
    }

    public Player at(int num) {
        Chords chords = ChordHelper.numToChords(num);
        return this.fields[chords.getRow()][chords.getCol()];
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public boolean isFull() {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(this.fields[row][col] == Player.NONE) return false;
            }
        }
        return true;
    }
}
