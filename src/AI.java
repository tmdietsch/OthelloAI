import java.util.ArrayList;

public class AI extends Controller {
	
	private int useHeuristic;
	private final int MAX_DEPTH = 6;
	private boolean smart;
	
	public AI(boolean isWhite, int useHeuristic, boolean isSmart) {
		this.isWhite = isWhite;
		this.useHeuristic = useHeuristic;
		smart = isSmart;
	}
	
	@Override
	public void makeMove(Board b) {
		
		
	}
	
	private int maxValue(Board state, int alpha, int beta, int currDepth) {
		return 0;
	}
	
	private int minValue(Board state, int alpha, int beta, int currDepth) {
		if(currDepth == MAX_DEPTH) {
			if(smart)
				return countingHeuristic(state, false) + cornersHeuristic(state, false);
			return countingHeuristic(state, false);
		}
		
		int v = Integer.MAX_VALUE;
		ArrayList<Board> actions = getActions(state, false);
		
		for(Board a: actions) {
			v = Math.min(v, maxValue(a, alpha, beta, currDepth+1));
			if(v <= alpha)
				return v;
			beta = Math.min(beta, v);
		}
		
		return v;
	}
	
	private int countingHeuristic(Board board, boolean isMax) {
		
		if(isMax) {
			if(isWhite) {
				return board.getNumWhite();
			}
			
			return board.getNumBlack();
		}
		else {
			if(isWhite) {
				return board.getNumBlack();
			}
			
			return board.getNumWhite();
		}
			
	}
		
	
	private int cornersHeuristic(Board board, boolean isMax) {
		int h = 0;
		int s;
		
		if(isMax) {
			if(isWhite) {
				if(board.getSpot(0, 0) == 0) {
					h+=10;
				}
				if(board.getSpot(0, 7) == 0) {
					h+=10;
				}
				if(board.getSpot(7, 0) == 0) {
					h+=10;
				}
				if(board.getSpot(7, 7) == 0) {
					h+=10;
				}
			}
			else {
				if(board.getSpot(0, 0) == 1) {
					h+=10;
				}
				if(board.getSpot(0, 7) == 1) {
					h+=10;
				}
				if(board.getSpot(7, 0) == 1) {
					h+=10;
				}
				if(board.getSpot(7, 7) == 1) {
					h+=10;
				}
			}
		}
		else {
			if(!isWhite) {
				if(board.getSpot(0, 0) == 0) {
					h+=10;
				}
				if(board.getSpot(0, 7) == 0) {
					h+=10;
				}
				if(board.getSpot(7, 0) == 0) {
					h+=10;
				}
				if(board.getSpot(7, 7) == 0) {
					h+=10;
				}
			}
			else {
				if(board.getSpot(0, 0) == 1) {
					h+=10;
				}
				if(board.getSpot(0, 7) == 1) {
					h+=10;
				}
				if(board.getSpot(7, 0) == 1) {
					h+=10;
				}
				if(board.getSpot(7, 7) == 1) {
					h+=10;
				}
			}
		}
		
		return h;
	}

	private ArrayList<Board> getActions(Board b, boolean isMax){
		ArrayList<Board> actions = new ArrayList<Board>();
		ArrayList<Integer[]> possibleActions = b.getValidMoves();
		Board temp;
		
		for(Integer[] i: possibleActions) {
			temp = new Board(b);
			if(isMax) {
				temp.move(i[0], i[1], isWhite);
			}
			else {
				temp.move(i[0], i[1], !isWhite);
			}
			actions.add(temp);
		}
		
		
		return actions;
	}
	
}
