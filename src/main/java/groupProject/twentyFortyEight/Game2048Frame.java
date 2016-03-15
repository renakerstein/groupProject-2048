package groupProject.twentyFortyEight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Game2048Frame extends JFrame {

	private final GridPanel gridPanel;
	private final GameLogic gameLogic;
	private JLabel scoreLabel, highScoreLabel;

	public Game2048Frame() {

		setTitle("2048");
		setSize(660, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout());
		gameLogic = new GameLogic();

		JPanel scorePanel = scorePanel();
		JPanel optionsPanel = optionsPanel();

		gridPanel = new GridPanel(gameLogic, scoreLabel, highScoreLabel);
		add(gridPanel, BorderLayout.CENTER);
	
		Container container = new Container();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(Color.WHITE);

		container.add(optionsPanel);
		container.add(scorePanel);
		add(container, BorderLayout.NORTH);
	}

	public JPanel optionsPanel() {
		JPanel optionsPanel = new JPanel();
		JLabel gameTitle, newGame;

		optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 0));
		optionsPanel.setBackground(Color.WHITE.brighter());
		optionsPanel.setPreferredSize(new Dimension(getWidth(), 100));

		gameTitle = new JLabel("2048");
		gameTitle.setFont(new Font("Calibri", Font.BOLD, 110));
		gameTitle.setForeground(Color.GRAY);

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

		JLabel howToPlay = new JLabel("HOW TO PLAY");
		howToPlay.setFont(new Font("Calibri", Font.PLAIN, 20));
		howToPlay.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				JOptionPane
						.showMessageDialog(
								getContentPane(),
								"Use your arrow keys to move the tiles. \nWhen two tiles with the same number touch, they merge into one!\nTo win, get to 2048!",
								"HOW TO PLAY", JOptionPane.PLAIN_MESSAGE);
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
		optionsPanel.add(gameTitle);
		optionsPanel.add(newGame);
		optionsPanel.add(howToPlay);

		return optionsPanel;
	}

	public JPanel scorePanel() {
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 22, 0));
		scorePanel.setBackground(Color.WHITE);
		scorePanel.setPreferredSize(new Dimension(getWidth(), 60));

		scoreLabel = new JLabel(String.valueOf("<html>SCORE<br>0</html>"));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		highScoreLabel = new JLabel(
				String.valueOf("<html>BEST SCORE<br>0</html>"));
		highScoreLabel.setFont(new Font("Calibri", Font.PLAIN, 20));

		scorePanel.add(scoreLabel);
		scorePanel.add(highScoreLabel);

		return scorePanel;
	}

}