public class King extends Enemy{

    public King(int x, int y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }
    // movemnt for enemy king
    public static final int[][] kingMove = {
        {-1, -1},{-1, 0},{-1, 1},
        {0,-1},         {0,1},
        {1, -1},{1, 0},{1, 1}
    };

    @Override
    public boolean canKill(PlayerState player, Tile[][] map) {
        for(int[] move : kingMove){
            int nx = x + move[0];
            int ny = y + move[1];
            // check if the next position is within bounds and matches the player's position
            if(!outOfBounds(nx, ny, map) && nx == player.x && ny == player.y) {
                return true;
            }
        }
        return false;
    }

    // Method to check if a position is out of bounds
    private boolean outOfBounds(int x, int y, Tile[][] map) {
        return x < 0 || y < 0 || x >= map.length || y >= map[0].length;
    }
    
}
