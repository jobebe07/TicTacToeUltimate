package app.belinked.model;

public enum Player {
    CROSS("cross"),
    CIRCLE("circle"),
    NONE("none");

    private final String id;

    Player(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
