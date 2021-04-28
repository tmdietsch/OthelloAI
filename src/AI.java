
public class AI extends Controller {
	
	private int useHeuristic;
	
	public AI(boolean isWhite, int useHeuristic) {
		this.isWhite = isWhite;
		this.useHeuristic = useHeuristic;
	}
	
	@Override
	public void makeMove(Board b) {
		
		
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

}
