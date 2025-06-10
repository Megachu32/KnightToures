import java.util.ArrayList;
import java.util.List;

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
        map[0][0].isKey = true; // setting a key tile
        map[2][1].isDoor = true;
        map[4][4].isExit = true;
        map[2][2].isPortal = true; // setting a portal tile
        map[3][2].isPortalExit = true; // setting a portal exit tile

        map[0][4].enemy = new King(4, 0); // placing a King enemy at (0, 4)
        // map[1][1].enemy = new Rook(1, 1); // placing a Rook enemy at (1, 1
        map[4][0].enemy = new Bishop(0, 4); // placing a Bishop enemy at (2, 2)
        // map[3][3].enemy = new Queen(3, 3); // placing a Queen enemy at (3, 3)
        // returning the map?
        return map;
    }
    public static List<Enemy> getEnemies(Tile[][] map) {
    List<Enemy> enemies = new ArrayList<>();
    for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[0].length; j++) {
            if (map[i][j].enemy != null) {
                enemies.add(map[i][j].enemy);
            }
        }
    }
    return enemies;
}

}