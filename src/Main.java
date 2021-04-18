
public class Main {

	public static void main(String[] args) {
		
		Board board = new Board();
		//System.out.println(board.isValidMove(5, 2, true));
		board.printBoard();
		board.move(0, 2, true);
		board.printBoard();
		
	}
	
}
