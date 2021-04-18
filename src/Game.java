
public class Game {
	
	private Controller player1;
	private Controller player2;
	private Board board;
	
	public Game(Controller p1, Controller p2) {
		player1 = p1;
		player2 = p2;
		board = new Board();		
	}
	
	public void play() {
		boolean p1Turn = true;
		board.printBoard();
		
		boolean notDone = board.isFull();
		while(notDone) {
			if(p1Turn) {
				player1.makeMove();
			}
			else {
				player2.makeMove();
			}
			System.out.println("\n");
			board.printBoard();
			p1Turn = !p1Turn;
			notDone = board.isFull();
		}
	}
	
}
