import java.util.ArrayList;

public class AI extends Controller {
	
	private final int MAX_DEPTH = 6;
	private boolean smart;
	
	public AI(boolean isWhite, boolean isSmart) {
		this.isWhite = isWhite;
		smart = isSmart;
	}
	
	@Override
	public void makeMove(Board state) {
		
		int[] thing = alphaBetaSearch(state);
		state.move(thing[0], thing[1], isWhite);
		
	}
	
	private int[] alphaBetaSearch(Board state) {
		
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int currDepth = 1;

		int v = Integer.MIN_VALUE;
		ArrayList<Board> actions = getActions(state, true);
		
		int[] temps = actions.get(1).getPreviousMove();
		
		System.out.println(temps[0] + " " + temps[1]);
		
		
		Board bestBoard = null;
		
		for(Board a: actions) {
			int temp = minValue(a, alpha, beta, currDepth + 1);
			
			if (v < temp) {
				v = temp;
				bestBoard = a;
			}
			
			if(v >= beta)
				return a.getPreviousMove();
			alpha = Math.max(alpha, v);
		}
		
		return bestBoard.getPreviousMove();
		
	}
	
	private int maxValue(Board state, int alpha, int beta, int currDepth) {
		if(currDepth == MAX_DEPTH) {
			if(smart)
				return countingHeuristic(state, true) + cornersHeuristic(state, true);
			return countingHeuristic(state, true);
		}
		
		int v = Integer.MIN_VALUE;
		ArrayList<Board> actions = getActions(state, true);
		
		for(Board a: actions) {
			v = Math.max(v, minValue(a, alpha, beta, currDepth + 1));
			if(v >= beta)
				return v;
			alpha = Math.max(alpha, v);
		}
		
		return v;
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
			v = Math.min(v, maxValue(a, alpha, beta, currDepth + 1));
			if(v <= alpha)
				return v;
			beta = Math.min(beta, v);
		}
		
		return v;
	}
	
	private int countingHeuristic(Board board, boolean isMax) {
		
		if(isWhite) {
			return board.getNumWhite();
		}
			
		return board.getNumBlack();	
	}
		
	
	private int cornersHeuristic(Board board, boolean isMax) {
		int h = 0;
		
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
		
		
		return h;
	}

	private ArrayList<Board> getActions(Board b, boolean isMax){
		ArrayList<Board> actions = new ArrayList<Board>();
		ArrayList<Integer[]> possibleActions = b.getValidMoves();
		Board temp;
		
		for(Integer[] i : possibleActions) {
			
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
