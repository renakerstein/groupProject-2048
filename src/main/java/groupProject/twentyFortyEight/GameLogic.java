package groupProject.twentyFortyEight;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameLogic {

	private final Tile[][] gameTiles;
	private ArrayList<Tile> availableSlots;
	private Tile chosenSlot;
	private int currentVal, comparisonVal;
	private Tile current, comparison;
	private boolean merged;

	public GameLogic() {
		this.gameTiles = new Tile[4][4];
		for (int row = 0; row < gameTiles.length; row++) {
			for (int column = 0; column < gameTiles[row].length; column++) {
				gameTiles[row][column] = new Tile();
			}
		}
		this.availableSlots = new ArrayList<Tile>();
		this.merged = false;
	}

	public Tile addTile() {
		availableSlots = emptySlots();
		if (!availableSlots.isEmpty()) {
			int index = (int) (Math.random() * availableSlots.size()) % availableSlots.size();
			chosenSlot = availableSlots.get(index);
			chosenSlot.setValue(Math.random() < 0.9 ? 2 : 4);
		}
		return chosenSlot; // get row and column of this specific tile

	}

	public void moveLeft() {
		for (int row = 0; row < gameTiles.length; row++) {
			for (int col = 3; col >= 0; col--) {
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					int counter = col;
					while (counter != 0) {
						comparison = gameTiles[row][counter - 1];
						comparisonVal = comparison.getValue();
						merged = checkForEquality();
						counter--;
					}
				}
			}
		}

		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[row][column]);
			}

			for (int i = list.size() - 1; i >= 0; i--) {
				if (list.get(i).isEmpty()) {
					list.remove(list.get(i));
				}
			}
			while (list.size() != 4) {
				list.add(new Tile());
			}
			for (int i = 0; i < list.size(); i++) {
				gameTiles[row][i] = list.get(i);
			}
		}
	}

	public void moveRight() {
		for (int row = 0; row < gameTiles.length; row++) {
			for (int col = 0; col < gameTiles[row].length; col++) {
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					int counter = col;
					while (counter != 3) {
						comparison = gameTiles[row][counter + 1];
						comparisonVal = comparison.getValue();
						merged = checkForEquality();
						counter++;
					}
				}
			}
		}

		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[row][column]);
			}

			for (int i = list.size() - 1; i >= 0; i--) {
				if (list.get(i).isEmpty()) {
					list.remove(list.get(i));
				}
			}
			while (list.size() != 4) {
				list.addFirst(new Tile());
			}
			for (int i = list.size() - 1; i >= 0; i--) {
				gameTiles[row][i] = list.get(i);
			}
		}

	}

	public void moveDown() {
		for (int row = 0; row < gameTiles.length; row++) {
			for (int col = 3; col >= 0; col--) {
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					int counter = row;
					while (counter != 3) {
						comparison = gameTiles[counter + 1][col];
						comparisonVal = comparison.getValue();
						merged = checkForEquality();
						counter++;
					}
				}
			}
		}

		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[column][row]);
			}

			for (int i = list.size() - 1; i >= 0; i--) {
				if (list.get(i).isEmpty()) {
					list.remove(list.get(i));
				}
			}
			while (list.size() != 4) {
				list.addFirst(new Tile());
			}
			for (int i = 0; i < (list.size() - 1); i++) {
				gameTiles[i][row] = list.get(i);
			}
		}
	}

	public void moveUp() {
		for (int row = 0; row < gameTiles.length; row++) {
			for (int col = 0; col < (gameTiles[col].length - 1); col++) {
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					comparison = gameTiles[row + 1][col];
					comparisonVal = comparison.getValue();
					checkForEquality();
				}
			}
		}
	}

	public ArrayList<Tile> emptySlots() {
		ArrayList<Tile> empty = new ArrayList<Tile>();
		for (Tile[] gameTile : gameTiles) {
			for (Tile element : gameTile) {
				if (element.isEmpty()) {
					empty.add(element);

				}
			}
		}
		return empty;
	}

	private boolean checkForEquality() {
		if (comparisonVal == currentVal) {
			comparison.setValue(0);
			current.setValue(currentVal + comparisonVal);
			return true;
		}
		return false;
	}

	public void print() {
		// StringBuilder builder= new StringBuilder();
		for (Tile[] gameTile : gameTiles) {
			for (Tile element : gameTile) {
				System.out.print(element.getValue() + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		GameLogic logic = new GameLogic();
		logic.addTile();
		logic.addTile();
		logic.print();
		System.out.print("\nMoving left\n");
		logic.moveLeft();
		logic.print();
		System.out.print("\nAdding tile\n");
		logic.addTile();
		logic.print();
		System.out.print("\nMoving left\n");
		logic.moveLeft();
		logic.print();
		System.out.print("\nAdding tile\n");
		logic.addTile();
		logic.print();
		System.out.print("\nMoving Down\n");
		logic.moveDown();
		logic.print();

	}

	public Tile[][] getTiles() {
		return gameTiles;
	}
}
