import java.util.ArrayList;

public class AI extends Controller {
	
	private int maxDepth;
	private boolean smart;
	
	public AI(boolean isWhite, boolean isSmart, int maxDepth) {
		this.isWhite = isWhite;
		smart = isSmart;
		this.maxDepth = maxDepth;
	}
	
	@Override
	public void makeMove(Board state) {
		
		int[] thing = alphaBetaSearch(state);
		
		if (thing[0] != -1)
			state.move(thing[0], thing[1], isWhite);
		
	}
	
	private int[] alphaBetaSearch(Board state) {
		
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int currDepth = 1;

		int v = Integer.MIN_VALUE;
		ArrayList<Board> actions = getActions(state, true);
		
//		int[] temps = actions.get(0).getPreviousMove();
//		
//		System.out.println(temps[0] + " " + temps[1]);
		
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
		
		if (bestBoard == null) {
			state.skipMove(isWhite);
			bestBoard = new Board(state);
		}
		
		return bestBoard.getPreviousMove();
		
	}
	
	private int maxValue(Board state, int alpha, int beta, int currDepth) {
		if(state.isFull()) {
			return countingHeuristic(state, true);
		}
		
		if(currDepth == maxDepth) {
			if(smart)
				return countingHeuristic(state, true) + cornersHeuristic(state, true);
			else
				return countingHeuristic(state, true);
		}
		
		int v = Integer.MIN_VALUE;
		ArrayList<Board> actions = getActions(state, true);
		
		if (actions.size() != 0) {
			for(Board a: actions) {
				v = Math.max(v, minValue(a, alpha, beta, currDepth + 1));
				if(v >= beta)
					return v;
				alpha = Math.max(alpha, v);
			}
		}
		else
			return countingHeuristic(state, true) + cornersHeuristic(state, true);
		
		return v;
	}
	
	private int minValue(Board state, int alpha, int beta, int currDepth) {
		if(state.isFull()) {
			return countingHeuristic(state, false);
		}
		
		if(currDepth == maxDepth) {
			if(smart)
				return countingHeuristic(state, false) + cornersHeuristic(state, false);
			else
				return countingHeuristic(state, false);
		}
		
		int v = Integer.MAX_VALUE;
		ArrayList<Board> actions = getActions(state, false);
		
		if (actions.size() != 0) {
			for(Board a: actions) {
				v = Math.min(v, maxValue(a, alpha, beta, currDepth + 1));
				if(v <= alpha)
					return v;
				beta = Math.min(beta, v);
			}
		}
		else
			return countingHeuristic(state, false) + cornersHeuristic(state, false);
		
		return v;
	}
	
	private int countingHeuristic(Board board, boolean isMax) {
		
		if(isWhite) {
			return board.getNumWhite() - board.getNumBlack();
		}
			
		return board.getNumBlack() - board.getNumWhite();
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
			
			//System.out.println("dumb: " + i[0] + " " + i[1]);
			
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
