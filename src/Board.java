//Mainly the logic of a board
public class Board {
	private boolean[][] life = new boolean[120][120];
	public Board(){
		//A default constructor which sets all the cells to false
		for (int i = 0; i < life.length; i++) {
			for (int j = 0; j < life[i].length; j ++) {
				life[i][j] = false;
			}
		}
	}
	public Board(Boolean[][] n) {
		//Taking in a 2D boolean array as a constructor
		for (int i = 0; i < life.length; i++) {
			for (int j = 0; j < life[i].length; j ++) {
				life[i][j] = n[i][j];
			}
		}
	}
	public Board(Board n) {
		//Taking in a Board object directly
		//I didn't copy the reference. Reference type issues...
		for (int i = 0; i < life.length; i++) {
			for (int j = 0; j < life[i].length; j ++) {
				life[i][j] = n.getLife()[i][j];
			}
		}
	}
	public boolean[][] getLife(){
		//return the 2D array
		return life;
	}
	
	public boolean getValue(int x, int y) {
		//Getting a value of a specific cell
		return life[x][y];
	}
	public void setLife(int x, int y, boolean value) {
		//Setting a value of a specific cell
		life[x][y] = value;
	}
	public boolean isAlive(int x, int y) { 
		//Judging whether a cell is alive or not
		boolean[] n = new boolean[8]; //An array of all the neighbors a cell
		//Prev and Next are helper methods which return the index of the previous or next index
		//Torus shape
		n[0] = life[prev(x)][prev(y)];
		n[1] = life[prev(x)][y]; 
		n[2] = life[prev(x)][next(y)];
		n[3] = life[x][prev(y)];
		n[4] = life[x][next(y)];
		n[5] = life[next(x)][prev(y)];
		n[6] = life[next(x)][y];
		n[7] = life[next(x)][next(y)];
		int count = 0;
		//Counting the number of neighbours which are alive
		for (boolean a: n) { //For each loop
			if (a) {
				count ++;
			}
		}
		//Rules of Game of Life
		if (life[x][y]) { //If the cell is alive
			if (count < 2) { //If it has less than two living neigbours
				return false; //Dead
			}
			if (count == 2 || count == 3) {  //If it has two or three neighbours
				return true; 
			}
			if (count > 3) {
				return false;
			}
		} else {
			if (count == 3) {
				return true;
			}
		}
		return false;
	}
	public int prev(int x) {
		if (x != 0) {
			return x-1;
		} else {
			return life.length-1;
		}
	}
	public int next(int x) {
		if (x == life.length-1) {
			return 0;
		} else {
			return x+1;
		}
	}
}
