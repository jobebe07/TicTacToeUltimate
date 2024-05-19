package app.belinked.model;

import app.belinked.service.ChordHelper;
import app.belinked.service.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameState {
    private final List<Move> moveHistory;
    private final Player currentPlayer;
    private final Board board;
    private final UUID uuid;
    private int next;

    public GameState(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.board = new Board();
        this.moveHistory = new ArrayList<>();
        this.uuid = UUID.randomUUID();
        this.next = 0;
    }

    public GameState(List<Move> moveHistory, Board board, Player currentPlayer, int next) {
        this.moveHistory = moveHistory;
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.uuid = UUID.randomUUID();
        this.next = next;
    }

    public static GameState fromHash(String hash) {
        if(!hash.contains("/") || !hash.contains("-") || !hash.contains("#")) return null; // ! invalid
        if(hash.split("-").length != 2) return null;  // ! invalid

        String boardPart = hash.split("-")[0];
        String playerPart = hash.split("-")[1];

        // * parse the player part
        if(playerPart.split("#").length != 2) return null;  // ! invalid

        Player currentPlayer = Player.valueOfID(playerPart.split("#")[0]);
        int next = Integer.parseInt(playerPart.split("#")[1]);
        if(currentPlayer == null || next < 0 || next > 9) return null;
        GameState state = new GameState(new ArrayList<>(), new Board(), currentPlayer, next);

        // * parse the field part
        String[] boardArray = boardPart.split("/");
        if(boardArray.length != 9) return null; // ! invalid
        for(int i = 0; i < boardArray.length; i++) {
            String current = boardArray[i];
            int numberInMini = 1;

            if(current.isEmpty()) return null; // ! invalid, something like 9/9//9/... happens

            // * iterate the current mini part (... /.../.../***/...)
            for(char c : current.toCharArray()) {
                if(numberInMini > 9) return null; // ! invalid

                // * check if c is a digit or a character
                int x = (Character.isDigit(c) ? Integer.parseInt(c+"") : -1);
                if(x == 0) return null;
                // * c is NOT a number (=> X or O)
                if(x <= -1) {
                    Player p = Player.valueOfID(c+"");
                    state.getBoard().set(i+1, numberInMini, p);
                    numberInMini++;
                }
                // * c IS a number => number of empty places
                else {
                    for(int j = 0; j < x; j++) {
                        if(numberInMini > 9) return null; // ! invalid

                        state.getBoard().set(i+1, numberInMini, Player.NONE);
                        numberInMini++;
                    }
                }
            }
        }
        // * lock full or won boards
        for(int i = 1; i < 10; i++) {
            MiniBoard mini = state.getBoard().getMini(i);
            if(mini.getWinner() != null) mini.lock();
        }
        return state;
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
    public String getUUID() {
        return this.uuid.toString();
    }

    public String generateHash() {
        String hash = "";
        for(int i = 1; i <= 9; i++) {
            int empty = 0;
            MiniBoard mini = this.getBoard().getMini(i);
            for(int j = 1; j <= 9; j++) {
                Player p = mini.at(j);
                if(p == Player.NONE) {
                    empty++;
                } else {
                    if(empty != 0) hash = hash.concat(empty + "");
                    empty = 0;
                    hash = hash.concat(p.getId());
                }
            }
            if(empty != 0) hash = hash.concat(empty + "");
            if(i != 9) hash = hash.concat("/");
        }
        return hash + "-" + this.currentPlayer.getId() + "#" + this.getNext();
    }

    public int getNext() {
        return this.next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public Player getWinner() {
        return this.getBoard().getWinner();
    }
}
