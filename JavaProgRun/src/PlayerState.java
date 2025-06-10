import java.util.HashSet;
import java.util.Set;

public class PlayerState {
    int x, y;
    int keysCollected = 0;
    // saving the condition of tiles and player actions
    Set<String> flags = new HashSet<>(); // like "door1_opened", "portalA_used"

    // Constructor to initialize player position
    public PlayerState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Clone method for backtracking
    public PlayerState clone() {
        PlayerState copy = new PlayerState(this.x, this.y);
        copy.keysCollected = this.keysCollected;
        copy.flags = new HashSet<>(this.flags);
        return copy;
    }
}