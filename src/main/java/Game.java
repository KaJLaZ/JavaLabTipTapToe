import winVersion.*;

public class Game {
    private char[][] field;

    public char[][] getField() {
        return field;
    }

    private WinVersion.Players currentPlayer;

    private char firPlayerSymb;
    private char secPlayerSymb;

    public char getCurrentPlayerSymbol() {
        if( currentPlayer == WinVersion.Players.FirstPlayer)
            return firPlayerSymb;

        else { return secPlayerSymb;}
    }

    private int amountSteps;

    private WinVersion[] versions;
    public static final int AMOUNT_ROWS = 3;
    public static final int AMOUNT_COLUMNS = 3;
    public static final int AMOUNT_WIN_VARIANTS = 8;

    public int getAmountCells(){ return AMOUNT_ROWS * AMOUNT_COLUMNS; }

    public Game(char firPlayerSymb, char secPlayerSymb){
        if(firPlayerSymb == secPlayerSymb)
            throw new IncorectInputException("symbols are same");

        this.firPlayerSymb = firPlayerSymb;
        this.secPlayerSymb = secPlayerSymb;

        currentPlayer = WinVersion.Players.FirstPlayer;
        amountSteps = 0;

        versions = Initialize.initializeWinVersion();
        field =  Initialize.initializeField();
    }

    public boolean step(int row, int column){
        if(row >= AMOUNT_ROWS || column >= AMOUNT_COLUMNS)
            throw new IncorectInputException("going abroad");

        if(!isAvailable(field[row][column]))
            throw new IncorectInputException("place is unavailable");

        WinVersion.refrWinVers(versions, new Coordinate(row, column), currentPlayer);
        field[row][column] = getCurrentPlayerSymbol();
        amountSteps++;

        if(WinVersion.isWin(versions)) {
            System.out.println(String.format("%s is win", currentPlayer));
            return false;
        }

        if(amountSteps == AMOUNT_ROWS * AMOUNT_COLUMNS){
            System.out.println(String.format("draw"));
            return false;
        }

        nextPlayer();
        return true;
    }

    private boolean isAvailable(char symb){
        return symb == ' ';
    }

    private void nextPlayer(){
        if(currentPlayer == WinVersion.Players.FirstPlayer)
            currentPlayer = WinVersion.Players.SecondPlayer;

        else { currentPlayer = WinVersion.Players.FirstPlayer; }
    }

    private static class Initialize{
        private static WinVersion[] initializeWinVersion() {
            Coordinate[][] coordNet = Coordinate.makeSquareNetCoordinates(AMOUNT_ROWS, AMOUNT_COLUMNS);

            WinVersion[] winVersions = new WinVersion[AMOUNT_WIN_VARIANTS];

            winVersions[0] = new WinVersion(coordNet[0][0], coordNet[0][1], coordNet[0][2]);
            winVersions[1] = new WinVersion(coordNet[1][0], coordNet[1][1], coordNet[1][2]);
            winVersions[2] = new WinVersion(coordNet[2][0], coordNet[2][1], coordNet[2][2]);
            winVersions[3] = new WinVersion(coordNet[0][0], coordNet[1][0], coordNet[2][0]);
            winVersions[4] = new WinVersion(coordNet[0][1], coordNet[1][1], coordNet[2][1]);
            winVersions[5] = new WinVersion(coordNet[0][2], coordNet[1][2], coordNet[2][2]);
            winVersions[6] = new WinVersion(coordNet[0][0], coordNet[1][1], coordNet[2][2]);
            winVersions[7] = new WinVersion(coordNet[2][0], coordNet[1][1], coordNet[0][2]);

            return winVersions;
        }

        private static char[][] initializeField(){
            return new char[][] {{' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}};
        }
    }

    public class IncorectInputException extends RuntimeException{
        public IncorectInputException(String message){
            super(message);
        }

        @Override
        public IncorectInputException getCause(){
            return this;
        }
    }
}
