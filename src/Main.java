
public class Main {

	public static void main(String[] args) {
		
		Controller p1 = new AI(true, true, 4);
		Controller p2 = new AI(false, true, 4);
		Game othello = new Game(p1, p2);
		
		othello.play(true);
		
//		experiment();
		
	}
	
	static void experiment() {
		int minLevel = 3;
		int maxLevel = 8;
		
		int[] numWinsDumb = new int[3];
		int[] numWinsDumbVSmart = new int[3];
		int[] numWinsSmartVDumb = new int[3];
		int[] numWinsSmart = new int[3];
		
		System.out.println("Playing dumb vs. dumb...");
		for (int i = minLevel; i <= maxLevel; i++) {
			for (int j = minLevel; j <= maxLevel; j++) {
				Controller p1 = new AI(true, false, i);
				Controller p2 = new AI(false, false, j);
				Game othello = new Game(p1, p2);
				
				int result = othello.play(false);
				
				if (result == 0)
					numWinsDumb[0]++;
				else if (result == 1)
					numWinsDumb[1]++;
				else
					numWinsDumb[2]++;
			}
			System.out.println(i);
		}
		
		System.out.println("Playing dumb vs. smart...");
		for (int i = minLevel; i <= maxLevel; i++) {
			for (int j = minLevel; j <= maxLevel; j++) {
				Controller p1 = new AI(true, false, i);
				Controller p2 = new AI(false, true, j);
				Game othello = new Game(p1, p2);
				
				int result = othello.play(false);
				
				if (result == 0)
					numWinsDumbVSmart[0]++;
				else if (result == 1)
					numWinsDumbVSmart[1]++;
				else
					numWinsDumbVSmart[2]++;
			}
			System.out.println(i);
		}
		
		System.out.println("Playing smart vs. dumb...");
		for (int i = minLevel; i <= maxLevel; i++) {
			for (int j = minLevel; j <= maxLevel; j++) {
				Controller p1 = new AI(true, true, i);
				Controller p2 = new AI(false, false, j);
				Game othello = new Game(p1, p2);
				
				int result = othello.play(false);
				
				if (result == 0)
					numWinsSmartVDumb[0]++;
				else if (result == 1)
					numWinsSmartVDumb[1]++;
				else
					numWinsSmartVDumb[2]++;
			}
			System.out.println(i);
		}
		
		System.out.println("Playing smart vs. smart...");
		for (int i = minLevel; i <= maxLevel; i++) {
			for (int j = minLevel; j <= maxLevel; j++) {
				Controller p1 = new AI(true, true, i);
				Controller p2 = new AI(false, true, j);
				Game othello = new Game(p1, p2);
				
				int result = othello.play(false);
				
				if (result == 0)
					numWinsSmart[0]++;
				else if (result == 1)
					numWinsSmart[1]++;
				else
					numWinsSmart[2]++;
			}
			System.out.println(i);
		}
		
		System.out.println("Results");
		System.out.println("\tDumb: " + numWinsDumb[0] + "\tDumb: " + numWinsDumb[1]
				+ "\tTies: " + numWinsDumb[2]);
		System.out.println("\tDumb: " + numWinsDumbVSmart[0] + "\tSmart: " + numWinsDumbVSmart[1]
				+ "\tTies: " + numWinsDumbVSmart[2]);
		System.out.println("\tSmart: " + numWinsSmartVDumb[0] + "\tDumb: " + numWinsSmartVDumb[1]
				+ "\tTies: " + numWinsSmartVDumb[2]);
		System.out.println("\tSmart: " + numWinsSmart[0] + "\tSmart: " + numWinsSmart[1]
				+ "\tTies: " + numWinsSmart[2]);
	}
	
}
