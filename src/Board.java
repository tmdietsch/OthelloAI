import java.util.ArrayList;
import java.util.HashMap;

public class Board {

	private final int BOARD_SIZE = 8;
	private final int WHITE = 0;
	private final int BLACK = 1;
	private final int EMPTY = -1;
	private int numWhite;
	private int numBlack;
	
	private int[] previousMove;	
	private int[][] board;
	//HashMap<Integer, ArrayList<Integer>> validMoves;
	ArrayList<Integer[]> validMoves;
	
	Board() {
		
		board = new int[BOARD_SIZE][BOARD_SIZE];
		validMoves = new ArrayList<Integer[]>();
		
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
		
		updateValidMoves(true);
		
		previousMove = null;
	}
	
	Board(Board orig){
		
		board = new int[BOARD_SIZE][BOARD_SIZE];
		validMoves = new ArrayList<Integer[]>();//new HashMap<Integer, ArrayList<Integer>>();
		
		for (Integer[] space : validMoves) {
			validMoves.add(space);
		}
		
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = orig.board[i][j];
			}
		}
		
		numWhite = orig.numWhite;
		numBlack = orig.numBlack;
		
		previousMove = orig.previousMove;
	}
	
	public int getSpot(int x, int y) {
		return board[x][y];
	}
	
	public ArrayList<Integer[]> getValidMoves() {
		
		return validMoves;
	}
	
	public boolean isValidMove(int width, int height, boolean isWhite){
		int matchingColor, enemy;
		int currLoc;
		
		if(board[width][height] == 1 || board[width][height] == 0) {
			return false;
		}
		
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
			
			updateValidMoves(!isWhite);
			
			previousMove = new int[] {width, height};
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
	
	public int getNumValidMoves() {
		return validMoves.size();
	}
	
	public int[] getPreviousMove() {
		return previousMove;
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
	
	private void updateValidMoves(boolean isWhite) {
		
		validMoves = new ArrayList<Integer[]>();
		
		for (int w = 0; w < BOARD_SIZE; w++) {
			for (int h = 0; h < BOARD_SIZE; h++) {
				
				if (isValidMove(w, h, isWhite)) {
					validMoves.add(new Integer[] {w, h});
				}
				
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
