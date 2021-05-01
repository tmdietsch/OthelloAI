import java.util.ArrayList;

public class AI extends Controller {
	
	private int maxDepth;
	private boolean smart;
	
	//Constructor
	public AI(boolean isWhite, boolean isSmart, int maxDepth) {
		this.isWhite = isWhite;
		smart = isSmart;
		this.maxDepth = maxDepth;
	}
	
	//Overridden method which determines the move made by the AI
	@Override
	public void makeMove(Board state) {
		
		int[] thing = alphaBetaSearch(state);
		
		//make move if it isn't invalid
		if (thing[0] != -1)
			state.move(thing[0], thing[1], isWhite);
		
	}
	
	//Starting method for the alpha-beta search
	private int[] alphaBetaSearch(Board state) {
		
		//Setting initial values
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int currDepth = 1;	//start at 1 since we are already 1 deep
		int v = Integer.MIN_VALUE;
		
		//Getting all available actions in the state
		ArrayList<Board> actions = getActions(state, true);
		
		Board bestBoard = null;
		
		//for each board in actions, find the min value
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
		
		//there are no available moves, skip this turn
		//sets the move to (-1, -1), which counts as skipping this turn
		if (bestBoard == null) {
			state.skipMove(isWhite);
			bestBoard = new Board(state);
		}
		
		//returning the previous move made
		return bestBoard.getPreviousMove();
		
	}
	
	private int maxValue(Board state, int alpha, int beta, int currDepth) {
		//stop if board is full
		if(state.isFull()) {
			return countingHeuristic(state, true);
		}
		
		//if we have gone as far as we wanted to stop
		if(currDepth == maxDepth) {
			//if smart AI return this
			if(smart)
				return countingHeuristic(state, true) + cornersHeuristic(state, true);
			//if dumb AI return this
			else
				return countingHeuristic(state, true);
		}
		
		//worst possible value
		int v = Integer.MIN_VALUE;
		ArrayList<Board> actions = getActions(state, true);
		
		if (actions.size() != 0) {
			for(Board a: actions) {
				v = Math.max(v, minValue(a, alpha, beta, currDepth + 1));
				//prune if possible
				if(v >= beta)
					return v;
				alpha = Math.max(alpha, v);
			}
		}
		//if no possible moves go to the next state
		else {
			Board a = new Board(state);
			a.skipMove(isWhite);
			return minValue(a, alpha, beta, currDepth + 1);
		}
		
		return v;
	}
	
	private int minValue(Board state, int alpha, int beta, int currDepth) {
		//stop if board is full
		if(state.isFull()) {
			return countingHeuristic(state, false);
		}
		
		//if we have gone as far as we wanted to stop
		if(currDepth == maxDepth) {
			//if smart AI return this
			if(smart)
				return countingHeuristic(state, false) + cornersHeuristic(state, false);
			//If dumb AI return this
			else
				return countingHeuristic(state, false);
		}
		
		//worst possible value
		int v = Integer.MAX_VALUE;
		ArrayList<Board> actions = getActions(state, false);
		
		if (actions.size() != 0) {
			for(Board a: actions) {
				v = Math.min(v, maxValue(a, alpha, beta, currDepth + 1));
				//prune if possible
				if(v <= alpha)
					return v;
				beta = Math.min(beta, v);
			}
		}
		//if no possible moves go to the next state
		else {
			Board a = new Board(state);
			a.skipMove(!isWhite);
			return maxValue(a, alpha, beta, currDepth + 1);
		}
		
		return v;
	}
	
	//returns the difference in changed pieces
	private int countingHeuristic(Board board, boolean isMax) {
		
		if(isWhite) {
			return board.getNumWhite() - board.getNumBlack();
		}
			
		return board.getNumBlack() - board.getNumWhite();
	}
		
	//adds 10 to the h value for every corner piece
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

	//returns an ArrayList of all the possible states
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
