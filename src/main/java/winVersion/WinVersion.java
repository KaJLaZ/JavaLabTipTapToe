package winVersion;

import java.util.ArrayList;
import java.util.Collections;

public class WinVersion {
    private int amountMatchFirPlayer;
    private int amountMatchSecPlayer;

    private void addMatchPlayer(Players player){
        if(player == Players.FirstPlayer)
            amountMatchFirPlayer++;

        else {amountMatchSecPlayer++;}
    }

    public enum Players{
        FirstPlayer, SecondPlayer;
    }

    private ArrayList<Coordinate> coordinates;

    private final static int AMOUNT_CELLS_FOR_WIN = 3;

    public WinVersion(Coordinate... coordinates) {
        this.coordinates = new ArrayList<Coordinate>();

        Collections.addAll(this.coordinates, coordinates);

        amountMatchFirPlayer =  0;
        amountMatchSecPlayer = 0;
    }

    static public boolean isWin(WinVersion[] winVersions) {

        for (WinVersion i : winVersions) {

            if (i.amountMatchFirPlayer == AMOUNT_CELLS_FOR_WIN || i.amountMatchSecPlayer == AMOUNT_CELLS_FOR_WIN)
                return true;
        }

        return false;
    }

    static public void refrWinVers(WinVersion[] winVersions, Coordinate coordMove, Players player) {
        for (WinVersion i : winVersions) {

            for (Coordinate j : i.coordinates) {

                if (j.equals(coordMove))
                    i.addMatchPlayer(player);
            }
        }
    }
}

