package app.belinked.model;

public enum Player {
    CROSS("X"),
    CIRCLE("O"),
    NONE("-");

    private final String id;

    Player(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
