package Learning.Java.problems.Minesweeper.src.minesweeper.board;

import java.util.Random;

import Learning.Java.problems.Minesweeper.src.minesweeper.util.DifficultyLevel;

public class Board {
    private Cell[][] cells;
    private int rows;
    private int cols;
    private int numMines;

    public Board(DifficultyLevel difficultyLevel) {
        switch (difficultyLevel) {
            case EASY:
                rows = 9;
                cols = 9;
                numMines = rows;
                break;
            case MEDIUM:
                rows = 9;
                cols = 9;
                numMines = rows + 1 * 3;
                break;
            case HARD:
                rows = 9;
                cols = 9;
                numMines = rows + 2 * 3;
                break;
        }
        cells = new Cell[rows][cols];
        initializeBoard();
        placeMines();
        calculateNumbers();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(false, 0, false);
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (!cells[row][col].isBomb()) {
                cells[row][col].setBomb(true);
                minesPlaced++;
            }
        }
    }

    private void calculateNumbers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!cells[i][j].isBomb()) {
                    int count = countAdjacentBombs(i, j);
                    cells[i][j].setNumAdjacentBombs(count);
                }
            }
        }
    }

    private int countAdjacentBombs(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isValidCell(i, j) && cells[i][j].isBomb()) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean allCellsRevealed() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!cells[i][j].isRevealed() && !cells[i][j].isBomb()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j].isRevealed()) {
                    if (cells[i][j].isBomb()) {
                        sb.append("@ ");
                    } else {
                        sb.append(cells[i][j].getNumAdjacentBombs()).append(" ");
                    }
                } else {
                    sb.append("- ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}