package pooja.tictactoegame;

// contains blueprints for each of the 9 mini boards as well as the "skeleton" of the main board
public class Board {
    Box[][] boxes = new Box[3][3]; // a board is actually a 2D array of boxes
    int boardNumber; // stores the number of the board (0 through 9; 9 is the main board)
    boolean hasWinner = false; // keeps track of whether the board has a winner
    boolean isDraw = false; // keeps track of whether the board ended in a tie
    char winner; // keeps track of whether X or O won the board
    boolean isFull = false; // keeps track of whether the board is full
    
    // constructor
    public Board (int boardNumber) {
        int counter = 0;
        // initializes, in order, each box with a number from 0 to 8
        for (int b = 0; b < 3; b++) {
            for (int c = 0; c < 3; c++) {
                boxes[b][c] = new Box(counter);
                counter++;
            }
        }
        // sets board number (provided when an instance of the class is created)
        this.boardNumber = boardNumber;
    }
    
    // checks if the board is full
    public void fullBoardCheck () {
        // goes through each of the 9 squares
        for (int d = 0; d < 3; d++) {
            for (int e = 0; e < 3; e++) {
                // if even one square is not occupied, then the board is not full
                if (!boxes[d][e].isFull)
                        return;
            }
        }
        // if all 9 squares pass the check, the board is full
        isFull = true;
        // fullBoardCheck is only called after checking for a winner, so if there's no winner and the board is full, it's a tie
        if (!hasWinner)
            isDraw = true;            
    }
    
    // checks if a player has won yet
    // returns true if the most recent move is what led to a win on this board
    public boolean winnerCheck(char letter) {   
        // if it's already been determined that there's a winner, return false
        if (hasWinner)
            return false;
        
        // checking for 3 in a row
        for (int f = 0; f < 3; f++) {
            for (int g = 0; g < 3; g++) {
                if ((char) boxes[f][g].getBoxContent() != letter)
                    break;
                if (g == 2) {
                    hasWinner = true;
                    winner = letter;
                    editWinnerBoard();
                    return true;
                }        
            }
        }
        
        // checking for 3 in a column
        for (int i = 0; i < 3; i++) {
            for (int h = 0; h < 3; h++) {
                if ((char) boxes[h][i].getBoxContent() != letter)
                    break;
                if (h == 2) {
                    hasWinner = true;
                    winner = letter;
                    editWinnerBoard();
                    return true;
                }        
            }
        }
        
        // checking for 3 in the same diagonal line
        if ((char) boxes[1][1].getBoxContent() != letter)
            return false;
        if (((char) boxes[0][0].getBoxContent() == letter) && ((char) boxes[2][2].getBoxContent() == letter)) {
            hasWinner = true;
            winner = letter;
            editWinnerBoard();
            return true;
        }
        if (((char) boxes[0][2].getBoxContent() == letter) && ((char) boxes[2][0].getBoxContent() == letter)) {
            hasWinner = true;
            winner = letter;
            editWinnerBoard();
            return true;
        }          
        
        // if there's no winner yet, return false
        return false;
    }
    
    // fills the "unoccupied" boxes (the ones with numbers) with asterisks if the board has a winner
    public void editWinnerBoard() {
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (((char) boxes[j][k].getBoxContent() != 'X') && ((char) boxes[j][k].getBoxContent() != 'O'))
                    boxes[j][k].setBoxContent('*');
            }
        }
    }
    
    // finds and prints all the legal moves left on the board (only applies to miniboards)
    public void legalMoveFinder() {
        System.out.println("These are all of the moves that can currently be made:");
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                if (!boxes[m][n].isFull)
                    System.out.println("Board " + boardNumber + ", Box " + rowColToBox(m,n));
            }
        }
    }
    
    // method for converting a row and column pair to a box number (e.g. row 0, column 2 is box #3)
    public int rowColToBox(int row, int col) {
        int counter = 0;
        for (int p = 0; p < 3; p++) {
            for (int q = 0; q < 3; q++) {
                if ((p == row) && (q == col))
                    return counter;
                counter++;
            }
        }
        
        return -1;
    }
    
}