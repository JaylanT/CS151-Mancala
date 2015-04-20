
public class MancalaTest {

	public static void main(String[] args) {
		MancalaModel m = new MancalaModel();
		m.setGamePieces(3);
		Board b = new Board(m);
		m.attach(b);
	}

}
