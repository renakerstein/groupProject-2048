package groupProject.twentyFortyEight;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel {

	private final JLabel gameTitle;
	private final JLabel bestScore;
	private final JLabel score;
	private final JLabel newGame;
	private GameLogic gameLogic;

	public OptionsPanel(final GridPanel gridPanel, GameLogic logic) {

		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(Color.WHITE.brighter());
		gameLogic = logic;

		gameTitle = new JLabel("2048");
		gameTitle.setFont(new Font("Calibri", Font.BOLD, 110));
		gameTitle.setForeground(Color.GRAY);
		add(gameTitle);
		add(new JLabel("     "));

		newGame = new JLabel("NEW GAME");
		newGame.setFont(new Font("Calibri", Font.PLAIN, 40));
		newGame.setForeground(Color.GRAY);
		newGame.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				gridPanel.newGame();
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}
		});
		add(newGame);

		add(new JLabel("                    "));

		score = new JLabel("SCORE");
		score.setFont(new Font("Calibri", Font.PLAIN, 20));
		bestScore = new JLabel("BEST");
		bestScore.setFont(new Font("Calibri", Font.PLAIN, 20));

		add(score);
		add(new JLabel("     "));
		add(bestScore);

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				score.setText(String.valueOf(gameLogic.getScore()));
			}
		});
	}

}