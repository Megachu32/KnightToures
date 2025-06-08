import java.awt.*;
import javax.swing.*;

public class MapPanel extends JPanel {
    Tile[][] map;
    PlayerState player;

    public MapPanel(Tile[][] map, PlayerState player) {
        this.map = map;
        this.player = player;
        setPreferredSize(new Dimension(500, 500)); // adjust as needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = 100;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].isBlocked) g.setColor(Color.DARK_GRAY);
                else if (map[i][j].isExit) g.setColor(Color.GREEN);
                else g.setColor(Color.LIGHT_GRAY);

                g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }

        // Draw player
        g.setColor(Color.BLUE);
        g.fillOval(player.y * tileSize + 25, player.x * tileSize + 25, 50, 50);
    }
}
