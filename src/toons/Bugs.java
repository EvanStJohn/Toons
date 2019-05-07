package toons;

import java.util.Arrays;
import java.util.Random;

public class Bugs extends Thread {
     
    Mutex lock;
    int carrot = 0; // amount of carrots being held
    int[] position = new int[2];
    Board board;
    
    Bugs(Mutex lock, Board board)
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
                    while(lock.flag != 1)
                    {
                        lock.wait();
                    }
                    
                    move();
                    board.printBoard();   
                      
                    Thread.sleep(1000);
                    lock.flag = 2;
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
        if (Arrays.equals(position, board.getMountain()))
        {
            board.setWinner(1);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isLiving()
    {
        return board.getBugs();
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
                board.editSpace(1, x, y);
                cont = false;
            }
        }
    }
         
    public void move()
    {
        Random rand = new Random();
        boolean cont = true;
        while(cont) {
            int vertOrhoriz = rand.nextInt(2);
            int newX;
            int newY;
            if (vertOrhoriz == 0) {
                newX = position[0] + (rand.nextInt(3) - 1);
                newY = position[1];
            }
            else {
                newX = position[0];
                newY = position[1] + (rand.nextInt(3) - 1);  
            }
            
            int valueOfLocation = board.spaceOccupiedBy(newX, newY);
            if (valueOfLocation == 0) {
                board.editSpace(0, position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                board.editSpace(1, newX, newY);
                cont = false;
            }
            else if(valueOfLocation == 7 && carrot == 0) {
                board.editSpace(0, position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                board.editSpace(1, newX, newY);
                carrot++;
                cont = false;
            }
            else if(valueOfLocation == 8 && carrot == 1) {
                board.editSpace(0, position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                cont = false;
            }
        }
    } 

    
}
