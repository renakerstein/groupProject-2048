package groupProject.twentyFortyEight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Game2048Frame extends JFrame {

	private final GridPanel gridPanel;
	private final GameLogic gameLogic;
	private JLabel scoreLabel, highScoreLabel;

	public Game2048Frame() {

		setTitle("2048");
		setSize(680, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout());
		gameLogic = new GameLogic();
		JPanel optionsPanel = optionsPanel();
		gridPanel = new GridPanel(gameLogic, scoreLabel, highScoreLabel);
		add(gridPanel, BorderLayout.CENTER);
		add(optionsPanel, BorderLayout.NORTH);
	}

	public JPanel optionsPanel() {
		JPanel optionsPanel = new JPanel();
		JLabel gameTitle, newGame;

		optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 30));
		optionsPanel.setBackground(Color.WHITE.brighter());

		gameTitle = new JLabel("2048");
		gameTitle.setFont(new Font("Calibri", Font.BOLD, 110));
		gameTitle.setForeground(Color.GRAY);
		optionsPanel.add(gameTitle);

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

		optionsPanel.add(newGame);

		scoreLabel = new JLabel(String.valueOf("<html>SCORE<br>0</html>"));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		highScoreLabel = new JLabel(
				String.valueOf("<html>BEST SCORE<br>0</html>"));
		highScoreLabel.setFont(new Font("Calibri", Font.PLAIN, 20));

		optionsPanel.add(scoreLabel);
		optionsPanel.add(highScoreLabel);

		return optionsPanel;
	}
}