public class MancalaTest {

	public static void main(String[] args) {
		MancalaModel m = new MancalaModel();
		Board b = new Board(m);
		m.attach(b);
		b.init();
	}

}