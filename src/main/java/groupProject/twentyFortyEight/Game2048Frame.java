package groupProject.twentyFortyEight;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game2048Frame extends JFrame {

	private final GridPanel gridPanel;
	private final OptionsPanel optionsPanel;

	public Game2048Frame() {

		setTitle("2048");
		setSize(680, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout());

		JLabel score = new JLabel();
		JLabel bestScore = new JLabel();
		gridPanel = new GridPanel(score, bestScore);
		optionsPanel = new OptionsPanel(gridPanel, score, bestScore);

		add(gridPanel, BorderLayout.CENTER);
		add(optionsPanel, BorderLayout.NORTH);

	}
}