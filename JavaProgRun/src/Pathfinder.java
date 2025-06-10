
import java.util.List;

public class Pathfinder {
    static boolean[][] visited;

    // knight movement need to be dynamic
    static int[][] knightMoves = {
        {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
        {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };
    // to check and visualize the pathfinding of plauer? need to be able to go to the same path to actualy comoplet anyhting
    public static boolean findPathWithVisual(PlayerState player, Tile[][] map, MapPanel panel, List<Enemy> enemies) {
        int rows = map.length;
        int cols = map[0].length;
        visited = new boolean[rows][cols];
        return dfs(player, map, panel, enemies);
    }
    // deep fith first search method
    private static boolean dfs(PlayerState player, Tile[][] map, MapPanel panel,List<Enemy> enemies) {
        int x = player.x, y = player.y;

        // check if the current position is out of bounds, blocked, or already visited
        if (outOfBounds(x, y, map) || map[x][y].isBlocked || visited[x][y]) {
            return false;
        }
        for(Enemy enemy : enemies) {
            // check if the enemy can kill the player
            if (enemy.canKill(player, map)) {
                return false; // player is killed by an enemy
            }
        }
        // if player reaxhes the exit tile
        if (map[x][y].isExit) {
            panel.repaint();
            return true;
        }

        visited[x][y] = true;// mark current position as visited

        panel.repaint(); // repaint to show current position
        sleep(1000); // delay to visualize movement

        for (int[] move : knightMoves) {
            int nextX = player.x + move[0];
            int nextY = player.y + move[1];

            int prevX = player.x, prevY = player.y;

            player.x = nextX;
            player.y = nextY;

            //System.out.println("DestroyRuinGotCalled"); //the spam on console should be fine as it's only checking for the are within 3x3 of the ruins.
            destroyAdjacentRuins(player, map);

            if (dfs(player, map, panel, enemies)) {
                return true;
            }

            // backtrack
            player.x = prevX;
            player.y = prevY;
        }

        visited[x][y] = false; // backtrack
        panel.repaint();// calling repaint to show backtracking
        return false; // no path found
    }

    // sleep method
    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
    
    // bound fot the map
    private static boolean outOfBounds(int x, int y, Tile[][] map) {
        return x < 0 || y < 0 || x >= map.length || y >= map[0].length;
    }

    private static void destroyAdjacentRuins(PlayerState player, Tile[][] map) { /* Logic for Ruin Destruction */
    int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}, // cardinal
        {1, 1}, {-1, -1}, {1, -1}, {-1, 1} // diagonals
    };

    for (int[] dir : directions) {
        int nx = player.x + dir[0];
        int ny = player.y + dir[1];

        if (!outOfBounds(nx, ny, map) && map[nx][ny].isRuin) {
            map[nx][ny].isRuin = false;       // mark as destroyed
            map[nx][ny].isBlocked = false;    // optionally unblock it
            // You could also change tile type or trigger animation
        }
    }
}

    
}
