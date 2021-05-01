
public class Game {
	
	private Controller player1;
	private Controller player2;
	private Board board;
	
	public Game(Controller p1, Controller p2) {
		player1 = p1;
		player2 = p2;
		board = new Board();		
	}
	
	public int play(boolean printProgress) {
		boolean p1Turn = true;
		boolean skippedLastPlayer = false;
		
		if (printProgress)
			board.printBoard();
		
		boolean done = board.isFull();
		while(!done) {
			if(board.getNumValidMoves() != 0) {
				if(p1Turn) {
					if (printProgress)
						System.out.println("Current player turn: X");
					
					player1.makeMove(board);
				}
				else {
					if (printProgress)
						System.out.println("Current player turn: O");
					
					player2.makeMove(board);
				}
				
				if (printProgress) {
					System.out.println("\n");
					board.printBoard();
				}
				
				p1Turn = !p1Turn;
				done = board.isFull();
				skippedLastPlayer = false;
			}
			else {
				if(!skippedLastPlayer) {
					if (printProgress) {
						if(p1Turn) {
							System.out.println("Player X has no avalible moves. Skipping turn.");
						}
						else {
							System.out.println("Player O has no avalible moves. Skipping turn.");
							
						}
					}
					
					skippedLastPlayer = true;
					board.skipMove(p1Turn);
					p1Turn = !p1Turn;
				}
				else {
					if (printProgress)
						System.out.println("No more moves avalible. Ending game.");
					done = true;
					
				}
			}
		}
		return endGame(printProgress);
		
	}

	
	private int endGame(boolean printProgress) {
		if(board.getNumWhite() > board.getNumBlack()) {
			if (printProgress)
				System.out.println("White wins!");
			return 0;
		}
		else if(board.getNumBlack() > board.getNumWhite()) {
			if (printProgress)
				System.out.println("Black wins!");
			return 1;
		}
		else {
			if (printProgress)
				System.out.println("Tie!");
			return 2;
		}
	}
}
