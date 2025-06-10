public class Bishop extends Enemy{

    public Bishop(int x, int y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }

    // Method to check if a position is out of bounds
    

     // movement for enemy bishop
    public static final int[][] bishopMove = {
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };
    @Override
    public boolean canKill(PlayerState player, Tile[][] map) {
        for(int[] move : bishopMove){
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
    private boolean outOfBounds(int x, int y, Tile[][] map) {
        return x < 0 || y < 0 || x >= map.length || y >= map[0].length;
    }
}
