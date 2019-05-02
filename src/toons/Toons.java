package toons;

public class Toons {

    public static void main(String[] args) {
        
        Mutex lock = new Mutex();
        Board board = new Board();
        
        Bugs bugs = new Bugs(lock, board);
        Tweety tweety = new Tweety(lock, board);
        Taz taz = new Taz(lock, board);
        
        board.moveMountain();
        board.placeCarrots();
        
        bugs.giveStartingLocation();
        tweety.giveStartingLocation();
        taz.giveStartingLocation();
        
        bugs.start();
        tweety.start();
        taz.start();
    }
    
}
