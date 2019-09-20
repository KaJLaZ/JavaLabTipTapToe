import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testGame {
    
    Game newGame;
    @BeforeEach
    public void initializeGame(){
        newGame = new Game('X', 'O');
    }
    @Test
    public void startGame() {
        char[][] expected = {{' ', ' ', ' '},
                             {' ', ' ', ' '},
                             {' ', ' ', ' '}};

            Assertions.assertArrayEquals(expected, newGame.getField());
    }
    @Test
    public void sameSymbols() {
        Assertions.assertThrows(Game.IncorectInputException.class, ()->{
            Game newGame =  new Game('X', 'X');
        });
    }
    @Test
    public void currentPlayer() {
        char firPlayerSymb = 'X';
        char secPlayerSymb = 'O';

        Game newGame =  new Game(firPlayerSymb, secPlayerSymb);

        assert (newGame.getCurrentPlayerSymbol() == firPlayerSymb);
    }
    @Test
    public void firstStep() {
        Assertions.assertDoesNotThrow(()->{
            newGame.step(0, 1);
        });
    }
    @Test
    public void stepInSamePlace() {
        Assertions.assertThrows(Game.IncorectInputException.class, ()->{
            newGame.step(0, 1);
            newGame.step(0, 1);
        });
    }
    @Test
    public void firstPlayerWin() {
        Assertions.assertThrows(Game.IncorectInputException.class, ()->{
            newGame.step(0, 0);
            newGame.step(0, 1);

            newGame.step(1, 1);
            newGame.step(2, 1);

            newGame.step(2, 2);
            newGame.step(1, 2);
        });
    }
    @Test
    public void goingAbroad() {
        Assertions.assertThrows(Game.IncorectInputException.class, ()->{
            newGame.step(3, 3);
        });
    }

    @Test
    public void draw() {
        Assertions.assertThrows(Game.IncorectInputException.class, ()-> {
            newGame.step(0, 0);
            newGame.step(0, 1);

            newGame.step(0, 2);
            newGame.step(1, 0);

            newGame.step(1, 2);
            newGame.step(1, 1);

            newGame.step(2, 0);
            newGame.step(2, 2);

            newGame.step(2, 1);
        });

    }


}
