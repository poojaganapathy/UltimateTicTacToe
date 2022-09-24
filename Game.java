package pooja.tictactoegame;

import java.util.*; // import required for allowing user input

// class that holds the blueprint for the entire game
public class Game {
    Scanner input = new Scanner(System.in); // for obtaining input from human players
    Board[] boards = new Board[9]; // array to hold the 9 miniboards
    MainBoard mainBoard; // instance of the MainBoard class to hold the main board
    Player[] players = new Player[2]; // array to hold the 2 players
    int currentPlayer = 0; // keeps track of which player is currently playing (0 is X, 1 is O)
    int gameMode; // holds the game mode chosen by the user (human vs computer, computer vs computer, human vs human)
    String userChoice; // holds the user's response for whether they want to play first when playing against the computer
    char player1Type; // 'h' for human, 'c' for computer
    char player2Type; // 'h' for human, 'c' for computer
    int boardChoice; // holds the number of the chosen board
    int boxChoice = -1; // holds the number of the chosen box
    int boxRow; // holds the row of the chosen box
    int boxCol; // holds the column of the chosen box
        
    // constructor
    public Game () {
        // creates 9 miniboards and stores them in the boards array
        for (int a = 0; a < 9; a++)
            boards[a] = new Board(a);
        
        // creates a main board
        mainBoard = new MainBoard(9);
        // initializes the main board by copying the contents of the 9 miniboards
        mainBoard.boardInitializer(boards);
    }
    
    // sets up the game
    public void setup () {
        System.out.println("Welcome to a game of Ultimate Tic Tac Toe!");
        System.out.println("There are three modes available:");
        System.out.println("1. Player vs. AI");
        System.out.println("2. AI vs. AI");
        System.out.println("3. Player vs. Player");
        
        // repeatedly prompts user to select a game mode until valid input is received
        while (true) {
            System.out.print("Please select a mode by typing in the mode's number (1, 2, or 3): ");
       
            // checks if input type is valid
            boolean isValidInput = input.hasNextInt();
                if (isValidInput)
                    gameMode = input.nextInt();
                else {               
                    System.out.println("Please enter integers only.");
                    input.next();
                    continue;
                }
            
            input.nextLine();
            // checks if the input actually chooses one of the 3 given options
            if ((gameMode != 1) && (gameMode != 2) && (gameMode != 3))
                System.out.println("Your input is invalid. Please try again.");
            else
                break;
        }
        
        // if mode 1 is chosen, repeatedly prompts user to input if they want to go first or not until valid input is received
        // also sets the player types accordingly
        while (true) {
            if (gameMode == 1) {
                System.out.print("Would you like to play first? Please enter 'yes' or 'no' (without the quotes): ");
                userChoice = input.nextLine();
                
                if (userChoice.equals("yes")) {
                    player1Type = 'h';
                    player2Type = 'c';
                    break;
                }
                else if (userChoice.equals("no")) {
                    player1Type = 'c';
                    player2Type = 'h';
                    break;
                }
                else
                    System.out.println("Your input is invalid. Please try again.");
            }
            else if (gameMode == 2) {
                player1Type = 'c';
                player2Type = 'c';
                break;
            }
            else {
                player1Type = 'h';
                player2Type = 'h';
                break;
            }
        }
        
        // creates two players of the correct types as determined above
        players[0] = new Player('X', player1Type);
        players[1] = new Player('O', player2Type);
        
        // prints the main board
        mainBoard.printBoard();
    }
    
    // begins actual gameplay
    public void play() {           
        while (true) {
            
            char currentPlayerType = players[currentPlayer].playerType; // stores if the current player is a human or a computer
            char currentPlayerLetter = players[currentPlayer].playerLetter; // stores if the current player is X or O
            
            System.out.println("It's Player " + currentPlayerLetter + "'s turn!");
           
            // boxChoice is -1 if this is the first move of the game or if the board with the same number as the previous player's box is full
            if (boxChoice == -1) {
                // prints a list of legal moves, with the board number and box number of each move
                mainBoard.legalMoveFinder(boards);
                System.out.println("");
            }
            
            // calls method that allows human and computer players to choose a board (if allowed) and validates human input
            boardChoice = players[currentPlayer].selectBoard(boxChoice);
            
            // if boxChoice is not -1, the only legal moves that need to be printed are from one board only
            if (boxChoice != -1) {
                    boards[boardChoice].legalMoveFinder();
                    System.out.println("");
            }
            
            while (true) {             
                // if human player's board choice is full, repeatedly prompts for another choice until one that is not full is chosen
                if (currentPlayerType == 'h') {
                    if (boards[boardChoice].isFull)
                        System.out.println("That board is already full. Please pick another board.");
                    else
                        break;
                }
                // if computer player's choice is full, repeatedly generates a new choice until one that is not full is found
                else {
                    if (!boards[boardChoice].isFull) {
                        if (boxChoice == -1)
                            System.out.println("Player " + currentPlayerLetter + " has chosen Board #" + boardChoice + ".");
                            
                        break;
                    }
                }
                boardChoice = players[currentPlayer].selectBoard(boxChoice);
            }
            
            while (true) {
                // calls method that allows human and computer players to choose a box and validates human input
                boxChoice = players[currentPlayer].selectBox();
                
                // finds the row and column of a box given the box number (e.g. box 5 is in row 1, column 2)
                if ((boxChoice == 0) || (boxChoice == 1) || (boxChoice == 2)) {
                    boxRow = 0;
                    boxCol = boxChoice;
                }
                else if ((boxChoice == 3) || (boxChoice == 4) || (boxChoice == 5)) {
                    boxRow = 1;
                    boxCol = boxChoice - 3;
                }
                else {
                    boxRow = 2;
                    boxCol = boxChoice - 6;
                }
                 
                // if chosen box is full, human players are repeatedly prompted for a new box number and computer players keep generating new box numbers
                if (boards[boardChoice].boxes[boxRow][boxCol].isFull) {
                    if (currentPlayerType == 'h')
                        System.out.println("That box is already full. Please pick another box.");
                }
                else {
                    if (currentPlayerType == 'c')
                        System.out.println("Player " + currentPlayerLetter + " has chosen Box #" + boxChoice + ".");
                    
                    // method for updating and checking the main board and miniboards
                    updateAndCheckBoards(currentPlayerLetter);              
                    
                    // prints the main board
                    mainBoard.printBoard();
                    
                    // prints all the miniboard winners so far
                    mainBoard.printWinners(boards);
                    
                    // if the main board has a winner, the game is over
                    if (mainBoard.hasWinner) {
                        System.out.println(" ");
                        System.out.println("Congratulations, Player " + currentPlayerLetter + "! You've won on the main board, so you've won this game of Ultimate Tic Tac Toe!");
                            return;
                    } 
                                 
                    // if the main board has a tie, the game is over
                    if (mainBoard.isDraw) {
                        System.out.println("");
                        System.out.println("It's a draw on the main board! You've now reached the end of the game.");
                        return;
                    } 
                
                    break;
                }
            }
            
            // if the board with the same number as the current player's box choice is full, boxChoice is set to -1
            if (boards[boxChoice].isFull)
                boxChoice = -1;
            
            // switches the player
            if (currentPlayer != 0)
                currentPlayer = 0;
            else
                currentPlayer = 1;
        }
    }     
    
    // method for updating and checking the main board and mini boards
    public void updateAndCheckBoards(char playerLetter) {
        // updates the value of the box that was just filled
        boards[boardChoice].boxes[boxRow][boxCol].setBoxContent(playerLetter);
        // updates the variable keeping track of whether the box is full
        boards[boardChoice].boxes[boxRow][boxCol].isFull = true;
                    
        // checks if the miniboard has a winner
        boolean temp = boards[boardChoice].winnerCheck(playerLetter);
        // checks if the miniboard is full, which also checks for a tie
        boards[boardChoice].fullBoardCheck();
        
        // if there is a tie or winner, update the main board
        if ((temp) || (boards[boardChoice].isDraw)) {
            // finds the row and column of a box given the box number (e.g. box 5 is in row 1, column 2)
            // here, we are using boardChoice since each miniboard is a box on the main board
            if ((boardChoice == 0) || (boardChoice == 1) || (boardChoice == 2)) {
                boxRow = 0;
                boxCol = boardChoice;
            }
            else if ((boardChoice == 3) || (boardChoice == 4) || (boardChoice == 5)) {
                boxRow = 1;
                boxCol = boardChoice - 3;
            }
            else {
                boxRow = 2;
                boxCol = boardChoice - 6;
            }
            
            // if there is a winner on the miniboard, set the corresponding box on the main board to the winner's letter (X or O)
            if (temp) {
                mainBoard.boxes[boxRow][boxCol].setBoxContent(playerLetter);
                // updates the variable keeping track of whether the box is full
                mainBoard.boxes[boxRow][boxCol].isFull = true;
                System.out.println("Congratulations, Player " + playerLetter + "! You've won on Board #" + boardChoice + "!");
                System.out.println("");
            }
            // if the miniboard is full and there is a tie, set the corresponding box on the main board to 'T'
            else {
                mainBoard.boxes[boxRow][boxCol].setBoxContent('T');
                // updates the variable keeping track of whether the box is full
                mainBoard.boxes[boxRow][boxCol].isFull = true;
                System.out.println("It's a tie on Board #" + boardChoice + "!");
                System.out.println("");
            }
        }
        
        // updates the 9 rows of miniboard rows on the main board
        mainBoard.updateMainBoard(boards);
        
        // checks for a winner on the main board
        mainBoard.winnerCheck(playerLetter);
        
        // checks if the main board is full
        mainBoard.fullBoardCheck();
    }
}