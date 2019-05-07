package toons;

import java.util.Random;

public class Marvin extends Thread {
    
    Mutex lock;
    int carrot = 0; // amount of carrots being held
    int[] position = new int[2];
    int turn = 0;
    Board board;
    
    Marvin(Mutex lock, Board board)
    {
        this.lock = lock;
        this.board = board;
    }
    
    @Override
    public void run()
    {
        try
        {
            synchronized(lock)
            {
                while (!isWinner())
                {
                    while(lock.flag != 4)
                    {
                        lock.wait();
                    }
                    
                    // add code below
                    
                    if(turn == 3)
                    {
                        board.editSpace(0, board.getMountain()[0], board.getMountain()[1]);
                        board.moveMountain();
                        turn = 0;
                    }
                    else
                    {
                        turn++;
                    }
                    
                    Thread.sleep(1000);
                    lock.flag = 1;
                    lock.notifyAll();
                }
            }
        }
        catch (Exception e)
        {
            
        }
    }
    
    public boolean hasCarrot()
    {
        if (carrot > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isWinner()
    {
        if (carrot == 2 || !board.isLiving())
        {
            board.setWinner(4);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void giveStartingLocation()
    {
        Random r = new Random();
        boolean cont = true;
        while(cont) {
            int x = r.nextInt(5);
            int y = r.nextInt(5);
            if (board.spaceOccupiedBy(x, y) == 0) {
                position[0] = x;
                position[1] = y;
                board.editSpace(4, x, y);
                cont = false;
            }
        }
    }
}
