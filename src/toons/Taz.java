package toons;

public class Taz extends Thread {
    
    Mutex lock;
    int carrot = 0; // amount of carrots being held
    int[] position;
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
}
