
public class Queen extends Enemy{

    public Queen(int x, int y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }

    
    // enemy movement for queem
    public static final int[][] queenMove = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},      // Rook directions
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}     // Bishop directions
    };

    @Override
    public boolean canKill(PlayerState player, Tile[][] map) {
        for(int[] move : queenMove){
            int nx = x + move[0];
            int ny = y + move[1];
            while(!outOfBounds(nx,ny ,map) && map[nx][ny].isBlocked == false) {
                // check if the next position is within bounds and matches the player's position
                if(nx == player.x && ny == player.y) {
                    return true;
                }
                // move in the same direction
                nx += move[0];
                ny += move[1];
            }
        }
        return false;
    }

    // Method to check if a position is out of bounds
    private boolean outOfBounds(int x, int y, Tile[][] map) {
        return x < 0 || y < 0 || x >= map.length || y >= map[0].length;
    }
    
}
