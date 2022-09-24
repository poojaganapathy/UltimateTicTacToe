package pooja.tictactoegame;

// class that provides blueprints for each of the 81 boxes on the board as well as the 9 "boxes" on the main board
public class Box {
    private int boxContent; // stores what mark is on the box ('X', 'O', 'T', '*', or a number
    boolean isFull = false; // keeps track of whether a box has a letter or not
    
    // constructor
    public Box(int boxContent) {
        this.boxContent = boxContent;
    }
    
    // setter method for the box content
    public void setBoxContent(int boxContent) {
        this.boxContent = boxContent;
    }
    
    // getter method for the box content
    public int getBoxContent() {
        return boxContent;
    }
}