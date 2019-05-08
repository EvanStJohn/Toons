package toons;

import java.util.Random;

public class Marvin extends Thread {
    
    Mutex lock;
    int carrot = 0; // amount of carrots being held
    int[] position = new int[2];
    int turn = 0;
    String currentPiece = "  M  ";
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
                while (board.getWinner() == 0)
                {
                    while(lock.flag != 4)
                    {
                        lock.wait();
                    }
                    if (board.getWinner() == 0) {
                       System.out.println("Marvin's turn");
                        move();
                        isWinner();  
                        if(turn == 3)
                        {
                            board.editSpace("  -  ", board.getMountain()[0], board.getMountain()[1]);
                            board.moveMountain();
                            turn = 0;
                        }
                        else
                        {
                            turn++;
                        }
                        board.printBoard();
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
        if (carrot == 2 || !board.playersRemaining())
        {
            board.setWinner(4);
            System.out.println("Marvin the Martian is the winner");
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
            if (board.spaceOccupiedBy(x, y) == "  -  ") {
                position[0] = x;
                position[1] = y;
                board.editSpace(currentPiece, x, y);
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
            
            String valueOfLocation = board.spaceOccupiedBy(newX, newY);
            if (valueOfLocation == "  -  ") {
                board.editSpace("  -  ", position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                board.editSpace(currentPiece, newX, newY);
                cont = false;
            }
            else if(valueOfLocation == "  C  ") {
                board.editSpace("  -  ", position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                board.editSpace(currentPiece, newX, newY);
                carrot++;
                cont = false;
            }
            
            else if (valueOfLocation == "  B  " || valueOfLocation == " B(C)") {
                board.killBugs();
                board.editSpace("  -  ", position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                if (valueOfLocation == " B(C)") {
                    carrot++;
                    currentPiece = " M(C)";
                }
                board.editSpace(currentPiece, newX, newY);
            }
            else if (valueOfLocation == "  T  " || valueOfLocation == " T(C)") {
                board.killTweety();
                board.editSpace("  -  ", position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                if (valueOfLocation == " T(C)") {
                    carrot++;
                    currentPiece = " M(C)";                    
                }
                board.editSpace(currentPiece, newX, newY);
            }
            else if (valueOfLocation == "  D  " || valueOfLocation == " D(C)") {
                board.killTaz();
                board.editSpace("  -  ", position[0], position[1]);
                position[0] = newX;
                position[1] = newY;
                if (valueOfLocation == " D(C)") {
                    carrot++;
                    currentPiece = " M(C)";                    
                }                
                board.editSpace(currentPiece, newX, newY);
            }
        }
    } 
}
