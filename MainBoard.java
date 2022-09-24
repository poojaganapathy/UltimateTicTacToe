package pooja.tictactoegame;

// only one instance of this class is created; this is the main board
// this is a subclass of class Board

public class MainBoard extends Board {
    char[][] mainBoardArray = new char[9][9]; // stores the characters at each of the 81 miniboard squares on the board
    String[] boardLineArray = new String[9]; // stores what is printed in the 9 rows of miniboard squares when the board is printed
       
    // constructor; uses the constructor in the superclass and creates a "miniboard" to hold the skeleton of the main board
    public MainBoard(int number) {
        super(number);
    }
    
    // initializes the board; we call this method only after an instance is created and only once
    // copies (from the 9 miniboard arrays) the content of each of the 81 miniboxes into mainBoardArray
    public void boardInitializer(Board[] boardArray) {
        // calls method for initializing the nine lines of the board to empty strings
        initializeBoardLines();
        
        int boardCount = 0; // stores the board whose array is currently being copied
        int rowNumber = 0; // stores the current row number of the array being copied
        int colNumber = 0; // stores the current column number of the array being copied
        for (int r = 0; r < 9; r++) {
            for (int s = 0; s < 9; s++) {
                mainBoardArray[r][s] = ((char) (boardArray[boardCount].boxes[rowNumber][colNumber].getBoxContent() + '0'));
                colNumber++;
                if (colNumber == 3) {
                    colNumber = 0;
                    boardCount++;
                }
            }
            rowNumber++;
            if (rowNumber == 3)
                rowNumber = 0;
            
            if (r <= 2)
                boardCount = 0;
            else if (r <= 5)
                boardCount = 3;
            else
                boardCount = 6;             
        }
    }
    
    // finds all the legal moves left on the entire board (this is called when a player is allowed to choose both the board and the box)
    public void legalMoveFinder(Board[] boardArray) {
        System.out.println("These are all of the moves that can currently be made:");
        for (int currentBoard = 0; currentBoard < 9; currentBoard++) {
            if (boardArray[currentBoard].isFull)
                continue;
            
            for (int currentRow = 0; currentRow < 3; currentRow++) {
                for (int currentCol = 0; currentCol < 3; currentCol++) {
                    if (!boardArray[currentBoard].boxes[currentRow][currentCol].isFull)
                        System.out.println("Board #" + currentBoard + ", Box #" + rowColToBox(currentRow, currentCol));
                }
            }
        }
        
    }
    
    // finds and prints the list of miniboard winners so far
    public void printWinners(Board[] boardArray) {
        for (int v = 0; v < 9; v++) {
            if (boardArray[v].hasWinner == true)
                System.out.println("Board #" + v + " winner: Player " + boardArray[v].winner);
        }
    }
    
        // initializes each of the elements of the array holding the 9 total rows of mini boards to empty strings
    public void initializeBoardLines() {
        for (int A = 0; A < 9; A++) {
            boardLineArray[A] = "";
        }
    }
    
    // prints the board after updating the board's rows with the most recent move
    public void printBoard() {
        updateBoardLines();
        
        for (int w = 0; w < 9; w += 3) {
            System.out.println("");
            System.out.println("Board " + w + ":      Board " + (w + 1) + ":      Board " + (w + 2) + ":");
            System.out.println("");
            System.out.println(boardLineArray[w]);
            System.out.println("---------- |  ---------- |  ----------");
            System.out.println(boardLineArray[w + 1]);
            System.out.println("---------- |  ---------- |  ----------");
            System.out.println(boardLineArray[w + 2]);
            System.out.println("");
            if (w != 6)
                System.out.println("--------------------------------------");
        }
    }
    
    // updates the board's rows with the most recent move by initializing the lines and copying all the rows from the main board array
    public void updateBoardLines() {
        initializeBoardLines();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if ((y == 3) || (y == 6))
                    boardLineArray[x] += " ";
                
                boardLineArray[x] += mainBoardArray[x][y];
                
                if ((y == 2) || (y == 5))
                    boardLineArray[x] += " ";
                
                if (y != 8)
                    boardLineArray[x] += " | "; 
            }
        }
    }
    
    public void updateMainBoard(Board[] boardArray) {
        
        mainBoardArray[0][0] = (char) boardArray[0].boxes[0][0].getBoxContent();
        if ((mainBoardArray[0][0] >= 0) && (mainBoardArray[0][0] <=9))
            mainBoardArray[0][0] += '0';
        mainBoardArray[0][1] = (char) boardArray[0].boxes[0][1].getBoxContent();
        if ((mainBoardArray[0][1] >= 0) && (mainBoardArray[0][1] <=9))
            mainBoardArray[0][1] += '0';
        mainBoardArray[0][2] = (char) boardArray[0].boxes[0][2].getBoxContent();
        if ((mainBoardArray[0][2] >= 0) && (mainBoardArray[0][2] <=9))
            mainBoardArray[0][2] += '0';
        mainBoardArray[0][3] = (char) boardArray[1].boxes[0][0].getBoxContent();
        if ((mainBoardArray[0][3] >= 0) && (mainBoardArray[0][3] <=9))
            mainBoardArray[0][3] += '0';
        mainBoardArray[0][4] = (char) boardArray[1].boxes[0][1].getBoxContent();
        if ((mainBoardArray[0][4] >= 0) && (mainBoardArray[0][4] <=9))
            mainBoardArray[0][4] += '0';
        mainBoardArray[0][5] = (char) boardArray[1].boxes[0][2].getBoxContent();
        if ((mainBoardArray[0][5] >= 0) && (mainBoardArray[0][5] <=9))
            mainBoardArray[0][5] += '0';
        mainBoardArray[0][6] = (char) boardArray[2].boxes[0][0].getBoxContent();
        if ((mainBoardArray[0][6] >= 0) && (mainBoardArray[0][6] <=9))
            mainBoardArray[0][6] += '0';
        mainBoardArray[0][7] = (char) boardArray[2].boxes[0][1].getBoxContent();
        if ((mainBoardArray[0][7] >= 0) && (mainBoardArray[0][7] <=9))
            mainBoardArray[0][7] += '0';
        mainBoardArray[0][8] = (char) boardArray[2].boxes[0][2].getBoxContent();
        if ((mainBoardArray[0][8] >= 0) && (mainBoardArray[0][8] <=9))
            mainBoardArray[0][8] += '0';
        mainBoardArray[1][0] = (char) boardArray[0].boxes[1][0].getBoxContent();
        if ((mainBoardArray[1][0] >= 0) && (mainBoardArray[1][0] <=9))
            mainBoardArray[1][0] += '0';
        mainBoardArray[1][1] = (char) boardArray[0].boxes[1][1].getBoxContent();
        if ((mainBoardArray[1][1] >= 0) && (mainBoardArray[1][1] <=9))
            mainBoardArray[1][1] += '0';
        mainBoardArray[1][2] = (char) boardArray[0].boxes[1][2].getBoxContent();
        if ((mainBoardArray[1][2] >= 0) && (mainBoardArray[1][2] <=9))
            mainBoardArray[1][2] += '0';
        mainBoardArray[1][3] = (char) boardArray[1].boxes[1][0].getBoxContent();
        if ((mainBoardArray[1][3] >= 0) && (mainBoardArray[1][3] <=9))
            mainBoardArray[1][3] += '0';
        mainBoardArray[1][4] = (char) boardArray[1].boxes[1][1].getBoxContent();
        if ((mainBoardArray[1][4] >= 0) && (mainBoardArray[1][4] <=9))
            mainBoardArray[1][4] += '0';
        mainBoardArray[1][5] = (char) boardArray[1].boxes[1][2].getBoxContent();
        if ((mainBoardArray[1][5] >= 0) && (mainBoardArray[1][5] <=9))
            mainBoardArray[1][5] += '0';
        mainBoardArray[1][6] = (char) boardArray[2].boxes[1][0].getBoxContent();
        if ((mainBoardArray[1][6] >= 0) && (mainBoardArray[1][6] <=9))
            mainBoardArray[1][6] += '0';
        mainBoardArray[1][7] = (char) boardArray[2].boxes[1][1].getBoxContent();
        if ((mainBoardArray[1][7] >= 0) && (mainBoardArray[1][7] <=9))
            mainBoardArray[1][7] += '0';
        mainBoardArray[1][8] = (char) boardArray[2].boxes[1][2].getBoxContent();
        if ((mainBoardArray[1][8] >= 0) && (mainBoardArray[1][8] <=9))
            mainBoardArray[1][8] += '0';
        mainBoardArray[2][0] = (char) boardArray[0].boxes[2][0].getBoxContent();
        if ((mainBoardArray[2][0] >= 0) && (mainBoardArray[2][0] <=9))
            mainBoardArray[2][0] += '0';
        mainBoardArray[2][1] = (char) boardArray[0].boxes[2][1].getBoxContent();
        if ((mainBoardArray[2][1] >= 0) && (mainBoardArray[2][1] <=9))
            mainBoardArray[2][1] += '0';
        mainBoardArray[2][2] = (char) boardArray[0].boxes[2][2].getBoxContent();
        if ((mainBoardArray[2][2] >= 0) && (mainBoardArray[2][2] <=9))
            mainBoardArray[2][2] += '0';
        mainBoardArray[2][3] = (char) boardArray[1].boxes[2][0].getBoxContent();
        if ((mainBoardArray[2][3] >= 0) && (mainBoardArray[2][3] <=9))
            mainBoardArray[2][3] += '0';
        mainBoardArray[2][4] = (char) boardArray[1].boxes[2][1].getBoxContent();
        if ((mainBoardArray[2][4] >= 0) && (mainBoardArray[2][4] <=9))
            mainBoardArray[2][4] += '0';
        mainBoardArray[2][5] = (char) boardArray[1].boxes[2][2].getBoxContent();
        if ((mainBoardArray[2][5] >= 0) && (mainBoardArray[2][5] <=9))
            mainBoardArray[2][5] += '0';
        mainBoardArray[2][6] = (char) boardArray[2].boxes[2][0].getBoxContent();
        if ((mainBoardArray[2][6] >= 0) && (mainBoardArray[2][6] <=9))
            mainBoardArray[2][6] += '0';
        mainBoardArray[2][7] = (char) boardArray[2].boxes[2][1].getBoxContent();
        if ((mainBoardArray[2][7] >= 0) && (mainBoardArray[2][7] <=9))
            mainBoardArray[2][7] += '0';
        mainBoardArray[2][8] = (char) boardArray[2].boxes[2][2].getBoxContent();
        if ((mainBoardArray[2][8] >= 0) && (mainBoardArray[2][8] <=9))
            mainBoardArray[2][8] += '0';
        mainBoardArray[3][0] = (char) boardArray[3].boxes[0][0].getBoxContent();
        if ((mainBoardArray[3][0] >= 0) && (mainBoardArray[3][0] <=9))
            mainBoardArray[3][0] += '0';
        mainBoardArray[3][1] = (char) boardArray[3].boxes[0][1].getBoxContent();
        if ((mainBoardArray[3][1] >= 0) && (mainBoardArray[3][1] <=9))
            mainBoardArray[3][1] += '0';
        mainBoardArray[3][2] = (char) boardArray[3].boxes[0][2].getBoxContent();
        if ((mainBoardArray[3][2] >= 0) && (mainBoardArray[3][2] <=9))
            mainBoardArray[3][2] += '0';
        mainBoardArray[3][3] = (char) boardArray[4].boxes[0][0].getBoxContent();
        if ((mainBoardArray[3][3] >= 0) && (mainBoardArray[3][3] <=9))
            mainBoardArray[3][3] += '0';
        mainBoardArray[3][4] = (char) boardArray[4].boxes[0][1].getBoxContent();
        if ((mainBoardArray[3][4] >= 0) && (mainBoardArray[3][4] <=9))
            mainBoardArray[3][4] += '0';
        mainBoardArray[3][5] = (char) boardArray[4].boxes[0][2].getBoxContent();
        if ((mainBoardArray[3][5] >= 0) && (mainBoardArray[3][5] <=9))
            mainBoardArray[3][5] += '0';
        mainBoardArray[3][6] = (char) boardArray[5].boxes[0][0].getBoxContent();
        if ((mainBoardArray[3][6] >= 0) && (mainBoardArray[3][6] <=9))
            mainBoardArray[3][6] += '0';
        mainBoardArray[3][7] = (char) boardArray[5].boxes[0][1].getBoxContent();
        if ((mainBoardArray[3][7] >= 0) && (mainBoardArray[3][7] <=9))
            mainBoardArray[3][7] += '0';
        mainBoardArray[3][8] = (char) boardArray[5].boxes[0][2].getBoxContent();
        if ((mainBoardArray[3][8] >= 0) && (mainBoardArray[3][8] <=9))
            mainBoardArray[3][8] += '0';
        mainBoardArray[4][0] = (char) boardArray[3].boxes[1][0].getBoxContent();
        if ((mainBoardArray[4][0] >= 0) && (mainBoardArray[4][0] <=9))
            mainBoardArray[4][0] += '0';
        mainBoardArray[4][1] = (char) boardArray[3].boxes[1][1].getBoxContent();
        if ((mainBoardArray[4][1] >= 0) && (mainBoardArray[4][1] <=9))
            mainBoardArray[4][1] += '0';
        mainBoardArray[4][2] = (char) boardArray[3].boxes[1][2].getBoxContent();
        if ((mainBoardArray[4][2] >= 0) && (mainBoardArray[4][2] <=9))
            mainBoardArray[4][2] += '0';
        mainBoardArray[4][3] = (char) boardArray[4].boxes[1][0].getBoxContent();
        if ((mainBoardArray[4][3] >= 0) && (mainBoardArray[4][3] <=9))
            mainBoardArray[4][3] += '0';
        mainBoardArray[4][4] = (char) boardArray[4].boxes[1][1].getBoxContent();
        if ((mainBoardArray[4][4] >= 0) && (mainBoardArray[4][4] <=9))
            mainBoardArray[4][4] += '0';
        mainBoardArray[4][5] = (char) boardArray[4].boxes[1][2].getBoxContent();
        if ((mainBoardArray[4][5] >= 0) && (mainBoardArray[4][5] <=9))
            mainBoardArray[4][5] += '0';
        mainBoardArray[4][6] = (char) boardArray[5].boxes[1][0].getBoxContent();
        if ((mainBoardArray[4][6] >= 0) && (mainBoardArray[4][6] <=9))
            mainBoardArray[4][6] += '0';
        mainBoardArray[4][7] = (char) boardArray[5].boxes[1][1].getBoxContent();
        if ((mainBoardArray[4][7] >= 0) && (mainBoardArray[4][7] <=9))
            mainBoardArray[4][7] += '0';
        mainBoardArray[4][8] = (char) boardArray[5].boxes[1][2].getBoxContent();
        if ((mainBoardArray[4][8] >= 0) && (mainBoardArray[4][8] <=9))
            mainBoardArray[4][8] += '0';
        mainBoardArray[5][0] = (char) boardArray[3].boxes[2][0].getBoxContent();
        if ((mainBoardArray[5][0] >= 0) && (mainBoardArray[5][0] <=9))
            mainBoardArray[5][0] += '0';
        mainBoardArray[5][1] = (char) boardArray[3].boxes[2][1].getBoxContent();
        if ((mainBoardArray[5][1] >= 0) && (mainBoardArray[5][1] <=9))
            mainBoardArray[5][1] += '0';
        mainBoardArray[5][2] = (char) boardArray[3].boxes[2][2].getBoxContent();
        if ((mainBoardArray[5][2] >= 0) && (mainBoardArray[5][2] <=9))
            mainBoardArray[5][2] += '0';
        mainBoardArray[5][3] = (char) boardArray[4].boxes[2][0].getBoxContent();
        if ((mainBoardArray[5][3] >= 0) && (mainBoardArray[5][3] <=9))
            mainBoardArray[5][3] += '0';
        mainBoardArray[5][4] = (char) boardArray[4].boxes[2][1].getBoxContent();
        if ((mainBoardArray[5][4] >= 0) && (mainBoardArray[5][4] <=9))
            mainBoardArray[5][4] += '0';
        mainBoardArray[5][5] = (char) boardArray[4].boxes[2][2].getBoxContent();
        if ((mainBoardArray[5][5] >= 0) && (mainBoardArray[5][5] <=9))
            mainBoardArray[5][5] += '0';
        mainBoardArray[5][6] = (char) boardArray[5].boxes[2][0].getBoxContent();
        if ((mainBoardArray[5][6] >= 0) && (mainBoardArray[5][6] <=9))
            mainBoardArray[5][6] += '0';
        mainBoardArray[5][7] = (char) boardArray[5].boxes[2][1].getBoxContent();
        if ((mainBoardArray[5][7] >= 0) && (mainBoardArray[5][7] <=9))
            mainBoardArray[5][7] += '0';
        mainBoardArray[5][8] = (char) boardArray[5].boxes[2][2].getBoxContent();
        if ((mainBoardArray[5][8] >= 0) && (mainBoardArray[5][8] <=9))
            mainBoardArray[5][8] += '0';
        mainBoardArray[6][0] = (char) boardArray[6].boxes[0][0].getBoxContent();
        if ((mainBoardArray[6][0] >= 0) && (mainBoardArray[6][0] <=9))
            mainBoardArray[6][0] += '0';
        mainBoardArray[6][1] = (char) boardArray[6].boxes[0][1].getBoxContent();
        if ((mainBoardArray[6][1] >= 0) && (mainBoardArray[6][1] <=9))
            mainBoardArray[6][1] += '0';
        mainBoardArray[6][2] = (char) boardArray[6].boxes[0][2].getBoxContent();
        if ((mainBoardArray[6][2] >= 0) && (mainBoardArray[6][2] <=9))
            mainBoardArray[6][2] += '0';
        mainBoardArray[6][3] = (char) boardArray[7].boxes[0][0].getBoxContent();
        if ((mainBoardArray[6][3] >= 0) && (mainBoardArray[6][3] <=9))
            mainBoardArray[6][3] += '0';
        mainBoardArray[6][4] = (char) boardArray[7].boxes[0][1].getBoxContent();
        if ((mainBoardArray[6][4] >= 0) && (mainBoardArray[6][4] <=9))
            mainBoardArray[6][4] += '0';
        mainBoardArray[6][5] = (char) boardArray[7].boxes[0][2].getBoxContent();
        if ((mainBoardArray[6][5] >= 0) && (mainBoardArray[6][5] <=9))
            mainBoardArray[6][5] += '0';
        mainBoardArray[6][6] = (char) boardArray[8].boxes[0][0].getBoxContent();
        if ((mainBoardArray[6][6] >= 0) && (mainBoardArray[6][6] <=9))
            mainBoardArray[6][6] += '0';
        mainBoardArray[6][7] = (char) boardArray[8].boxes[0][1].getBoxContent();
        if ((mainBoardArray[6][7] >= 0) && (mainBoardArray[6][7] <=9))
            mainBoardArray[6][7] += '0';
        mainBoardArray[6][8] = (char) boardArray[8].boxes[0][2].getBoxContent();
        if ((mainBoardArray[6][8] >= 0) && (mainBoardArray[6][8] <=9))
            mainBoardArray[6][8] += '0';
        mainBoardArray[7][0] = (char) boardArray[6].boxes[1][0].getBoxContent();
        if ((mainBoardArray[7][0] >= 0) && (mainBoardArray[7][0] <=9))
            mainBoardArray[7][0] += '0';
        mainBoardArray[7][1] = (char) boardArray[6].boxes[1][1].getBoxContent();
        if ((mainBoardArray[7][1] >= 0) && (mainBoardArray[7][1] <=9))
            mainBoardArray[7][1] += '0';
        mainBoardArray[7][2] = (char) boardArray[6].boxes[1][2].getBoxContent();
        if ((mainBoardArray[7][2] >= 0) && (mainBoardArray[7][2] <=9))
            mainBoardArray[7][2] += '0';
        mainBoardArray[7][3] = (char) boardArray[7].boxes[1][0].getBoxContent();
        if ((mainBoardArray[7][3] >= 0) && (mainBoardArray[7][3] <=9))
            mainBoardArray[7][3] += '0';
        mainBoardArray[7][4] = (char) boardArray[7].boxes[1][1].getBoxContent();
        if ((mainBoardArray[7][4] >= 0) && (mainBoardArray[7][4] <=9))
            mainBoardArray[7][4] += '0';
        mainBoardArray[7][5] = (char) boardArray[7].boxes[1][2].getBoxContent();
        if ((mainBoardArray[7][5] >= 0) && (mainBoardArray[7][5] <=9))
            mainBoardArray[7][5] += '0';
        mainBoardArray[7][6] = (char) boardArray[8].boxes[1][0].getBoxContent();
        if ((mainBoardArray[7][6] >= 0) && (mainBoardArray[7][6] <=9))
            mainBoardArray[7][6] += '0';
        mainBoardArray[7][7] = (char) boardArray[8].boxes[1][1].getBoxContent();
        if ((mainBoardArray[7][7] >= 0) && (mainBoardArray[7][7] <=9))
            mainBoardArray[7][7] += '0';
        mainBoardArray[7][8] = (char) boardArray[8].boxes[1][2].getBoxContent();
        if ((mainBoardArray[7][8] >= 0) && (mainBoardArray[7][8] <=9))
            mainBoardArray[7][8] += '0';
        mainBoardArray[8][0] = (char) boardArray[6].boxes[2][0].getBoxContent();
        if ((mainBoardArray[8][0] >= 0) && (mainBoardArray[8][0] <=9))
            mainBoardArray[8][0] += '0';
        mainBoardArray[8][1] = (char) boardArray[6].boxes[2][1].getBoxContent();
        if ((mainBoardArray[8][1] >= 0) && (mainBoardArray[8][1] <=9))
            mainBoardArray[8][1] += '0';
        mainBoardArray[8][2] = (char) boardArray[6].boxes[2][2].getBoxContent();
        if ((mainBoardArray[8][2] >= 0) && (mainBoardArray[8][2] <=9))
            mainBoardArray[8][2] += '0';
        mainBoardArray[8][3] = (char) boardArray[7].boxes[2][0].getBoxContent();
        if ((mainBoardArray[8][3] >= 0) && (mainBoardArray[8][3] <=9))
            mainBoardArray[8][3] += '0';
        mainBoardArray[8][4] = (char) boardArray[7].boxes[2][1].getBoxContent();
        if ((mainBoardArray[8][4] >= 0) && (mainBoardArray[8][4] <=9))
            mainBoardArray[8][4] += '0';
        mainBoardArray[8][5] = (char) boardArray[7].boxes[2][2].getBoxContent();
        if ((mainBoardArray[8][5] >= 0) && (mainBoardArray[8][5] <=9))
            mainBoardArray[8][5] += '0';
        mainBoardArray[8][6] = (char) boardArray[8].boxes[2][0].getBoxContent();
        if ((mainBoardArray[8][6] >= 0) && (mainBoardArray[8][6] <=9))
            mainBoardArray[8][6] += '0';
        mainBoardArray[8][7] = (char) boardArray[8].boxes[2][1].getBoxContent();
        if ((mainBoardArray[8][7] >= 0) && (mainBoardArray[8][7] <=9))
            mainBoardArray[8][7] += '0';
        mainBoardArray[8][8] = (char) boardArray[8].boxes[2][2].getBoxContent();
        if ((mainBoardArray[8][8] >= 0) && (mainBoardArray[8][8] <=9))
            mainBoardArray[8][8] += '0';
    }
}