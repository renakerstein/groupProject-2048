package groupProject.twentyFortyEight;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game2048Frame extends JFrame {

	private final GridPanel gridPanel;
	private final OptionsPanel optionsPanel;
	private GameLogic gameLogic;

	public Game2048Frame() {

		setTitle("2048");
		setSize(680, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout());

		gameLogic = new GameLogic();
		gridPanel = new GridPanel(gameLogic);
		optionsPanel = new OptionsPanel(gridPanel, gameLogic);

		add(gridPanel, BorderLayout.CENTER);
		add(optionsPanel, BorderLayout.NORTH);

	}
}