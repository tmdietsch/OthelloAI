
public class Main {

	public static void main(String[] args) {
		
//		Board board = new Board();
//		//System.out.println(board.isValidMove(5, 2, true));
//		board.printBoard();
//		board.move(0, 2, true);
//		board.printBoard();
		
		Controller p1 = new Player(true);
		Controller p2 = new AI(false, false);
		Game othello = new Game(p1, p2);
		
		othello.play();
		
	}
	
}
