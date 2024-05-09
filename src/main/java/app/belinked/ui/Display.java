package app.belinked.ui;

import app.belinked.model.Board;

public class Display {
    public static void board(Board board) {
        System.out.println("-------------------------------------");
        System.out.printf("| %s + %s + %s | %s + %s + %s | %s + %s + %s |%n",
                board.at(1, 1).getId(), board.at(1, 2).getId(), board.at(1, 3).getId(),
                board.at(2, 1).getId(), board.at(2, 2).getId(), board.at(2, 3).getId(),
                board.at(3, 1).getId(), board.at(3, 2).getId(), board.at(3, 3).getId());
        System.out.printf("| %s + %s + %s | %s + %s + %s | %s + %s + %s |%n",
                board.at(1, 4).getId(), board.at(1, 5).getId(), board.at(1, 6).getId(),
                board.at(2, 4).getId(), board.at(2, 5).getId(), board.at(2, 6).getId(),
                board.at(3, 4).getId(), board.at(3, 5).getId(), board.at(3, 6).getId());
        System.out.printf("| %s + %s + %s | %s + %s + %s | %s + %s + %s |%n",
                board.at(1, 7).getId(), board.at(1, 8).getId(), board.at(1, 9).getId(),
                board.at(2, 7).getId(), board.at(2, 8).getId(), board.at(2, 9).getId(),
                board.at(3, 7).getId(), board.at(3, 8).getId(), board.at(3, 9).getId());
        System.out.println("-------------------------------------");
        System.out.printf("| %s + %s + %s | %s + %s + %s | %s + %s + %s |%n",
                board.at(4, 1).getId(), board.at(4, 2).getId(), board.at(4, 3).getId(),
                board.at(5, 1).getId(), board.at(5, 2).getId(), board.at(5, 3).getId(),
                board.at(6, 1).getId(), board.at(6, 2).getId(), board.at(6, 3).getId());
        System.out.printf("| %s + %s + %s | %s + %s + %s | %s + %s + %s |%n",
                board.at(4, 4).getId(), board.at(4, 5).getId(), board.at(4, 6).getId(),
                board.at(5, 4).getId(), board.at(5, 5).getId(), board.at(5, 6).getId(),
                board.at(6, 4).getId(), board.at(6, 5).getId(), board.at(6, 6).getId());
        System.out.printf("| %s + %s + %s | %s + %s + %s | %s + %s + %s |%n",
                board.at(4, 7).getId(), board.at(4, 8).getId(), board.at(4, 9).getId(),
                board.at(5, 7).getId(), board.at(5, 8).getId(), board.at(5, 9).getId(),
                board.at(6, 7).getId(), board.at(6, 8).getId(), board.at(6, 9).getId());
        System.out.println("-------------------------------------");
        System.out.printf("| %s + %s + %s | %s + %s + %s | %s + %s + %s |%n",
                board.at(7, 1).getId(), board.at(7, 2).getId(), board.at(7, 3).getId(),
                board.at(8, 1).getId(), board.at(8, 2).getId(), board.at(8, 3).getId(),
                board.at(9, 1).getId(), board.at(9, 2).getId(), board.at(9, 3).getId());
        System.out.printf("| %s + %s + %s | %s + %s + %s | %s + %s + %s |%n",
                board.at(7, 4).getId(), board.at(7, 5).getId(), board.at(7, 6).getId(),
                board.at(8, 4).getId(), board.at(8, 5).getId(), board.at(8, 6).getId(),
                board.at(9, 4).getId(), board.at(9, 5).getId(), board.at(9, 6).getId());
        System.out.printf("| %s + %s + %s | %s + %s + %s | %s + %s + %s |%n",
                board.at(7, 7).getId(), board.at(7, 8).getId(), board.at(7, 9).getId(),
                board.at(8, 7).getId(), board.at(8, 8).getId(), board.at(8, 9).getId(),
                board.at(9, 7).getId(), board.at(9, 8).getId(), board.at(9, 9).getId());
        System.out.println("-------------------------------------");
    }
}
