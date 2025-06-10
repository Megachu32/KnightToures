public abstract  class Enemy {
    // TODO Future: make the properties for the enemy
    // exmaple king move, queen move, rook move, bishop move, knight move, pawn move
    // spawn randomly in the map


    // initiralize the enemy with a position
    public int x, y;
    // ememy thihng like posititon ect
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }
    //calling a method to kill the player
     public abstract boolean canKill(PlayerState player,Tile[][] map);


}
