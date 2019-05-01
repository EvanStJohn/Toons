package toons;

public class Board {
    
    private int[][] board = new int[5][5];
    private int[] mountain = new int[2];
    private int winner = 0;
    private boolean bugs = true;
    private boolean tweety = true;
    private boolean taz = true;
    private int alive = 3;
    
    public Board()
    {
        
    }
    
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
    
    public boolean getBugs()
    {
        return bugs;
    }
    
    public boolean getTweety()
    {
        return tweety;
    }
    
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
