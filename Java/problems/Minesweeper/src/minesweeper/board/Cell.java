package Learning.Java.problems.Minesweeper.src.minesweeper.board;

public class Cell {
    private boolean isBomb;
    private int numAdjacentBombs;
    private boolean isRevealed;

    public Cell(boolean isBomb, int numAdjacentBombs, boolean isRevealed) {
        this.isBomb = isBomb;
        this.numAdjacentBombs = numAdjacentBombs;
        this.isRevealed = isRevealed;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public int getNumAdjacentBombs() {
        return numAdjacentBombs;
    }

    public void setNumAdjacentBombs(int numAdjacentBombs) {
        this.numAdjacentBombs = numAdjacentBombs;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }
}
