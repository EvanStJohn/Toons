package toons;

public class Toons {

    public static void main(String[] args) {
        
        Mutex lock = new Mutex();
        Board board = new Board();
        
        Bugs bugs = new Bugs(lock, board);
        Tweety tweety = new Tweety(lock, board);
        Taz taz = new Taz(lock, board);
        Marvin marvin = new Marvin(lock, board);
        
        board.moveMountain();
        board.placeCarrots();
        
        bugs.giveStartingLocation();
        tweety.giveStartingLocation();
        taz.giveStartingLocation();
        marvin.giveStartingLocation();
        
        board.printBoard();
        
        bugs.start();
        tweety.start();
        taz.start();
        marvin.start();
    }
    
}
