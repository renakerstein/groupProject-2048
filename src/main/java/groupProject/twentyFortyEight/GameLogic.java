package groupProject.twentyFortyEight;

import java.util.ArrayList;

public class GameLogic {

	private Tile[][] gameTiles;
	private ArrayList<Tile> availableSlots;
	private Tile chosenSlot;
	private int currentVal, comparisonVal;
	private Tile current, comparison;

	public GameLogic() {
		this.gameTiles = new Tile[4][4];
		this.availableSlots = new ArrayList<Tile>();
	}

	public Tile addTile(int row, int column, int value) {
		availableSlots = emptySlots();
		if (!availableSlots.isEmpty()) {
			int index = (int) (Math.random() * availableSlots.size())
					% availableSlots.size();
			chosenSlot = availableSlots.get(index);
			chosenSlot.setValue(Math.random() < 0.9 ? 2 : 4);
		}
		return chosenSlot; // get row and column of this specific tile

	}

	public void moveLeft() {
		for (int row = 0; row < gameTiles.length; row++) {
			for (int col = 3; col < gameTiles[row].length-1; col--) {
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					comparison = gameTiles[row][col--];
					comparisonVal = comparison.getValue();
					checkForEquality();
				}
			}
		}
	}
	
	public void moveRight(){
		for(int row=0; row<gameTiles.length; row++){
			for(int col=0; col<gameTiles[col].length-1; col++){
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					comparison = gameTiles[row][col++];
					comparisonVal = comparison.getValue();
					checkForEquality();
				}
			}
		}
	}

	public void moveUp(){
		for(int row=3; row<gameTiles.length; row--){
			for(int col=0; col<gameTiles[col].length-1; col++){
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					comparison = gameTiles[row--][col];
					comparisonVal = comparison.getValue();
					checkForEquality();
				}
			}
		}
	}
	
	public void moveDown(){
		for(int row=0; row<gameTiles.length; row++){
			for(int col=0; col<gameTiles[col].length-1; col++){
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					comparison = gameTiles[row++][col];
					comparisonVal = comparison.getValue();
					checkForEquality();
				}
			}
		}
	}
	public ArrayList<Tile> emptySlots() {
		ArrayList<Tile> empty = new ArrayList<Tile>();
		for (int row = 0; row < gameTiles.length; row++) {
			for (int column = 0; column < gameTiles[row].length; column++) {
				if (gameTiles[row][column].isEmpty()) {
					empty.add(gameTiles[row][column]);

				}
			}
		}
		return empty;
	}
	
	private void checkForEquality(){
		if (comparisonVal == currentVal) {
			current.setValue(0);
			comparison.setValue(currentVal + comparisonVal);
		}
	}
}
