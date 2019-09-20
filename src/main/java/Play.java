import java.util.Scanner;

public abstract class Play {
    static char firSymbPlayer = ' ';
    static char secSymbPlayer = ' ';

    static int tempRow = 0;
    static int tempCol = 0;

    public static void Play(){
        Game game = start();
        boolean canStep = true;

        while(canStep) {
            try {

                inputPlaceTurn();
                canStep = game.step(tempRow, tempCol);
                printField(game.getField());

                continue;

            } catch (Game.IncorectInputException ex) {
                System.out.println(ex);

                switch (ex.getMessage()) {
                    case "going abroad":
                        continue;

                    case "place is unavailable":
                        continue;

                        default:
                            break;
                }
            }
            break;
        }
    }

    private static void inputPlayersSymbols(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input first players symbol");
        firSymbPlayer = scanner.next().charAt(0);

        System.out.println("Input second players symbol");
        secSymbPlayer = scanner.next().charAt(0);
    }

    private static void inputPlaceTurn(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input row");
        tempRow = scanner.nextInt();

        System.out.println("Input column");
        tempCol = scanner.nextInt();
    }

    private static Game start(){
        Game game = new Game('x', 'o');

        try {
            inputPlayersSymbols();
            game = new Game(firSymbPlayer, secSymbPlayer);
        }
        catch (Game.IncorectInputException ex){
            start();
        }

        return game;
    }

    private static void printField(char[][] field){
        for (int i = 0; i < field.length; i++){

            for (int j = 0; j < field[0].length; j++){
                System.out.print(String.format("|%s|", field[i][j]));
            }
            System.out.println();
        }
    }
}
