
import java.util.List;

import javax.swing.SwingUtilities;

public class Pathfinder {
    static int prevX, prevY;

    // knight movement need to be dynamic
    static int[][] knightMoves = {
        {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
        {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };
    // to check and visualize the pathfinding of plauer? need to be able to go to the same path to actualy comoplet anyhting
    public static boolean findPathWithVisual(PlayerState player, Tile[][] map, MapPanel panel, List<Enemy> enemies) {
        int rows = map.length;
        int cols = map[0].length;
        return dfs(player, map, panel, enemies);
    }

    private static boolean checkingForDfs(PlayerState player, Tile[][] map, MapPanel panel,List<Enemy> enemies){
        int x = player.x, y = player.y;
        if (outOfBounds(x, y, map)) {
            return false;
        }
        if (map[x][y].isBlocked || map[x][y].hasVisited == true) {
            System.out.println("visited at: (" + x + ", " + y + ")");
            return false;
        }
        if (map[x][y].isExit) {
            // panel.repaint();
            return true;
        }
        if(map[x][y].isPortal == true) {
            // if the next tile is a portal, teleport the player to the exit
            System.out.println("Teleporting through portal at: " + x + ", " + y);
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j].isPortalExit) {
                        x = i;
                        y = j;
                        System.out.println("Teleporting to exit at: " + i + ", " + j);
                        player.x = x;
                        player.y = y;
                        break;
                    }
                }
            }
        }
        if(map[x][y].isDoor && player.keysCollected != 0){
            System.out.println("Opening door at: " + x + ", " + y);
        }
        else if(map[x][y].isDoor && player.keysCollected == 0) {
            System.out.println("Cannot open door at: " + x + ", " + y + " - no keys available");
            return false; // cannot open door without keys
        }
        if(map[x][y].isKey) {
            player.keysCollected++; // collect the key
            System.out.println("Collected key at: " + x + ", " + y + ". Total keys: " + player.keysCollected);
        }
        for(Enemy enemy : enemies) {
                System.out.println("it's checking");
                // check if the enemy can kill the player
                if (enemy.canKill(player, map)) {
                    System.out.println("Player killed by enemy at (" + enemy.x + ", " + enemy.y + ")");
                    
                    player.x = prevX;
                    player.y = prevY;
                    sleep(1000);
                    panel.repaint(); // request repaint
                    panel.paintImmediately(panel.getBounds()); // 🔥 force repaint NOW
                    
                    sleep(1000);
                    return false; // player is killed, cannot continue
                }

        }
        return true;
    }
    // deep fith first search method
    private static boolean dfs(PlayerState player, Tile[][] map, MapPanel panel,List<Enemy> enemies) {
        sleep(1000);
        int x = player.x, y = player.y;


        panel.repaint(); // repaint to show current position

        map[x][y].hasVisited = true;// mark current position as visited
        
        panel.repaint(); // repaint to show current position
        sleep(1000); // delay to visualize movement

        for (int[] move : knightMoves) {
            System.out.println("Current Position dfs: (" + player.x + ", " + player.y + ")");
            prevX = player.x;
            prevY = player.y;

            applyIceSlide(player, map, prevX, prevY);
            destroyAdjacentRuins(player, map);

            if(!outOfBounds(player.x + move[0], player.y + move[1], map)) {
                System.out.println("not out of bounds: (" + player.x + ", " + player.y + ")");
                player.x += move[0];
                player.y += move[1];
            }
                panel.repaint(); // repaint to show new position
                sleep(1000); // delay to visualize movement
                if (checkingForDfs(player, map, panel, enemies)) {
                    System.out.println("continue to: (" + player.x + ", " + player.y + ")");
                    map[player.x][player.y].hasVisited = true; // mark the new position as visited
                    if (map[player.x][player.y].isExit) {
                        panel.repaint();
                        return true;
                    }
                }
                else{
                    System.out.println("Backtracking from: (" + player.x + ", " + player.y + ")");
                    System.out.println("Previous Position: (" + prevX + ", " + prevY + ")");
                
                    player.x = prevX;
                    player.y = prevY;
                    panel.repaint(); // repaint to show backtracking
                }
        }
        System.out.println("done out of moves at: (" + player.x + ", " + player.y + ")");
        if (dfs(player, map, panel, enemies)) {
            System.out.println("Path found to exit at: (" + player.x + ", " + player.y + ")");
            return true;
        }
        return false;
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

private static void applyIceSlide(PlayerState player, Tile[][] map, int fromX, int fromY) {
     if (outOfBounds(player.x, player.y, map)) return;

    if (!map[player.x][player.y].isIce) return;

    int dx = player.x - fromX;
    int dy = player.y - fromY;

    int slideX = player.x + dx;
    int slideY = player.y + dy;

    if (!outOfBounds(slideX, slideY, map) && !map[slideX][slideY].isBlocked) {
        player.x = slideX;
        player.y = slideY;
    }
}


    
}
