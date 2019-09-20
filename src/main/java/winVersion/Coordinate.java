package winVersion;

public class Coordinate {
    private int rowNum;
    private int colNum;

    public Coordinate(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    public static Coordinate[][] makeSquareNetCoordinates(int amRows, int amColumns) {
        Coordinate[][] netCoordinates = new Coordinate[amRows][amColumns];

        for (int i = 0; i < amRows; i++) {

            for (int j = 0; j < amRows; j++) {
                netCoordinates[i][j] = new Coordinate(i, j);
            }
        }

        return netCoordinates;
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Coordinate))
            return false;

        Coordinate coord = (Coordinate) obj;

        if(coord.rowNum != rowNum || coord.colNum != colNum)
            return false;

        return true;
    }
}
