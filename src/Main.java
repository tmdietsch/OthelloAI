
public class Main {

	public static void main(String[] args) {
		
//		Board board = new Board();
//		//System.out.println(board.isValidMove(5, 2, true));
//		board.printBoard();
//		board.move(0, 2, true);
//		board.printBoard();
		
		Controller p1 = new AI(true, true, 4);
		Controller p2 = new AI(false, true, 4);
		Game othello = new Game(p1, p2);
		
		othello.play();
		
	}
	
}
