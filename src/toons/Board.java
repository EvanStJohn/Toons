package toons;

import java.util.Random;

public class Board {
    
    private String[][] board = new String[5][5];
    private int[] mountain = new int[2];
    private int[][] carrot = new int[2][2];
    private int winner = 0; // id of the winning toon
    private boolean bugs = true; // is bugs alive
    private boolean tweety = true; // is tweety alive
    private boolean taz = true; // is taz alive
    private int alive = 3; // how many toons are alive
    
    public Board()
    {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "  -  ";
            }
        }
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
        System.out.println("Bugs has been killed");
        alive--;
        bugs = false;
    }
    
    public void killTweety()
    {
        System.out.println("Tweety has been killed");
        alive--;
        tweety = false;
    }
    
    public void killTaz()
    {
        System.out.println("Taz has been killed");
        alive--;
        taz = false;
    }
    
    public void setWinner(int num)
    {
        winner = num;
    }
    
    public int getWinner()
    {
        return winner;
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
    
    public String spaceOccupiedBy(int x, int y)
    {
        if (x < 0 || x > 4) {
            return "Out of Bounds";
        }
        else if (y < 0 || y > 4) {
            return "Out of Bounds";
        }
        else {
            return board[x][y];
        }
    }
    
    public void editSpace(String newID, int x, int y)
    {
        board[x][y] = newID;
    }
    
    // check if there toons to keep the game running
    public boolean playersRemaining()
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
    
    public void moveMountain()
    {
        Random r = new Random();
        
        boolean cont = true;
        while(cont) {
            int x = r.nextInt(4);
            int y = r.nextInt(4);
            
            if (spaceOccupiedBy(x, y) == "  -  ") {
                mountain[0] = x;
                mountain[1] = y;
                board[x][y] = "  F  ";
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
            
            if (spaceOccupiedBy(x, y) == "  -  ") {
                carrot[c][0] = x;
                carrot[c][1] = y;
                board[x][y] = "  C  ";
                
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
        System.out.println("=======================");
    }    
}
