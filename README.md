# UltimateTicTacToe

## Introduction

This program is a command line version of Ultimate Tic Tac Toe for two players who can each be a human or a computer. Ultimate Tic Tac Toe consists of a regular Tic Tac Toe board with smaller Tic Tac Toe boards in each of the 9 squares. A players wins the game when three of the smaller boards that they win on form a row, column, or diagonal line together.

## Features

- [X] **Supports both human and computer players**
- [X] **The full board (all 9 mini boards + main board + mark on each square) is displayed during each player's turn**
- [X] **Displays all possible (legal) moves that a player can make during their turn**
- [X] **Checks if human input is of the correct type before reading it in**
- [X] **If the game is Human vs. Computer, the human player can specify if they would like to play first**
- [X] **Player 1, on their first turn, chooses the small board they would like to start on as well as the box where they would like to make their move**
- [X] **For all subsequent turns, players can only choose which box; the board is determined by the box the previous player picked (unless that board is full, in which case the player can pick any board)**
- [X] **Keeps track of the results and winners of each of the 9 mini boards as well as the main board**
- [X] **Determines if a board or particular box is already full and if a board already has a winner**
- [X) **Keeps track of the box chosen by the previous player**


## Design

I split my program into 6 classes. Class Tictactoegame is the driver for the program. It creates an instance of the game and starts the
game. Class Game contains the main game and makes use of the remaining 4 classes. Class Board contains the blueprint for each of the 9
small boards as well as the "skeleton" of the main board (i.e. who won each of the 9 mini games). It contains methods for determining
if the board is full, if there is a winner, replacing the numbers in empty boxes with asterisks if the board has a winner, finding the
legal moves left on the board, and finding the row and column of any box on the board. Class MainBoard is a subclass of Board; as
mentioned before, creating an instance of this class will also create a mini board with the "skeleton" of the main board, but it also
includes a 9x9 array that contains the marks on each of the 81 mini squares. It also includes methods for printing the
whole board, updating the board, finding all the legal moves left on the entire board, and printing a list of all the miniboard winners
so far. Class Box contains setter and getter methods for the value in a box ('X', 'O', the box number, an asterisk, or, in the case
of the main board, 'T') and also keeps track of whether the box is occupied by a letter or not. Finally, class Player contains info
about each player's letter and whether they are a human or a computer, and also contains methods that allow human and computer players
to choose the boards and boxes they want (either through input or random number generation) and also validates the human inputs.

## Testing

I tested the program multiple times, mostly in AI vs. AI mode. I made sure that the list of winners could logically lead to the main
winner (e.g. if X only won boards 0, 2, and 5, there's no way X could win the main board). I also made sure that the list of winners
matched what was seen on the displayed board. I also tested various types of invalid input in the two modes that involve humans to make
sure that the program wouldn't crash; this includes input of the wrong type as well as input of the correct type but out of range. I
also made sure that the list of legal moves was being properly updated and matched what the board was displaying.
