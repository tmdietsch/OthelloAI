
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
		boolean skippedLastPlayer = false;
		board.printBoard();
		
		boolean done = board.isFull();
		while(!done) {
			if(board.getNumValidMoves() != 0) {
				if(p1Turn) {
					System.out.println("Current player turn: X");
					player1.makeMove(board);
				}
				else {
					System.out.println("Current player turn: O");
					player2.makeMove(board);
				}
				System.out.println("\n");
				board.printBoard();
				p1Turn = !p1Turn;
				done = board.isFull();
				skippedLastPlayer = false;
			}
			else {
				if(!skippedLastPlayer) {
					if(p1Turn) {
						System.out.println("Player X has no avalible moves. Skipping turn.");
					}
					else {
						System.out.println("Player O has no avalible moves. Skipping turn.");
						
					}
					skippedLastPlayer = true;
					board.skipMove(p1Turn);
					p1Turn = !p1Turn;
				}
				else {
					System.out.println("No more moves avalible. Ending game.");
					done = true;
					
				}
			}
		}
		endGame();
		
	}

	
	public void endGame() {
		if(board.getNumWhite() > board.getNumBlack()) {
			System.out.println("White wins!");
		}
		else if(board.getNumBlack() > board.getNumWhite()) {
			System.out.println("Black wins!");
		}
		else {
			System.out.println("Tie!");
		}
	}
}
