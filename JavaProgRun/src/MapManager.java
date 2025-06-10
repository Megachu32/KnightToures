public class MapManager {
    public static Tile[][] loadSampleMap() {
        int rows = 5, cols = 5; // i believe this change the map, could be expanded upon.
        Tile[][] map = new Tile[rows][cols]; // map matrix


        // filling the map with tile objects
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = new Tile();
            }
        }

        // Sample setup
        // examople of changing tne tile properties
        map[0][0].isBlocked = false;
        map[4][4].isExit = true;
        map[2][2].isBlocked = true; // blocking a tile

        // returning the map?
        return map;
    }
}