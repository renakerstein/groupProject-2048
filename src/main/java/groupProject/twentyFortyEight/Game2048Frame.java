package groupProject.twentyFortyEight;

import java.awt.BorderLayout;

import javax.swing.JFrame;

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

		gridPanel = new GridPanel();
		optionsPanel = new OptionsPanel(gridPanel);

		add(gridPanel, BorderLayout.CENTER);
		add(optionsPanel, BorderLayout.NORTH);

	}
}