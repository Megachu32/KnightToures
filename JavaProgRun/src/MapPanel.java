import java.awt.*;
import javax.swing.*;

public class MapPanel extends JPanel {
    Tile[][] map;
    PlayerState player;

    public MapPanel(Tile[][] map, PlayerState player) {
        // mpa initialization
        this.map = map;
        //  player  initialization
        this.player = player;
        // the size of the panel
        setPreferredSize(new Dimension(500, 500)); // adjust as needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        //calling the super class method
        super.paintComponent(g);
        // size of then tiles
        int tileSize = 100;

        // giving color to the tiles
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].isBlocked) g.setColor(Color.DARK_GRAY);
                else if (map[i][j].isRuin) g.setColor(Color.RED);
                else if (map[i][j].isIce) g.setColor(Color.CYAN);
                else if (map[i][j].isPortal) g.setColor(Color.MAGENTA);
                else if (map[i][j].isPortalExit) g.setColor(Color.PINK);                
                else if (map[i][j].isKey) g.setColor(Color.YELLOW);
                else if (map[i][j].isDoor) g.setColor(Color.ORANGE);
                else if (map[i][j].isExit) g.setColor(Color.GREEN);
                else g.setColor(Color.LIGHT_GRAY);

                g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);

                // Draw enemy piece if exists
                if (map[i][j].enemy != null) {
                    g.setColor(Color.RED);
                    String pieceType = map[i][j].enemy.getClass().getSimpleName().substring(0, 1); // K, R, B, etc.
                    g.drawString(pieceType, j * tileSize + tileSize / 2, i * tileSize + tileSize / 2);
                }
            }
        }

        // Draw player
        g.setColor(Color.BLUE);
        g.fillOval(player.y * tileSize + 25, player.x * tileSize + 25, 50, 50);
    }
}
