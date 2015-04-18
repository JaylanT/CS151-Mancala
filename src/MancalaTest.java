
public class MancalaTest {

	public static void main(String[] args) {
		Board b = new Board();
		b.setGamePieces(3);
		b.print();
		b.move(4);
		b.print();
		b.move(12);
		b.print();
		b.move(3);
		b.print();
		b.move(2);
		b.print();
		b.move(8);
		b.print();
		b.move(4);
		b.print();
		b.move(1);
		b.print();
		b.move(7);
		b.print();
		b.move(5);
		b.print();
	}

}
