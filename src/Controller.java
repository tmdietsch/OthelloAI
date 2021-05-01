
//Is extended by Player and AI classes for simplicity
abstract class Controller {
	
	//Which color does the controller have? (black or white)
	protected boolean isWhite;
	
	//Basic move method
	public abstract void makeMove(Board b);

}
