package toons;

public class Board {
    
    private int[][] board = new int[5][5];
    private int[] mountain = new int[2];
    private int[][] carrot = new int[2][2];
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
    
    // get position of carrots
    public int[][] getCarrot()
    {
        return carrot;
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
    
    public void moveMountains()
    {
        Random r = new Random();
        
        boolean cont = true;
        while(cont) {
            int x = r.nextInt(4);
            int y = r.nextInt(4);
            
            if (spaceOccupiedBy(x, y) == 0) {
                mountain[0] = x;
                mountain[1] = y;
                board[x][y] = 8;
                cont = false;
            }
        }

    }
    
    public void placeCarrots()
    {
        Random r = new Random();
        
        int c = 0;
        while(c < 2) {
            int x = r.nextInt(4);
            int y = r.nextInt(4);
            
            if (spaceOccupiedBy(x, y) == 0) {
                carrot[c][0] = x;
                carrot[c][1] = y;
                board[x][y] = 7;
                
                c++;
            }
        }

    }
    
    public void printBoard()
    {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
