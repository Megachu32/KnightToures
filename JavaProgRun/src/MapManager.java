public class MapManager {
    public static Tile[][] loadSampleMap() {
        int rows = 5, cols = 5;
        Tile[][] map = new Tile[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = new Tile();
            }
        }

        // Sample setup
        map[0][0].isBlocked = false;
        map[4][4].isExit = true;

        return map;
    }
}