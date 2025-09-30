// TO DO: add your implementation and JavaDoc

/**
 * A table that changes what's inside the cells based on its headers.
 * @author Drew Reynolds
 * @param <RowT> Generic of the row head
 * @param <ColT> Generic of the column head
 * @param <CellT> Generic of the table
 * @param <OpT> Generic of the operation
 */
public class Table<RowT, ColT, CellT, OpT extends Combiner<RowT, ColT, CellT>> {

	/**
	 * Class variable.
	 * @param rowHead header for all the rows
	 */
	private DynamicArray<RowT> rowHead;

	/**
	 * Class variable.
	 * @param colHead header for all the columns
	 */
	private DynamicArray<ColT> colHead;

	/**
	 * Class variable.
	 * @param board 2D grid of of CellT values determined by rowHead, colHead, and op
	 */
	private DynamicGrid<CellT> board;

	/**
	 * Class variable.
	 * @param op The operation that combines two elements of colHead and rowHead
	 */
	private OpT op;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

	/**
	 * Constructor.
	 * @param op The operation for this object
	 */
	public Table(OpT op) {
		// constructor
		// create an table of empty rowHead and colHead, board of 0 rows and 0 cols
		// set the operator

		this.rowHead = new DynamicArray<>();
		this.colHead = new DynamicArray<>();
		this.board = new DynamicGrid<>();
		this.op = op;
	}

	/**
	 * Row num getter.
	 * @return number of rows
	 */
	public int getSizeRow() {
		// report the number of rows in board
		// O(1)

		return board.getNumRow();
	}

	/**
	 * Column num getter.
	 * @return number of columns
	 */
	public int getSizeCol() {
		// report the number of columns in board
		// O(1)

		return board.getNumCol();

	}

	/**
	 * rowHead element getter.
	 * @param r index of the row
	 * @return the row at index r
	 */
	public RowT getRowHead(int r) {
		// return the item at index r from rowHead
		// throw IndexOutOfBoundsException for invalid index
		// O(1)

		return rowHead.get(r); // Exception handled by .get() method
	}

	/**
	 * rowCol element getter.
	 * @param c index of the column
	 * @return the column at index c
	 */
	public ColT getColHead(int c) {
		// return the item at index c from colHead
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		
		return colHead.get(c); // Exception handled by .get() method
	}

	/**
	 * board element getter.
	 * @param r row index
	 * @param c column index
	 * @return element at board[row][column]
	 */
	public CellT getCell(int r, int c) {
		// return the cell at row r, column c from board
		// throw IndexOutOfBoundsException for invalid index
		// O(1)

		return board.get(r, c); // Exception handled by .get() method
	}

	/**
	 * Sets the operation to a new operation and sets all elements in the table to reflect the new operation.
	 * @param op the new operation
	 */
	public void setOp(OpT op) {
		// change the operation
		// re-calculate and reset the cells of the board TODO
		//
		// O(CR) where C is the number of columns and R is the number of rows of the
		// grid

		this.op = op;

		// Recalculating every cell
		for (int row = 0; row < getSizeRow(); row++) {
			for (int col = 0; col < getSizeCol(); col++) {
				board.set(row, col, op.combine(rowHead.get(row), colHead.get(col)));
			}
		}
	}

	/**
	 * Adds a new row to board and a new element to rowHead.
	 * @param i index of the new row
	 * @param v element to be put into rowhead at index i
	 * @return true if successfully added
	 */
	public boolean addRow(int i, RowT v) {
		// insert v to rowHead at index i
		// also insert a new row to the grid at row index i
		// calculate the new row based on v, existing colHead and op
		//
		// i may be equal to the size (indicating that you are appending a row)
		//
		// O(C+R) where R is the number of rows of the grid and
		// C is the number of columns of the grid

		// Adding element v to rowHead at i
		rowHead.add(i, v);

		// Setting elements in the new row (uses the add() method because the new row is empty)
		DynamicArray<CellT> newRow = new DynamicArray<>();
		for (int col = 0; col < board.getNumCol(); col++) {
			newRow.add(op.combine(rowHead.get(i), colHead.get(col)));
		}

		// Adding newRow to the board at index i
		board.addRow(i, newRow);

		return true;

	}

	/**
	 * Adds a new column to board and a new element to colHead.
	 * @param i index of the new column
	 * @param v element to be put into colhead at index i
	 * @return true if successfully added
	 */
	public boolean addCol(int i, ColT v) {
		// insert v to colHead at index i
		// also insert a new column to the grid at column index i
		// calculate the new column based on v, existing rowHead and op
		// i may be equal to the size (indicating that you are appending a column)
		//
		// O(CR) where R is the number of rows of the grid and
		// C is the number of columns of the grid

		// Adding element v to colHead at index i
		colHead.add(i, v);

		// Setting elements in the new column (uses the add() method because the new column is empty); 
		DynamicArray<CellT> newCol = new DynamicArray<>();
		for (int row = 0; row < board.getNumRow(); row++) {
			newCol.add(op.combine(rowHead.get(row), colHead.get(i)));
		}

		// Adding newCol to the coard at index i
		board.addCol(i, newCol);

		return true;

	}

	/**
	 * Removes a row and its RowT element from index i.
	 * @param i index of the removed elements
	 * @return the removed Rowt element
	 */
	public RowT removeRow(int i) {
		// remove and return value from rowHead at index i
		// also remove row i from grid
		// throw IndexOutOfBoundsException for invalid index
		//
		// O(R) where R is the number of rows of the grid

		// Removing the row at index i
		board.removeRow(i);

		// Removing the RowT at index i
		RowT oldRowT = rowHead.get(i);
		rowHead.remove(i);

		return oldRowT;

	}

	/**
	 * Remove the column and its ColT element from index i.
	 * @param i index of the removed elements
	 * @return the removed ColT element
	 */
	public ColT removeCol(int i) {
		// remove and return value from colHead at index i
		// also remove column i from grid
		// throw IndexOutOfBoundsException for invalid index
		//
		// O(CR) where R is the number of rows and
		// C is the number of columns of the grid

		// Removing the column and index i
		board.removeCol(i);

		// Removing the ColT element at index i
		ColT oldColT = colHead.get(i);
		colHead.remove(i);

		return oldColT;

	}

	/**
	 * Sets an element in rowHead at index i to v and resets the associated row.
	 * @param i index of the row
	 * @param v value to replace the element in rowHead at index i
	 * @return the removed element in rowHead at index i
	 */
	public RowT setRow(int i, RowT v) {
		// change value of rowHead at index i to be v
		// also change the ith row of grid using v, the ColTs, and op
		// return old value of rowHead from index i
		// throw IndexOutOfBoundsException for invalid index
		//
		// O(C) where C is the number of columns of the grid

		// Setting the value of rowHead at i to v
		RowT oldRowT = rowHead.get(i);
		rowHead.set(i, v);

		// Setting the values of the new row
		for (int col = 0; col < board.getNumCol(); col++) {
			board.set(i, col, op.combine(rowHead.get(i), colHead.get(col)));
		}

		return oldRowT;

	}

	/**
	 * Sets an element in colHead at index i to v and resets the associated column.
	 * @param i index of the column
	 * @param v value to replace the element in colHead at index i
	 * @return the removed element in colHead at index i
	 */
	public ColT setCol(int i, ColT v) {
		// change value of colHead at index i to be v
		// also change the ith column of grid using v, the RowTs, and op
		// return old value of colHead from index i
		// throw IndexOutOfBoundsException for invalid index
		//
		// O(R) where R is the number of rows of the grid

		// Setting the value of colHead at i to v
		ColT oldColT = colHead.get(i);
		colHead.set(i, v);

		// Setting the values of the new row
		for (int row = 0; row < board.getNumRow(); row++) {
			board.set(row, i, op.combine(rowHead.get(row), colHead.get(i)));
		}

		return oldColT;

	}

	// --------------------------------------------------------
	// PROVIDED for you to help with testing
	// More testing code you can change further down...
	// --------------------------------------------------------

	/**
	 * Find the width we should use to print the specified column.
	 * 
	 * @param colIndex column index to specify which column of the grid to check
	 *                 width
	 * @return an integer to be used to for formatted printing of the column
	 */

	private int getColMaxWidth(int colIndex) {
		int ans = -1;
		for (int i = 0; i < this.getSizeRow(); i++) {
			int width = (this.getCell(i, colIndex)).toString().length();
			if (ans < width)
				ans = width;
		}
		return ans + 1;
	}

	/**
	 * Find the width we should use to print the rowHead.
	 * 
	 * @return an integer to be used to for formatted printing of the rowHead
	 */
	private int getRowHeadMaxWidth() {
		int ans = -1;
		for (int i = 0; i < this.getSizeRow(); i++) {
			int width = (rowHead.get(i)).toString().length();
			if (ans < width)
				ans = width;
		}
		return ans + 1;
	}

	/**
	 * Construct a string representation of the table.
	 * 
	 * @return a string representation of the table
	 */

	@Override
	public String toString() {

		if (getSizeRow() == 0 && getSizeCol() == 0) {
			return "Empty Table";
		}

		// basic info of op and size
		StringBuilder sb = new StringBuilder("============================\nTable\n");
		sb.append("Operation: " + op.getClass() + "\n");
		sb.append("Size: " + getSizeRow() + " rows, " + getSizeCol() + " cols\n");

		// decide how many chars to use for rowHead
		int rowHeadWidth = getRowHeadMaxWidth();
		int totalWidth = rowHeadWidth;
		DynamicArray<Integer> colWidths = new DynamicArray<>();
		sb.append(String.format(String.format("%%%ds", rowHeadWidth), " "));

		// colHead
		for (int i = 0; i < getSizeCol(); i++) {
			int colWidth = getColMaxWidth(i);
			colWidths.add(colWidth);
			totalWidth += colWidth + 1;
			sb.append(String.format(String.format("|%%%ds", colWidth), colHead.get(i)));
		}

		sb.append("\n" + String.format(String.format("%%%ds", totalWidth), " ").replace(" ", "-") + "\n");

		// row by row
		for (int i = 0; i < getSizeRow(); i++) {
			sb.append(String.format(String.format("%%%ds", rowHeadWidth), rowHead.get(i)));
			for (int j = 0; j < getSizeCol(); j++) {
				int colWidth = colWidths.get(j);
				sb.append(String.format(String.format("|%%%ds", colWidth), board.get(i, j)));
			}
			sb.append("\n");
		}
		sb.append("============================\n");
		return sb.toString();

	}

	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	/**
	 * main method.
	 * @param args cmd arguments
	 */
	public static void main(String[] args) {

		StringAdder sa = new StringAdder();
		Table<String, String, String, StringAdder> stable = new Table<>(sa);
		stable.addRow(0, "red");
		stable.addRow(1, "yellow");
		stable.addCol(0, "apple"); 

		if (stable.getSizeRow() == 2 && stable.getSizeCol() == 1 &&
				stable.getCell(0, 0).equals("red apple") && stable.getCell(1, 0).equals("yellow apple")) {
			System.out.println("Yay 1");
		}
		// System.out.println(stable.toString());

		stable.addCol(1, "banana");
		stable.addCol(1, "kiwi");
		stable.addRow(2, "green");
		if (stable.getSizeRow() == 3 && stable.getSizeCol() == 3 && stable.getRowHead(2).equals("green")
				&& stable.getColHead(2).equals("banana") && stable.getCell(2, 1).equals("green kiwi")) {
			System.out.println("Yay 2");
		}
		// System.out.println(stable.toString());

		stable.removeRow(0);
		stable.setCol(2, "orange");
		if (stable.getSizeRow() == 2 && stable.getSizeCol() == 3 && stable.getRowHead(0).equals("yellow")
				&& stable.getColHead(2).equals("orange") && stable.getCell(0, 2).equals("yellow orange")) {
			System.out.println("Yay 3");
		}
		// System.out.println(stable.toString());

		Table<Integer, Integer, Integer, IntegerComb> itable = new Table<>(new IntegerAdder());
		for (int i = 0; i < 5; i++) {
			itable.addRow(itable.getSizeRow(), i + 1);
			itable.addCol(0, (i + 1) * 10);
		}
		if (itable.getSizeRow() == 5 && itable.getSizeCol() == 5 && itable.getCell(0, 0) == 51
				&& itable.getCell(4, 0) == 55 && itable.getCell(3, 4) == 14) {
			System.out.println("Yay 4");
		}
		// System.out.println(itable.toString());

		itable.setOp(new IntegerTimer());
		if (itable.getSizeRow() == 5 && itable.getSizeCol() == 5 && itable.getCell(0, 0) == 50
				&& itable.getCell(4, 0) == 250 && itable.getCell(3, 4) == 40) {
			System.out.println("Yay 5");
		}
		// System.out.println(itable.toString());

		Table<String, Integer, String, StringTimer> table = new Table<>(new StringTimer());
		table.addRow(0, "red");
		table.addRow(1, "yellow");
		table.addRow(1, "appplel");
		table.addCol(0, 1); 
		table.addCol(1, 2);
		table.addCol(2, 3);
		System.out.println(table);
		table.setRow(1, "*");
		System.out.println(table);
	}

}