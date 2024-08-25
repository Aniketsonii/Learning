package minesweeper.game;


import minesweeper.board.Board;
import minesweeper.board.Cell;
import minesweeper.util.DifficultyLevel;

import java.util.Scanner;

public class GamePlay {
    private Board board;
    private boolean gameOver;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper!");
        System.out.println("Choose difficulty level:");
        System.out.println("0 - EASY");
        System.out.println("1 - MEDIUM");
        System.out.println("2 - HARD");
        System.out.print("Enter difficulty level: ");
        int difficulty = scanner.nextInt();
        DifficultyLevel difficultyLevel = DifficultyLevel.values()[difficulty];
        board = new Board(difficultyLevel);
        gameOver = false;
        while (!gameOver) {
            printBoard();
            int row, col;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Enter row (0 to " + (board.getRows() - 1) + "): ");
                row = scanner.nextInt();
                System.out.print("Enter column (0 to " + (board.getCols() - 1) + "): ");
                col = scanner.nextInt();
                if (isValidCell(row, col)) {
                    validInput = true;
                    reveal(row, col);
                    if (gameOver) {
                        printBoard();
                        System.out.println(gameWon() ? "Congratulations! You won!" : "Game Over! You hit a mine.");
                    }
                } else {
                    System.out.println("Invalid position! Please enter a valid position.");
                }
            }
        }
        scanner.close();
    }
    
    private boolean isValidCell(int row, int col) {
    return row >= 0 && row < board.getRows() && col >= 0 && col < board.getCols();
}

    

    private void reveal(int row, int col) {
        Cell cell = board.getCell(row, col);
        if (cell.isBomb()) {
            gameOver = true;
            cell.setRevealed(true);
            return;
        }
        if (cell.getNumAdjacentBombs() == 0) {
            revealEmptyCells(row, col);
        }
        cell.setRevealed(true);
        if (board.allCellsRevealed()) {
            gameOver = true;
        }
    }

    private void revealEmptyCells(int row, int col) {
    Cell cell = board.getCell(row, col);
    if (cell.isRevealed()) {
        return; // Exit if the current cell has already been revealed
    }
    cell.setRevealed(true);
    
    // Check if the current cell has adjacent bombs
    if (cell.getNumAdjacentBombs() == 0) {
        // Iterate over neighboring cells
        for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, board.getRows() - 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, board.getCols() - 1); j++) {
                // Skip the current cell (row, col)
                if (i == row && j == col) {
                    continue;
                }
                // Recursively reveal neighboring cells
                revealEmptyCells(i, j);
            }
        }
    }
}

    

    private boolean gameWon() {
        return !gameOver && board.allCellsRevealed();
    }

    private void printBoard() {
        System.out.println("*******************");
        System.out.println("      Minesweeper    ");
        System.out.println("*******************");

        // Print separator row after column numbers
        System.out.print("   "); // Additional space for alignment
        for (int col = 0; col < board.getCols(); col++) {
            System.out.print(col + " ");
        }
        System.out.println();
    
        // Print separator row after column numbers
        System.out.print("  ");
        for (int col = 0; col < board.getCols(); col++) {
            System.out.print("- ");
        }
        System.out.println();
    
        // Print row numbers and board contents
        for (int row = 0; row < board.getRows(); row++) {
            // Print row number
            System.out.printf("%2d|", row);
    
            // Print board contents
            for (int col = 0; col < board.getCols(); col++) {
                Cell cell = board.getCell(row, col);
                if (cell.isRevealed()) {
                    if (cell.isBomb()) {
                        System.out.print("@");
                    } else {
                        System.out.print(cell.getNumAdjacentBombs());
                    }
                } else {
                    System.out.print("."); // Display "." for unrevealed non-bomb cells
                }
                System.out.print("|");
            }
            System.out.println();
    
            // Print separator row between rows
            System.out.print("  ");
            for (int col = 0; col < board.getCols(); col++) {
                System.out.print("- ");
            }
            System.out.println();
        }
    }
                
}
