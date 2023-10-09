package com.example.myapplication;
//Author @Eric Su

public class LightsOutGame {

    private int size = 5;
    private boolean[][] board = new boolean[size][size];

    public LightsOutGame() {
        randomize();
    }

    public void randomize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Math.random() > 0.5;
            }
        }
    }

    public void toggle(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            board[x][y] = !board[x][y];

            // Toggle left neighbor
            if (y - 1 >= 0) board[x][y - 1] = !board[x][y - 1];

            // Toggle right neighbor
            if (y + 1 < size) board[x][y + 1] = !board[x][y + 1];

            // Toggle upper neighbor
            if (x - 1 >= 0) board[x - 1][y] = !board[x - 1][y];

            // Toggle lower neighbor
            if (x + 1 < size) board[x + 1][y] = !board[x + 1][y];
        }
    }


    public boolean getValue(int x, int y) {
        return board[x][y];
    }

    public boolean isComplete() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
