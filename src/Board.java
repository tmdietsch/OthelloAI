import java.util.ArrayList;
import java.util.HashMap;

public class Board {

	private final int BOARD_SIZE = 8;
	private final int WHITE = 0;
	private final int BLACK = 1;
	private final int EMPTY = -1;
	private int numWhite;
	private int numBlack;
	
	private int[][] board;
	HashMap<Integer, ArrayList<Integer>> validMoves;
	
	Board() {
		
		board = new int[BOARD_SIZE][BOARD_SIZE];
		validMoves = new HashMap<Integer, ArrayList<Integer>>();
		
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = EMPTY;
			}
		}
		
		//Starting positions
		board[3][3] = WHITE;
		board[3][4] = BLACK;
		board[4][3] = BLACK;
		board[4][4] = WHITE;
		
		numWhite = 2;
		numBlack = 2;
		
	}
	
	Board(Board orig){
		
		board = new int[BOARD_SIZE][BOARD_SIZE];
		validMoves = new HashMap<Integer, ArrayList<Integer>>();
		
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = orig.board[i][j];
			}
		}
		
		numWhite = orig.numWhite;
		numBlack = orig.numBlack;
		
	}
	
	public boolean isValidMove(int width, int height, boolean isWhite){
		int matchingColor, enemy;
		int currLoc;
		if(isWhite) {
			matchingColor = WHITE;
			enemy = BLACK;
		}
		else {
			matchingColor = BLACK;
			enemy = WHITE;
		}
		int x = width;
		int y = height;
		x--;
		boolean metEnemy = false;
		while(x >= 0) {
			currLoc = board[x][y];
			if(currLoc == matchingColor && metEnemy) {
				return true;
			}
			else if(currLoc == enemy) {
				metEnemy = true;
			}
			else {
				break;
			}
			
			x--;
		}
		
		x = width;
		x--;
		y++;
		metEnemy = false;
		while(x >=0 && y < 8) {
			currLoc = board[x][y];
			if(currLoc == matchingColor && metEnemy) {
				return true;
			}
			else if(currLoc == enemy) {
				metEnemy = true;
			}
			else {
				break;
			}
			
			x--;
			y++;
		}
		x = width;
		y = height;
		y++;
		metEnemy = false;
		while(y < 8) {
			currLoc = board[x][y];
			if(currLoc == matchingColor && metEnemy) {
				return true;
			}
			else if(currLoc == enemy) {
				metEnemy = true;
			}
			else {
				break;
			}
			
			y++;
		}
		x = width;
		y = height;
		x++;
		y++;
		metEnemy = false;
		while(x < 8 && y < 8) {
			currLoc = board[x][y];
			if(currLoc == matchingColor && metEnemy) {
				return true;
			}
			else if(currLoc == enemy) {
				metEnemy = true;
			}
			else {
				break;
			}
			
			x++;
			y++;
		}
		
		x = width;
		y = height;
		x++;
		metEnemy = false;
		while(x < 8) {
			currLoc = board[x][y];
			if(currLoc == matchingColor && metEnemy) {
				return true;
			}
			else if(currLoc == enemy) {
				metEnemy = true;
			}
			else {
				break;
			}
			
			x++;
		}
		
		x = width;
		y = height;
		x++;
		y--;
		metEnemy = false;
		while(x < 8 && y >=0) {
			currLoc = board[x][y];
			if(currLoc == matchingColor && metEnemy) {
				return true;
			}
			else if(currLoc == enemy) {
				metEnemy = true;
			}
			else {
				break;
			}
			
			x++;
			y--;
		}
		
		x = width;
		y = height;
		y--;
		metEnemy = false;
		while(y >= 0) {
			currLoc = board[x][y];
			if(currLoc == matchingColor && metEnemy) {
				return true;
			}
			else if(currLoc == enemy) {
				metEnemy = true;
			}
			else {
				break;
			}
			
			y--;
		}
		
		x = width;
		y = height;
		y--;
		x--;
		metEnemy = false;
		while(x >= 0 && y >= 0) {
			currLoc = board[x][y];
			if(currLoc == matchingColor && metEnemy) {
				return true;
			}
			else if(currLoc == enemy) {
				metEnemy = true;
			}
			else {
				break;
			}
			
			y--;
			x--;
		}
		
		return false;
	}
	
	public void move(int width, int height, boolean isWhite) {
		
		if (isValidMove(width, height, isWhite)) {
			board[width][height] = isWhite ? WHITE : BLACK;
			if(isWhite) {
				numWhite++;
			}
			else {
				numBlack++;
			}
			flipPieces(width, height);
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public boolean isFull() {
		return (numWhite + numBlack == BOARD_SIZE * BOARD_SIZE);
	}
	
	public int getNumWhite() {
		return numWhite;
	}
	
	public int getNumBlack() {
		return numBlack;
	}
	
	private void flipPieces(int width, int height) {
		
		int samePiece;
		int oppositePiece;
		boolean isWhite;
		
		if (board[width][height] == 0) {
			samePiece = 0;
			oppositePiece = 1;
			isWhite = true;
		}
		else {
			samePiece = 1;
			oppositePiece = 0;
			isWhite = false;
		}
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				
				int distance = 1;
				
				try {
					
					while(board[width + (i * distance)][height + (j * distance)] == oppositePiece) {
						
						distance++;
						
					}
					
					if (board[width + (i * distance)][height + (j * distance)] == samePiece) {
						
						distance--;
						while (distance != 0) {
							
							board[width + (i * distance)][height + (j * distance)] = samePiece;
							distance--;
							if(isWhite) {
								numWhite++;
								numBlack--;
							}
							else {
								numWhite--;
								numBlack++;
							}
							
						}
						
						
					}
					
				}
				catch (ArrayIndexOutOfBoundsException e) {}
				
			}
			
		}
		
	}
	
	public void printBoard() {
		System.out.println("White score: " + getNumWhite());
		System.out.println("Black score: " + getNumBlack());
		
		System.out.println("\n   0 1 2 3 4 5 6 7");
		for(int i = 0; i < BOARD_SIZE; i++) {
			System.out.print(i + " |");
			for(int j = 0; j < BOARD_SIZE; j++) {
				switch(board[j][i]) {
				case(WHITE):
					System.out.print("X");
					System.out.print("|");
					break;
				case(BLACK):
					System.out.print("O|");
					break;
				default:
					System.out.print(" |");
				}
			}
			System.out.println();
		}
	}
	
}
