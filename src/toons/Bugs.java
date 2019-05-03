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
        boolean cont = true;
        int count;
        int mountain[] = board.getMountain();
        
        while (cont)
        {
            count = 0;
            Random r = new Random();
            int num = r.nextInt(8);
            
            if (num == 0)
            {
                // up
                if (position[0] == 0)
                {
                    count++;
                }
                
                if (board.spaceOccupiedBy(position[0] - 1, position[1]) < 5 && board.spaceOccupiedBy(position[0] - 1, position[1]) > 0)
                {
                    count++;
                }
                
                if (mountain[0] == position[0] - 1 && mountain[1] == position[1])
                {
                    if (carrot < 1)
                    {
                        count++;
                    }
                }
                
                if (count == 0)
                {
                    board.editSpace(0, position[0], position[1]);
                    position[0] =- 1;
                    board.editSpace(1, position[0], position[1]);

                    if (Arrays.equals(position, board.getCarrot()[0]) || Arrays.equals(position, board.getCarrot()[1]))
                    {
                        carrot += 1;
                    }
                    

                    cont = false;
                    
                }
                
            }
            
            if (num == 1)
            {
                // down
                if (position[0] == 4)
                {
                    count++;
                }

                if (board.spaceOccupiedBy(position[0] + 1, position[1]) < 5 && board.spaceOccupiedBy(position[0] + 1, position[1]) > 0)
                {
                    count++;
                }

                if (mountain[0] == position[0] - 1 && mountain[1] == position[1])
                {
                    if (carrot < 1)
                    {
                        count++;
                    }
                }

                if (count == 0)
                {
                    board.editSpace(0, position[0], position[1]);
                    position[0] =+ 1;
                    board.editSpace(1, position[0], position[1]);

                    if (Arrays.equals(position, board.getCarrot()[0]) || Arrays.equals(position, board.getCarrot()[1]))
                    {
                        carrot += 1;
                    }

                    cont = false;
                }  
            }

            if (num == 2)
            {
                // left
                if (position[1] == 0)
                {
                    count++;
                }

                if (board.spaceOccupiedBy(position[0], position[1] - 1) < 5 && board.spaceOccupiedBy(position[0], position[1] - 1) > 0)
                {
                    count++;
                }

                if (mountain[0] == position[0] - 1 && mountain[1] == position[1])
                {
                    if (carrot < 1)
                    {
                        count++;
                    }
                }
                
                if (count == 0)
                {
                    board.editSpace(0, position[0], position[1]);
                    position[1] =- 1;
                    board.editSpace(1, position[0], position[1]);
                    
                    if (Arrays.equals(position, board.getCarrot()[0]) || Arrays.equals(position, board.getCarrot()[1]))
                    {
                        carrot += 1;
                    }
                    
                    cont = false;
                }
            }

            if (num == 3)
            {
                // right
                if (position[1] == 4)
                {
                    count++;
                }

                if (board.spaceOccupiedBy(position[0], position[1] + 1) < 5 && board.spaceOccupiedBy(position[0], position[1] + 1) > 0)
                {
                    count++;
                }

                if (mountain[0] == position[0] - 1 && mountain[1] == position[1])
                {
                    if (carrot < 1)
                    {
                        count++;
                    }
                }
                
                if (count == 0)
                {
                    board.editSpace(0, position[0], position[1]);
                    position[1] =+ 1;
                    board.editSpace(1, position[0], position[1]);
                    
                    if (Arrays.equals(position, board.getCarrot()[0]) || Arrays.equals(position, board.getCarrot()[1]))
                    {
                        carrot += 1;
                    }
                    
                    cont = false;
                }
            }

            if (num == 4)
            {
                // up, left
                if (position[0] == 0 || position[1] == 0)
                {
                    count++;
                }

                if (board.spaceOccupiedBy(position[0] - 1, position[1] - 1) < 5 && board.spaceOccupiedBy(position[0] - 1, position[1] - 1) > 0)
                {
                    count++;
                }

                if (mountain[0] == position[0] - 1 && mountain[1] == position[1])
                {
                    if (carrot < 1)
                    {
                        count++;
                    }
                }
                
                if (count == 0)
                {
                    board.editSpace(0, position[0], position[1]);
                    position[0] =- 1;
                    position[1] =- 1;
                    board.editSpace(1, position[0], position[1]);
                    
                    if (Arrays.equals(position, board.getCarrot()[0]) || Arrays.equals(position, board.getCarrot()[1]))
                    {
                        carrot += 1;
                    }
                    
                    cont = false;
                }
            }

            if (num == 5)
            {
                // up, right
                if (position[0] == 0 || position[1] == 4)
                {
                    count++;
                }

                if (board.spaceOccupiedBy(position[0] - 1, position[1] + 1) < 5 && board.spaceOccupiedBy(position[0] - 1, position[1] + 1) > 0)
                {
                    count++;
                }

                if (mountain[0] == position[0] - 1 && mountain[1] == position[1])
                {
                    if (carrot < 1)
                    {
                        count++;
                    }
                }
                
                if (count == 0)
                {
                    board.editSpace(0, position[0], position[1]);
                    position[0] =- 1;
                    position[1] =+ 1;
                    board.editSpace(1, position[0], position[1]);
                    
                    if (Arrays.equals(position, board.getCarrot()[0]) || Arrays.equals(position, board.getCarrot()[1]))
                    {
                        carrot += 1;
                    }
                    
                    cont = false;
                }
            }

            if (num == 6)
            {
                //down, left
                if (position[0] == 4 || position[1] == 0)
                {
                    count++;
                }

                if (board.spaceOccupiedBy(position[0] + 1, position[1] - 1) < 5 && board.spaceOccupiedBy(position[0] + 1, position[1] - 1) > 0)
                {
                    count++;
                }

                if (mountain[0] == position[0] - 1 && mountain[1] == position[1])
                {
                    if (carrot < 1)
                    {
                        count++;
                    }
                }
                
                if (count == 0)
                {
                    board.editSpace(0, position[0], position[1]);
                    position[0] =+ 1;
                    position[1] =- 1;
                    board.editSpace(1, position[0], position[1]);
                    
                    if (Arrays.equals(position, board.getCarrot()[0]) || Arrays.equals(position, board.getCarrot()[1]))
                    {
                        carrot += 1;
                    }
                    
                    cont = false;
                }
            }
            
            if (num == 7)
            {
                // down, right
                if (position[0] == 4 || position[1] == 4)
                {
                    count++;
                }

                if (board.spaceOccupiedBy(position[0] + 1, position[1] + 1) < 5 && board.spaceOccupiedBy(position[0] + 1, position[1] + 1) > 0)
                {
                    count++;
                }

                if (mountain[0] == position[0] - 1 && mountain[1] == position[1])
                {
                    if (carrot < 1)
                    {
                        count++;
                    }
                }
                    
                if (count == 0)
                {
                    board.editSpace(0, position[0], position[1]);
                    position[0] =+ 1;
                    position[1] =+ 1;
                    board.editSpace(1, position[0], position[1]);
                    
                    if (Arrays.equals(position, board.getCarrot()[0]) || Arrays.equals(position, board.getCarrot()[1]))
                    {
                        carrot += 1;
                    }
                    
                    cont = false;
                }
            }
        }    
    }
}
