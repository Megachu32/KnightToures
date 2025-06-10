public class Pathfinder {
    static boolean[][] visited;

    // knight movement need to be dynamic
    static int[][] knightMoves = {
        {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
        {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };
    // to check and visualize the pathfinding of plauer? need to be able to go to the same path to actualy comoplet anyhting
    public static boolean findPathWithVisual(PlayerState player, Tile[][] map, MapPanel panel) {
        int rows = map.length;
        int cols = map[0].length;
        visited = new boolean[rows][cols];
        return dfs(player, map, panel);
    }
    // deep fith first search method
    private static boolean dfs(PlayerState player, Tile[][] map, MapPanel panel) {
        int x = player.x, y = player.y;

        // check if the current position is out of bounds, blocked, or already visited
        if (outOfBounds(x, y, map) || map[x][y].isBlocked || visited[x][y]) {
            return false;
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
            PlayerState next = player.clone();
            next.x += move[0];
            next.y += move[1];

            if (dfs(next, map, panel)) {
                // update original player position to show path
                player.x = next.x;
                player.y = next.y;
                panel.repaint();
                return true;
            }
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
}
