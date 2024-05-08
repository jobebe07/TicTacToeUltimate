package app.belinked.service;

import app.belinked.model.Chords;
import app.belinked.model.FullChords;

public class ChordHelper {
    public static Chords numToChords(int num) {
        num--;
        int col = (num % 3);
        int row = (int) Math.floor(num / 3);
        return new Chords(row, col);
    }

    public static FullChords numToFullChords(int boardPart, int fieldPart) {
        return new FullChords(ChordHelper.numToChords(boardPart), ChordHelper.numToChords(fieldPart));
    }

    public static int chordsToNum(Chords chords) {
        return ((chords.getRow()+1)*3) - (3-(chords.getCol()+1));
    }
}
