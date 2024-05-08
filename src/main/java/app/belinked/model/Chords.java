package app.belinked.model;

public class Chords {
    private final int row;
    private final int col;

    public Chords(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
