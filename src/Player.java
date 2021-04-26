import java.util.Scanner;

public class Player extends Controller {
	
	Player(boolean isWhite) {
		this.isWhite = isWhite;
	}

	@Override
	public void makeMove(Board b) {
		
		Scanner scnr = new Scanner(System.in);
		int width, height;
		boolean inLoop = true;
		
		while (inLoop) {
			System.out.println("Make your move");
			
			System.out.println("Width: ");
			width = scnr.nextInt();
			
			System.out.println("Height: ");
			height = scnr.nextInt();
			
			try {
				b.move(width, height, isWhite);
				inLoop = false;
			}
			catch (Exception e) {
				System.out.println("Invalid move, try again\n");
			}
		}
		
		//scnr.close();
		
	}
	
}
