package groupProject.twentyFortyEight;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel {

	private final JLabel gameTitle;
	private final JLabel highScore;
	private final JLabel score;
	private final JLabel newGame;

	public OptionsPanel(final GridPanel gridPanel) {

		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(Color.WHITE.brighter());

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
		highScore = new JLabel("BEST");
		highScore.setFont(new Font("Calibri", Font.PLAIN, 20));

		add(score);
		add(new JLabel("     "));
		add(highScore);
	}
}