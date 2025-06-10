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
        map[4][4].isExit = true;
        map[1][1].isRuin = true;
        map[1][1].isBlocked = true;
        // map[2][2].enemy = new Rook(2, 2); // placing a Rook enemy at (2, 2)

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