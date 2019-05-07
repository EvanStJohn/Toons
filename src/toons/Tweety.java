package toons;

import java.util.Arrays;
import java.util.Random;

public class Tweety extends Thread {
    
    Mutex lock;
    int carrot = 0; // amount of carrots being held
    int[] position = new int[2];
    Board board;
    
    Tweety(Mutex lock, Board board)
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
                while (board.getWinner() == 0 && isLiving())
                {
                    while(lock.flag != 2)
                    {
                        lock.wait();
                    }
                    
                    move();
                    board.printBoard();
                    isWinner();
                    
                    
                    Thread.sleep(1000);
                    lock.flag = 3;
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
            board.setWinner(2);
            System.out.println("Tweety is the winner");
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isLiving()
    {
        return board.getTweety();
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
                board.editSpace(2, x, y);
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
                board.editSpace(2, newX, newY);
                cont = false;
            }
            else if(valueOfLocation == 7 && carrot == 0) {
                board.editSpace(0, position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                board.editSpace(2, newX, newY);
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
