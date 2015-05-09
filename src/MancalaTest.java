public class MancalaTest {

	public static void main(String[] args) {
		MancalaModel m = new MancalaModel();
		final MancalaGame game = new MancalaGame(m);
		m.attach(game);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				game.init();
			}
		});
	}

}