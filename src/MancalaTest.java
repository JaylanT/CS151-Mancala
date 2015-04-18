
public class MancalaTest {

	public static void main(String[] args) {
		Board b = new Board();
		b.setGamePieces(3);
		b.print();
		b.move(4);
		b.print();
		b.move(12);
		b.print();
	}

}
