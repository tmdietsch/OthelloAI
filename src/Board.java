import java.util.ArrayList;
import java.util.HashMap;

public class Board {

	private final int BOARD_SIZE = 8; //8x8 board
	private final int WHITE = 0;
	private final int BLACK = 1;
	private final int EMPTY = -1;
	private int numWhite;	//number of white pieces on the board
	private int numBlack;	//number of black pieces on the board
	
	private int[] previousMove;	//the move taken in the last turn
	private int[][] board;
	ArrayList<Integer[]> validMoves;	//moves available to the current player
	
	//creates default board
	Board() {
		
		board = new int[BOARD_SIZE][BOARD_SIZE];
		validMoves = new ArrayList<Integer[]>();
		
		//Set all board spaces to empty
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
	
	//creates copy of given board
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
	
	//return value in given spot
	public int getSpot(int x, int y) {
		return board[x][y];
	}
	
	//returns the valid moves array
	public ArrayList<Integer[]> getValidMoves() {
		return validMoves;
	}
	
	//checks if it is a legal move
	public boolean isValidMove(int width, int height, boolean isWhite){
		int matchingColor, enemy;
		int currLoc;
		
		//if a piece is on the board then return false
		if(board[width][height] == 1 || board[width][height] == 0) {
			return false;
		}
		
		//check who's turn it is
		if(isWhite) {
			matchingColor = WHITE;
			enemy = BLACK;
		}
		else {
			matchingColor = BLACK;
			enemy = WHITE;
		}
		
		//check left
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
		
		//checks upper left
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
		
		//checks above
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
		
		//checks upper right
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
		
		//checks right
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
		
		//checks lower right
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
		
		//checks below
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
		
		//checks lower left
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
	
	//attempts to place the piece at x = width and y = height
	public void move(int width, int height, boolean isWhite) {
		
		//if legal move place the piece and increment the correct number
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
			
			//set as the last taken move
			previousMove = new int[] {width, height};
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}
	
	//adjust for skipped move
	public void skipMove(boolean isWhite) {
		previousMove = new int[] {-1, -1};
		
		updateValidMoves(!isWhite);
	}
	
	//checks if board is full of pieces
	public boolean isFull() {
		return (numWhite + numBlack == BOARD_SIZE * BOARD_SIZE);
	}
	
	//return number of white pieces 
	public int getNumWhite() {
		return numWhite;
	}
	
	//return number of black pieces
	public int getNumBlack() {
		return numBlack;
	}
	
	//returns how many moves are available to the current player
	public int getNumValidMoves() {
		return validMoves.size();
	}
	
	//returns the last move taken
	public int[] getPreviousMove() {
		return previousMove;
	}
	
	//Flips all taken pieces around a piece after a move is made
	//Piece is described in width and height
	private void flipPieces(int width, int height) {
		
		int samePiece;
		int oppositePiece;
		boolean isWhite;
		
		//Set same and opposite piece based on where we start
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
		
		//Loops go in each direction: up-left, up, up-right, left, right, etc.
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				
				//How far in each direction to check
				int distance = 1;
				
				//Used to check if we are out of bounds of the array
				try {
					
					//Go in a direction till find a blank or same piece
					while(board[width + (i * distance)][height + (j * distance)] == oppositePiece) {
						
						distance++;	//Go up one more
						
					}
					
					//Found a same piece?
					if (board[width + (i * distance)][height + (j * distance)] == samePiece) {
						
						//Start backtracking and flip all pieces as you go
						distance--;
						while (distance != 0) {
							
							//Change piece to your color
							board[width + (i * distance)][height + (j * distance)] = samePiece;
							if(isWhite) {
								numWhite++;
								numBlack--;
							}
							else {
								numWhite--;
								numBlack++;
							}
							
							distance--;	//Backtrack for next iteration
							
						}
						
					}
					
				}
				catch (ArrayIndexOutOfBoundsException e) {}
				
			}
			
		}
		
	}
	
	//checks for valid moves at each spot on the board
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
	
	//print the board to the console
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
