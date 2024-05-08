package app.belinked.model;

public class FullChords {
    private final int row;
    private final int column;
    private final int fieldRow;
    private final int fieldColumn;

    public FullChords(int row, int column, int fieldRow, int fieldColumn) {
        this.row = row;
        this.column = column;
        this.fieldRow = fieldRow;
        this.fieldColumn = fieldColumn;
    }

    public FullChords(Chords boardPart, Chords fieldPart) {
        this.row = boardPart.getRow();
        this.column = boardPart.getCol();
        this.fieldRow = fieldPart.getRow();
        this.fieldColumn = fieldPart.getCol();
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public int getFieldRow() {
        return fieldRow;
    }
    public int getFieldColumn() {
        return fieldColumn;
    }

    public Chords getBoardPart() {
        return new Chords(row, column);
    }

    public Chords getFieldPart() {
        return new Chords(fieldRow, fieldColumn);
    }
}
