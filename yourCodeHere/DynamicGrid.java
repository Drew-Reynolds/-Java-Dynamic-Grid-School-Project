// TO DO: add your implementation and JavaDoc
/**
 * A dynamic 2D array that's able to grow and shrink with less computations than a normal 2D array.
 * @author Drew Reynolds
 * @param <T> Type of element in the 2D array
 */
public class DynamicGrid<T> {

	/**
	 * class variable.
	 * @param storage holds the 2D array
	 */
	private DynamicArray<DynamicArray<T>> storage;	// underlying storage
	// HINT: Read the big-O requirements of the methods below to determine
	// how the columns/rows should be stored in storage.
	
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

	/**
	 * Constructor; Needs to add 2 dynamic arrays so that the capasity for both rows and columns is 2; The DynamicGrid is still filled with nulls.
	 */
	public DynamicGrid(){
		// constructor
		// create an empty table of 0 rows and 0 cols
		storage = new DynamicArray<>();
	}

	/**
	 * gets the number of rows, excluding unocupied.
	 * @return the number of rows in the dynamic grid
	 */
	public int getNumRow() {
		// return number of rows in the grid
		// O(1)
		return storage.size();
	}
	
	/**
	 * Gets the number of columns, excluding unocupied.
	 * @return the number of columns in the dynamic grid
	 */
	public int getNumCol() { 
		// return number of columns in the grid
		// O(1)

		// if no columns have been added, or all removed, all columns are null and therefor have a length of 0
		if (storage.get(0) == null) {
			return 0;
		}

		return storage.get(0).size();
	}

	/**
	 * Gets the number of rows, excluding empty ones.
	 * @return number of occupied rows
	 */
	private int getRowCapacity() {
		return storage.capacity();
	}

	/**
	 * Gets the number of columns, excluding empty ones.
	 * @return number of occupied columns
	 */
	private int getColCapacity() {

		// if no columns have been added, or all removed, all columns are null and therefor have a length of 0
		if (storage.get(0) == null) {
			return 0;
		}
		
		return storage.get(0).capacity();
	}
	
	/**
	 * Get's a value at index [indexRow][indexCol].
	 * @param indexRow index of the row
	 * @param indexCol index of the column
	 * @return The element at the index
	 */
	public T get(int indexRow, int indexCol){
		// return the value at the specified row and column
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		return storage.get(indexRow).get(indexCol); // Exception handled by .get() method
	}
	
	/**
	 * Sets the value at index [indexRow][indexCol].
	 * @param indexRow index of the row
	 * @param indexCol index of the column
	 * @param value value to be set at index [indexRow][indexCol
	 * @return the removed value
	 */
	public T set(int indexRow, int indexCol, T value){
		// change value at the specified row and column to be value
		// return the old value
		// throw IndexOutOfBoundsException for invalid index
		// O(1)

		// Note: this can not be used to add new items, only replace
		// existing items.

		T oldValue = storage.get(indexRow).get(indexCol);
		storage.get(indexRow).set(indexCol, value);
		return oldValue;
	}
	
	/**
	 * Adds a new row at the given index; Every row at or after the index is shifted down by one.
	 * @param index Index of the row to be replaced
	 * @param newRow The new row to be added
	 * @return false if the index is invalid; true if the row was successfully added
	 */
	public boolean addRow(int index, DynamicArray<T> newRow){
		// copy values from newRow to add a row at the row index specified
		// cannot add if the length of newRow does not match existing rows
		// 
		// Note: make a deep copy of the incoming row for insertion
		//
		// return true if newRow can be added correctly
		// return false otherwise
		// 
		// O(C+R) where R is the number of rows and C is the number of columns of the grid
		// Hint: Remember the big-O of the underlying DynamicArray of DynamicArrays
		
		// Note: this can be used to append rows as well as insert rows

		// If the new row has the same amount of columns as the rest of the rows, excluding when the grid is empty OR
		// If the index is less than 0 OR
		// If the index is greater than the last row's index + 1 (Would be able to leave a bank row inbetween without this condition)
		if ((newRow.size() != getNumCol() && getNumCol() != 0) || (index < 0) || (index > getNumRow())) {
			return false;
		}
		// System.out.println("Adding...");
		storage.add(index, newRow);
		return true;
	}
	
	/**
	 * Adds a new column at the given index, every column after the index shifts one to the right.
	 * @param index The index of the new row
	 * @param newCol The new column to be added at the index
	 * @return false if the index is invalid; true if the column was successfully added
	 */
	public boolean addCol(int index, DynamicArray<T> newCol){
		// copy values from newCol to add a column at the column index specified
		// cannot add if the length of newCol does not match existing columns
		//
		// return true if newCol can be added correctly
		// return false otherwise
		// 
		// O(RC) where R is the number of rows and C is the number of columns of the grid
		// Hint: Remember the big-O of the underlying DynamicArray of DynamicArrays
		
		// Note: this can be used to append columns as well as insert columns

		// If the new column has the same amount of rows as the rest of the columns, excluding when the grid is empty OR
		// If the index is less than 0 OR
		// If the index is greater than the last column's index + 1 (Would be able to leave a bank column inbetween without this condition)
		if ((newCol.size() != getNumRow() && getNumRow() != 0) || (index < 0) || (index > getNumCol())) {
			return false;
		}

		for (int r = 0; r < newCol.size(); r++) {
			storage.get(r).add(index, newCol.get(r));
		}
		return true;
	}
	
	/**
	 * Removes a row at the given index; every row after the index is shifted up one.
	 * @param index Index of the row to be removed
	 * @return the removed row
	 */
	public DynamicArray<T> removeRow(int index){
		// remove and return a row at index x
		// shift rows to remove the gap
		// throw IndexOutOfBoundsException for invalid index
		// Hint: Use the underlying storage to do 90% of this...
		//
		// O(R) where R is the number of rows of the grid

		DynamicArray<T> oldRow = storage.get(index);
		storage.remove(index);
		return oldRow;
		
	}

	/**
	 * Removes a column at the given index; every column after the index shifts one to the right.
	 * @param index index of the column to be removed
	 * @return the removed column in it's own DynamicArray
	 */
	public DynamicArray<T> removeCol(int index){
		System.out.println("removeCol() called");
		// remove and return a col at index x
		// shift columns to remove the gap
		// throw IndexOutOfBoundsException for invalid index
		// Hint: Use the underlying storage to do 90% of this...
		//
		// O(RC) where R is the number of rows and C is the number of columns

		DynamicArray<T> oldCol = new DynamicArray<>(getNumRow());
		for (int row = 0; row < getNumRow(); row++) {
			oldCol.add(storage.get(row).remove(index));
		}
		return oldCol;
	}


	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------


	// Not required, update for your own testing
	@Override
	public String toString(){
		String header = "Rows: " + getNumRow() + ", Cols: " + getNumCol() +"\nRowCap: " + getRowCapacity() + ", ColCap: " + getColCapacity();
		String contents = "";
		for (int r = 0; r < getNumRow(); r++) {
			for (int c = 0; c < getNumCol(); c++) {
				contents = contents + "[" + storage.get(r).get(c) + "] ";
			}
			if (r != getNumRow() - 1) {
				contents = contents + "\n";
			}
		}
		return header + "\n" + contents;
	}
	
	
	/**
	 * main method.
	 * @param args cmd arguments
	 */
	public static void main(String[] args){
		// make some simple grids
		DynamicGrid<String> sgrid = new DynamicGrid<>();
		DynamicArray<String> srow = new DynamicArray<>();
		srow.add("English");
		srow.add("Spanish");
		srow.add("German");

		//System.out.println("Test 1 start");
		//System.out.println(sgrid);
		//System.out.println(sgrid.getNumRow() == 0);
		//System.out.println(sgrid.getNumCol() == 0);
		if (sgrid.getNumRow() == 0 && sgrid.getNumCol() == 0 && sgrid.addRow(0,srow)
			&& sgrid.getNumRow() == 1 && sgrid.getNumCol() == 3){
			System.out.println("Yay 1");
		}
		//System.out.println(sgrid.getNumRow() == 1);
		//System.out.println(sgrid.getNumCol() == 3);
		//System.out.println(sgrid);
		//System.out.println("Test 1 over");
		
		if (sgrid.get(0,0).equals("English") && sgrid.set(0,1,"Espanol").equals("Spanish") 
			&& sgrid.get(0,1).equals("Espanol")) {
			System.out.println("Yay 2");
		}

		// more complicated grids
		DynamicGrid<Integer> igrid = new DynamicGrid<Integer>();
		boolean ok = true;

		for (int i=0; i<3; i++){
			DynamicArray<Integer> irow = new DynamicArray<>();
			irow.add( (i+1) *10);
			ok = ok && igrid.addRow(igrid.getNumRow(),irow);
		}
		if (ok && igrid.getNumRow() == 3 && igrid.getNumCol() == 1 && igrid.get(2,0)==30) {
			System.out.println("Yay 3");
		}
		
		DynamicArray<Integer> icol = new DynamicArray<>();
		icol.add(-10);
		icol.add(-20);
		System.out.println(icol);
		ok = igrid.addCol(1,icol);
		icol.add(-30);
		if (!ok && igrid.addCol(1,icol) && igrid.getNumRow() == 3 && igrid.getNumCol() == 2){
			System.out.println("Yay 4");		
		}
		
		DynamicArray<Integer> irow = new DynamicArray<>();
		irow.add(5); irow.add(10);
		//System.out.println(igrid);
		//System.out.println(!igrid.addRow(5,irow));
		//System.out.println(igrid);
		if (!igrid.addRow(5,irow) && igrid.addRow(0,irow) && igrid.getNumRow() == 4 &&
			igrid.get(0,0) == 5 && igrid.get(3,1) == -30){
			System.out.println("Yay 5");		
		}
		
		irow = igrid.removeRow(2);
		if (igrid.getNumRow() == 3 && igrid.getNumCol() == 2 && irow.get(0) == 20 &&
			igrid.get(0,1) == 10 && igrid.get(2,0) == 30){
			System.out.println("Yay 6");		
		}	
		
		DynamicArray<String> srow1 = new DynamicArray<>();
		for (int i = 0; i < 5; i++) {
			srow1.add("A" + (i+1));
		}
		DynamicArray<String> srow2 = new DynamicArray<>();
		for (int i = 0; i < 5; i++) {
			srow2.add("B" + (i+1));
		}
		DynamicArray<String> srow3 = new DynamicArray<>();
		for (int i = 0; i < 5; i++) {
			srow3.add("C" + (i+1));
		}
		DynamicArray<String> srow4 = new DynamicArray<>();
		for (int i = 0; i < 5; i++) {
			srow4.add("D" + (i+1));
		}

		sgrid = new DynamicGrid<>();
		sgrid.addRow(0, srow4);
		sgrid.addRow(0, srow3);
		sgrid.addRow(0, srow2);
		sgrid.addRow(0, srow1);

		System.out.println(sgrid);

		sgrid.removeCol(1);

		System.out.println(sgrid);
	
	}

}