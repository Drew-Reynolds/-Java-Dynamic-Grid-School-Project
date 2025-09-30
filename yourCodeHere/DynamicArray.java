// TO DO: add your implementation and JavaDocs

/**
 * A dynamic array that's able to grow and shrink with less computations than a normal array.
 * @author Drew Reynolds
 * @param <T> element type in the array
 */
public class DynamicArray<T> {
	
	/**
	 * class variable.
	 * @param default initial capacity / minimum capacity
	 */
	private static final int INITCAP = 2;

	/**
	 * class variable.
	 * @param underlying array
	 */
	private T[] storage;

	/**
	 * class variable.
	 * @param number of elements in the storage array, excluding null values
	 */
	private int size;
	
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

	/**
	 * Constructor when no capasity is given.
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray(){
		// constructor
		// initial capacity of the array should be INITCAP
		this.storage = (T[])new Object[INITCAP];
		this.size = 0;
	}

	/**
	 * Constructor.
	 * @param initCapacity Inital capasity of the array
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray(int initCapacity){
		// constructor
		// set the initial capacity of the array as initCapacity
		// throw IllegalArgumentException if initCapacity < 1
		if (initCapacity < 1) {
			throw new IllegalArgumentException();
		}
		this.storage = (T[])new Object[initCapacity];
		this.size = 0;
	}

	/**
	 * Equivalent to an array's .length.
	 * @return The length of the array
	 */
	public int size() {	  
		//report the number of elements in the list
		// O(1)
		return size;
	}
	
	/**
	 * The size() method that includes the null elements.
	 * @return The full capacity of the array
	 */
	public int capacity() { 
		//report the max number of elements before the next expansion
		// O(1)

		return storage.length;
	}
	
	/**
	 * Sets an element in the array to another element and returns the removed element.
	 * @param index Index of the element to be replaced
	 * @param value Value of the new element
	 * @return The removed value
	 */
	public T set(int index, T value){
		// change item x at index i to be value	
		// return old item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		
		// Note: you cannot add new items with this method
		if (index >= size || index < 0) {
			System.out.println(index + " " +  size);
			throw new IndexOutOfBoundsException();
		}
		T oldValue = storage[index];
		storage[index] = value;
		return oldValue;
	}

	/**
	 * Get's the value of the element in the array at the index.
	 * @param index Index of the element needed to get
	 * @return The element at the index
	 */
	public T get(int index){
		// return the item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)

		if (index >= capacity() || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		return storage[index];
	}

	/**
	 * Adds a value to the end of the array.
	 * @param value Value to be added
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean add(T value){
		// add value to the end of the list (append)
		// return true	 
		
		// double the capacity if no space is available
		// amortized O(1)
		
		// Note: Remember... code reuse is awesome...
		add(size, value); // Calls the other add() method with the index of the next empty space
		return true;
	}
	
	/**
	 * Adds a value at the stated index, all elements at and after the index are shifted one to the right.
	 * @param index Index of where the element will be added
	 * @param value Value to be added at the index
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value){
		// insert value at index, shift elements if needed  
		// double the capacity if no space is available
		// throw IndexOutOfBoundsException for invalid index
		// O(N) where N is the number of elements in the list
		
		// Note: this method may be used to append items as
		// well as insert items

		// if capacity is full, create a new array with double capasity and copy everything over; doesn't add the element
		if (size + 1 >= storage.length) {
			T[] newStorage = (T[])new Object[storage.length * 2];
			for (int i = 0; i < size(); i++) { // For every element
				newStorage[i] = storage[i];
			}
			storage = newStorage;
		}

		// Add the value and store the value that was replaced
		T oldValue = storage[index]; 
		storage[index] = value;

		
		if (oldValue != null) { // if not at the end of the array, recursivly go until you are at the end of the array
			add(index + 1, oldValue);
		}
		else { // if at the end of the array
			size++;
		}

	}
	
	/**
	 * Removes an element at the given index, all elements after the index are shifted one to the left.
	 * @param index Index of the element to be removed
	 * @return The removed element
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index){
		// remove and return element at position index
		// shift elements to remove any gap in the list
		// throw IndexOutOfBoundsException for invalid index
		
		// halve capacity if the number of elements falls below 1/3 of the capacity
		// capacity should NOT go below INITCAP
		
		// O(N) where N is the number of elements in the list


		if (index < 0 || index > storage.length || storage[index] == null) {
			throw new IndexOutOfBoundsException();
		}

		// if the new size is less than 1/3, remove half of the capacity; doesn't remove the element yet
		if (((double)(size - 1)) / (storage.length) < (double)1/3) { 
			T[] newStorage = (T[])new Object[storage.length / 2];
			for (int i = 0; i < newStorage.length; i++) { // For every element
				newStorage[i] = storage[i];
			}
			storage = newStorage;
		}

		// Shifts all the values left one, until at the index

		T value = null; // The value to be shifted left
		T oldValue = null; // The replaced value
		for (int i = size - 1; i >= index; i--) {
			oldValue = storage[i];
			storage[i] = value;
			value = oldValue;
		}

		size--;
		return oldValue;
	}  
	
	
	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------


	// Not required, update for your own testing
	@Override
	public String toString(){
		// return string representation of DynamicArray
		// update if you want to include more information 
		String elements = "";
		for (int i = 0; i < storage.length; i++) {
			elements = elements + "[" + storage[i] + "] ";
		}
		return "Size: " + size() + ", Capacity: " + capacity() + "\n" + elements;
		
  	}
  	
	/**
	 * main method.
	 * @param args cmd arguments
	 */
	public static void main (String args[]){
		// new list?
		DynamicArray<Integer> ida = new DynamicArray<>();
		if ((ida.size() == 0) && (ida.capacity() == 2)){
			System.out.println("Yay 1");
		}

		// adding to the list?
		boolean ok = true;
		for (int i=0; i<3;i++)
			ok = ok && ida.add(i*5);
		
		if (ok && ida.size()==3 && ida.get(0) == 0 && ida.get(1) == 5 && ida.get(2) == 10 && ida.capacity() == 4 ){
			System.out.println("Yay 2");
		}
		
		ida.add(1,-10);
		ida.add(4,100);
		if (ida.set(1,-20)==-10 && ida.get(2) == 5 && ida.size() == 5 
			&& ida.capacity() == 8 ){
			System.out.println("Yay 3");
		}
		
		// removing from the list?
		if (ida.remove(0) == 0 && ida.remove(0) == -20 && ida.remove(2) == 100 
			&& ida.size() == 2 && ida.capacity() == 4 ){
			System.out.println("Yay 4");
		}
		
		// remember to tests more things...
		System.out.println();

		DynamicArray<String> stringArray = new DynamicArray<>(15);
		System.out.println(stringArray);
		for (int i = 0; i < 15; i ++) {
			stringArray.add("" + (i + 1));
		}
		System.out.println(stringArray);
		for (int i = 5; i < 10; i++) {
			stringArray.add(5, "[" + i + "]");
		}
		System.out.println(stringArray);
		for (int i = 0; i < 11; i++) {
			stringArray.remove(0);
		}
		System.out.println(stringArray);
	}

}