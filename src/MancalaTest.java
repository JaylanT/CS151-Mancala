import java.util.InputMismatchException;
import java.util.Scanner;


public class MancalaTest {

	public static void main(String[] args) {
		Board b = new Board();
		b.setGamePieces(3);
		b.print();
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			try {
				int n = in.nextInt();
				b.move(n);
				b.print();
			} catch(InputMismatchException e) {
				System.exit(0);
			}
		}
	}

}
