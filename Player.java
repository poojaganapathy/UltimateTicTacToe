package pooja.tictactoegame;

import java.util.*; // import required for allowing user input

// instances of this class are created for each player
public class Player {
    char playerLetter; // 'X' or 'O'
    char playerType; // 'h' for human, 'c' for computer
    int boardChoice; // number representing the miniboard that was chosen
    int temp; // temporary variable holding the chosen box
    Scanner inputScanner = new Scanner(System.in); // for obtaining input from human players
    Random randomObject = new Random(); // an object of class Random is needed for generating random numbers
    
    // constructor
    public Player (char playerLetter, char playerType) {
        this.playerLetter = playerLetter;
        this.playerType = playerType;
    }
    
    // board selection
    public int selectBoard(int boxChoice) {
        // -1 means that either this is the first move of the game or the previous player's box choice corresponded to a board that
        // is already full
        if ((boxChoice == -1) && (playerType == 'h')) {
            // continues asking for input until valid input is provided
            while (true) {
                // asks human players to select a board
                System.out.print("Which board (0 to 8) do you want to place the ‘" + playerLetter + "’ on? Board #: ");
                // validates the input type
                boolean isValidInput2 = inputScanner.hasNextInt();
                if (isValidInput2)
                    boardChoice = inputScanner.nextInt();
                else {               
                    System.out.println("Please enter integers only.");
                    inputScanner.next();
                    continue;
                }
                // checks if the input is within the accepted range
                if ((boardChoice < 0) || (boardChoice > 8))
                    System.out.println ("Your input is invalid. Please try again.");
                else
                    break;
            }
            // returns the board number
            return boardChoice;         
        }
        else if ((boxChoice == -1) && (playerType == 'c')) {
            // allows computer players to pick a board by generating a random number between 0 and 8
            boardChoice = randomObject.nextInt(9);
            // returns the board number
            return boardChoice;         
        }
        else {
            // if boxChoice is not -1, then the board number should be the same as the previous player's box number
            boardChoice = boxChoice;
            System.out.println("The board that must be used is Board #" + boardChoice + ".");
            // returns the board number
            return boardChoice;
        }
    }
    
    // box selection
    public int selectBox() {
        // continues asking for input from human players until valid input is provided
        while (true) {
            if (playerType == 'c')
                // allows computer players to pick a box by generating a random number between 0 and 8 and returns the box number
                return randomObject.nextInt(9);
            else {
                // asks human players to select a box
                System.out.print("Which box (0 to 8) do you want to place it on? Box #: ");
                // validates the input type
                boolean isValidInput3 = inputScanner.hasNextInt();
                if (isValidInput3)
                    temp = inputScanner.nextInt();
                else {               
                    System.out.println("Please enter integers only.");
                    inputScanner.next();
                    continue;
                }
                // checks if the input is within the accepted range
                if ((temp < 0) || (temp > 8))
                    System.out.println ("Your input is invalid. Please try again.");
                else
                    break;
            }
        }  
        // returns a box number selected by a human player
        return temp;
    }
}
