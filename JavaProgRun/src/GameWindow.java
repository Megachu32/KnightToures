import javax.swing.*;

public class GameWindow {
    public static void launchGame() {
        Tile[][] map = MapManager.loadSampleMap();
        PlayerState player = new PlayerState(0, 0);

        JFrame frame = new JFrame("Knight Pathfinding");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MapPanel panel = new MapPanel(map, player);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);

        // Start pathfinding in a separate thread to prevent UI freezing
        new Thread(() -> {
            Pathfinder.findPathWithVisual(player, map, panel);
        }).start();
    }
}
