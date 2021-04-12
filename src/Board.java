
public class Board {

	private final int BOARD_SIZE = 8;
	private final int WHITE = 0;
	private final int BLACK = 1;
	
	private int[][] board;
	
	Board() {
		
		board = new int[BOARD_SIZE][BOARD_SIZE];
		
		//Starting positions
		board[3][3] = WHITE;
		board[3][4] = BLACK;
		board[4][3] = BLACK;
		board[4][4] = WHITE;
		
	}
	
	public void move(int width, int height, boolean isWhite) {
		
		board[width][height] = isWhite ? WHITE : BLACK;
		
	}
	
}
