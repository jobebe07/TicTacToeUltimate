package app.belinked.ui;

import javax.swing.*;

public class GameWindow {
    public GameWindow() {
        JFrame frame = new JFrame("TicTacToeUltimate");
        frame.setSize(200, 200);
        frame.add(new JLabel("Tic Tac Toe Game"));
        frame.setVisible(true);
    }
}
