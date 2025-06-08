
import javax.swing.JFrame;

public class Visualizer {
    public static void main(String[] args) {
        Tile[][] map = MapManager.loadSampleMap();
        PlayerState player = new PlayerState(0, 0);

        JFrame frame = new JFrame("Knight Pathfinding");
        MapPanel panel = new MapPanel(map, player);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Now call a modified Pathfinder that supports animation
        Pathfinder.findPathWithVisual(player, map, panel);
    }
}
