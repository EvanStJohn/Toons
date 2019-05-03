package toons;

import java.util.Random;

public class Taz extends Thread {
    
    Mutex lock;
    int carrot = 0; // amount of carrots being held
    int[] position = new int[2];
    Board board;
    
    Taz(Mutex lock, Board board)
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
                while (!isWinner() && isLiving())
                {
                    while(lock.flag != 3)
                    {
                        lock.wait();
                    }
                    
                    // add code below
                    
                    
                    
                    Thread.sleep(1000);
                    lock.flag = 4;
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
        if (position.equals(board.getMountain()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isLiving()
    {
        return board.getTaz();
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
                board.editSpace(3, x, y);
                cont = false;
            }
        }
    }
    
    public void move()
    {
        Random rand = new Random();
        boolean cont = true;
        while(cont) {
            int newX = position[0] + (rand.nextInt(2) - 1);
            int newY = position[1] + (rand.nextInt(2) - 1);
            
            int valueOfLocation = board.spaceOccupiedBy(newX, newY);
            if (valueOfLocation == 0) {
                board.editSpace(0, position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                board.editSpace(3, newX, newY);
                cont = false;
            }
            else if(valueOfLocation == 7) {
                if (carrot == 0) {
                    board.editSpace(0, position[0], position[1]);
                    position[0] = newX;
                    position[1] = newY;
                    board.editSpace(3, newX, newY);
                    carrot++;
                    cont = false;
                }
            }
            else if(valueOfLocation == 8) {
                if (carrot == 1) {
                    board.editSpace(0, position[0], position[1]);
                    position[0] = newX;
                    position[1] = newY;
                    isWinner(); //Check to see if winner. Add winning functionality here.
                    cont = false;
                }
            }
        }
    }
}
