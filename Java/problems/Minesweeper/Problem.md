# pnwMinesweeperJava

## Overview
**pnwMinesweeperJava** is a Java implementation of the classic Minesweeper game. The goal of the game is to clear a minefield without detonating any mines. The player selects cells on a grid, and the game reveals either a number indicating the count of adjacent mines or a mine itself, which ends the game. The game features different difficulty levels and uses object-oriented programming principles.

## Game Details
- **Grid Dimensions**: The game grid is set to a 9x9 dimension, resulting in 81 squares.
- **Difficulty Levels**:
  - **EASY (0)**: 9 mines are placed on the grid.
  - **MEDIUM (1)**: 12 mines are placed on the grid.
  - **HARD (2)**: 15 mines are placed on the grid.

## How to Play
1. **Difficulty Selection**: The player selects a difficulty level by entering `0`, `1`, or `2`.
2. **Gameplay**:
   - The player selects a cell on the grid to reveal its contents.
   - If the selected cell contains a mine, the game ends.
   - If the cell is adjacent to mines, a number is displayed indicating the count of adjacent mines.
   - If the cell has no adjacent mines, it is marked with a space, and adjacent cells are automatically revealed.
3. **Winning**: The player wins if all non-mine cells are revealed before a mine is selected.

## Features
- **Random Mine Placement**: Mines are randomly placed on the grid at the start of each game.
- **Recursive Reveal**: Cells with no adjacent mines trigger the reveal of surrounding cells.
- **Input Validation**: The game ensures valid input for both difficulty level and cell selection.
- **Object-Oriented Design**: The game uses multiple classes, packages, and inheritance to maintain clean and modular code.

## Project Structure
The project is organized as follows:
1. **Main Driver**: The entry point of the application, handling game initialization and user interaction.
2. **Non-Inheritance Classes**: Utility classes and components that do not rely on inheritance.
3. **Inheritance Classes**: Core game classes that utilize inheritance for shared functionality.

## Development Goals
- Implement a clear and functional Java application using multiple classes.
- Organize the project using packages for better code management.
- Apply solid OOP techniques, including inheritance, where appropriate.
- Utilize Java's rich API, such as `Vector` or `Stack`, if necessary.
- Isolate all input/output operations within a dedicated gameplay object, making it reusable with the `Minesweeper` class.

## How to Run
1. Compile all Java files in the project directory.
2. Run the main driver class to start the game.
3. Follow on-screen instructions to play Minesweeper.

## Example Gameplay

```plaintext
Enter difficulty level
(0 = EASY, 1 = MEDIUM, 2 = HARD): 0

  0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8
-------------------------------------
  . | . | . | . | . | . | . | . | @ | 0
-------------------------------------
  . | . | . | . | . | . | . | . | . | 1
-------------------------------------
  . | . | . | . | . | . | . | . | . | 2
-------------------------------------
  . | . | . | . | . | . | . | . | . | 3
-------------------------------------
  . | . | @ | . | @ | . | . | . | . | 4
-------------------------------------
  . | . | @ | . | . | @ | . | . | . | 5
-------------------------------------
  @ | . | 2 | . | 4 | . | . | . | . | 6
-------------------------------------
  @ | . | 1 | @ | 1 | @ | . | . | . | 7
-------------------------------------
  . | . | 1 | @ | 1 | @ | . | . | . | 8
-------------------------------------

Enter X and Y Coordinate: 3 8

  0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8
-------------------------------------
  . | . | . | . | . | . | . | . | @ | 0
-------------------------------------
  . | . | . | . | . | . | . | . | . | 1
-------------------------------------
  . | . | . | . | . | . | . | . | . | 2
-------------------------------------
  . | . | @ | . | @ | . | . | . | . | 3
-------------------------------------
  . | . | @ | . | . | @ | . | . | . | 4
-------------------------------------
  @ | . | 2 | . | 4 | . | . | . | . | 5
-------------------------------------
  @ | . | 1 | @ | 1 | @ | . | . | . | 6
-------------------------------------
  @ | @ | 1 | @ | 1 | @ | . | . | . | 7
-------------------------------------

Enter X and Y Coordinate: 0 0

  0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8
-------------------------------------
  1 | . | . | . | 1 | . | . | . | @ | 0
-------------------------------------
  1 | . | . | . | 1 | @ | . | . | . | 1
-------------------------------------
  1 | . | . | . | 1 | 1 | . | . | . | 2
-------------------------------------
  . | . | . | . | . | . | . | . | . | 3
-------------------------------------
  @ | 1 | 1 | 2 | 3 | 1 | . | . | 1 | 4
-------------------------------------
  . | . | . | @ | 1 | 1 | . | . | . | 5
-------------------------------------
  . | . | 3 | 2 | 4 | . | 2 | . | . | 6
-------------------------------------
  @ | @ | 2 | @ | 1 | @ | . | . | . | 7
-------------------------------------

Enter X and Y Coordinate: 5 8
Boooom!!! You lose.
