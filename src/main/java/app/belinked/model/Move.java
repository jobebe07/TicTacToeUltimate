package app.belinked.model;

import app.belinked.service.ChordHelper;

public class Move {
    private final int row;
    private final int column;
    private final int fieldRow;
    private final int fieldColumn;

    private final FullChords chords;

    public Move(int row, int column, int fieldRow, int fieldColumn) {
        this.row = row;
        this.column = column;
        this.fieldRow = fieldRow;
        this.fieldColumn = fieldColumn;

        this.chords = new FullChords(row, column, fieldRow, fieldColumn);
    }

    public Move(int boardPart, int fieldPart) {
        this.chords = ChordHelper.numToFullChords(boardPart, fieldPart);
        this.row = this.chords.getRow();
        this.column = this.chords.getColumn();
        this.fieldRow = this.chords.getFieldRow();
        this.fieldColumn = this.chords.getFieldColumn();
    }

    public Move(Chords boardPart, Chords fieldPart) {
        this.chords = new FullChords(boardPart, fieldPart);
        this.row = this.chords.getRow();
        this.column = this.chords.getColumn();
        this.fieldRow = this.chords.getFieldRow();
        this.fieldColumn = this.chords.getFieldColumn();
    }

    public Move(FullChords chords) {
        this.chords = chords;

        this.row = chords.getRow();
        this.column = chords.getColumn();
        this.fieldRow = chords.getFieldRow();
        this.fieldColumn = chords.getFieldColumn();
    }

    public FullChords getChords() {
        return this.chords;
    }

    public String hash() {
        return row + "," + column + "," + fieldRow + "," + fieldColumn;
    }
}
