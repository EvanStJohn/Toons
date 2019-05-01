package toons;

public class Tweety extends Thread {
    
    Mutex lock;
    int carrot = 0; // amount of carrots being held
    int[] position;
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
                while (!isWinner() && isLiving())
                {
                    while(lock.flag != 2)
                    {
                        lock.wait();
                    }
                    
                    // add code below
                    
                    
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
        return board.getTweety();
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
                board.editSpace(2, x, y);
                cont = false;
            }
        }
    }
}
