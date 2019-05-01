package toons;

public class Board {
    
    private int[][] board = new int[5][5];
    private int[] mountain = new int[2];
    private int winner = 0; // id of the winning toon
    private boolean bugs = true; // is bugs alive
    private boolean tweety = true; // is tweety alive
    private boolean taz = true; // is taz alive
    private int alive = 3; // how many toons are alive
    
    public Board()
    {
        
    }
    
    // get position of the mountain
    public int[] getMountain()
    {
        return mountain;
    }
    
    public void killBugs()
    {
        bugs = false;
    }
    
    public void killTweety()
    {
        tweety = false;
    }
    
    public void killTaz()
    {
        taz = false;
    }
    
    // check if bugs is alive
    public boolean getBugs()
    {
        return bugs;
    }
    
    // check if bugs is alive
    public boolean getTweety()
    {
        return tweety;
    }
    
    // check if bugs is alive
    public boolean getTaz()
    {
        return taz;
    }
    
    public int spaceOccupiedBy(int x, int y)
    {
        return board[x][y];
    }
    
    public void editSpace(int newID, int x, int y)
    {
        board[x][y] = newID;
    }
    
    // check if there toons to keep the game running
    public boolean isLiving()
    {
        if (alive > 0)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}
