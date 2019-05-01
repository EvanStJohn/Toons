package toons;

public class Marvin extends Thread {
    
    Mutex lock;
    int carrot = 0; // amount of carrots being held
    int[] position;
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
        int x = r.nextInt(4);
        int y = r.nextInt(4);
        boolean cont = true;
        while(cont) {
            if (board.spaceOccupiedBy(x, y) == 0) {
                position[0] = x;
                position[1] = y;
                board.editSpace(4, x, y);
                cont = false;
            }
        }
    }
}
