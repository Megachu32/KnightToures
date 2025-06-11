import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MapPanel extends JPanel {
    Tile[][] map;
    PlayerState player;
    BufferedImage playerImage;
    BufferedImage kingImage;
    BufferedImage queenImage;
    BufferedImage rookImage;
    BufferedImage bishopImage;
    BufferedImage pawnImage;

    public MapPanel(Tile[][] map, PlayerState player) {
        // mpa initialization
        this.map = map;
        //  player  initialization
        this.player = player;
        // the size of the panel
        setPreferredSize(new Dimension(500, 500)); // adjust as needed
        setImages();
    }

    public void setImages(){
        // Load player image
        try {
            playerImage = ImageIO.read(getClass().getResource("/images/knights.jpg"));
            kingImage = ImageIO.read(getClass().getResource("/images/king.jpg"));
            queenImage = ImageIO.read(getClass().getResource("/images/queen.jpg"));
            rookImage = ImageIO.read(getClass().getResource("/images/rook.jpg"));
            bishopImage = ImageIO.read(getClass().getResource("/images/bishop.jpg"));
            pawnImage = ImageIO.read(getClass().getResource("/images/pawn.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(playerImage == null || kingImage == null || queenImage == null ||
           rookImage == null || bishopImage == null || pawnImage == null) {
            System.err.println("Error loading images. Please check the file paths.");
        }
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
                if (map[i][j].isRuin) g.setColor(Color.RED);
                else if (map[i][j].isBlocked) g.setColor(Color.DARK_GRAY);
                else if (map[i][j].isIce) g.setColor(Color.CYAN);
                else if (map[i][j].isPortal) g.setColor(Color.MAGENTA);
                else if (map[i][j].isPortalExit) g.setColor(Color.PINK);                
                else if (map[i][j].isKey) g.setColor(Color.YELLOW);
                else if (map[i][j].isDoor) g.setColor(Color.ORANGE);
                else if (map[i][j].isExit) g.setColor(Color.GREEN);
                else if (map[i][j].hasVisited) g.setColor(Color.BLACK);
                else g.setColor(Color.LIGHT_GRAY);

                g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);

                // Draw enemy piece if exists
                BufferedImage pieceImage = null;
                if (map[i][j].enemy != null) {
                    g.setColor(Color.RED);
                    String pieceType = map[i][j].enemy.getClass().getSimpleName().substring(0, 1); // K, R, B, etc.
                    switch (pieceType) {
                        case "K":
                            pieceImage = kingImage;
                            break;
                        case "Q":
                            pieceImage = queenImage;
                            break;
                        case "R":
                            pieceImage = rookImage;
                            break;
                        case "B":
                            pieceImage = bishopImage;
                            break;
                        case "P":
                            pieceImage = pawnImage;
                            break;
                    }
                    g.drawImage(pieceImage, j * tileSize, i * tileSize, tileSize, tileSize, null);
                }
            }
        }
        
        // Draw player
        g.drawImage(playerImage, player.y * tileSize, player.x * tileSize, tileSize, tileSize, null);

    }
}
