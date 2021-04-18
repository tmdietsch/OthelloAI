import java.util.ArrayList;
import java.util.HashMap;

public class Board {

	private final int BOARD_SIZE = 8;
	private final int WHITE = 0;
	private final int BLACK = 1;
	private int numWhite;
	private int numBlack;
	
	private int[][] board;
	HashMap<Integer, ArrayList<Integer>> validMoves;
	
	Board() {
		
		board = new int[BOARD_SIZE][BOARD_SIZE];
		
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = -1;
			}
		}
		
		//Starting positions
		board[3][3] = WHITE;
		board[3][4] = BLACK;
		board[4][3] = BLACK;
		board[4][4] = WHITE;
		
		numWhite = 2;
		numBlack = 2;
		
		validMoves = new HashMap<Integer, ArrayList<Integer>>();
		
	}
	
	Board(Board original){
		
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
		
		board[width][height] = isWhite ? WHITE : BLACK;
		
	}
	
}
