package pooja.tictactoegame;

// driver class to create an instance of the game and start the game
public class Tictactoegame {
    public static void main(String[] args) {
        Game game = new Game();
        game.setup();
        game.play();
    }
}
