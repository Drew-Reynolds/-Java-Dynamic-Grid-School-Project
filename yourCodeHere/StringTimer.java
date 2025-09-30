// TO DO: add your implementation and JavaDoc

/**
 * A String repeater.
 * @author Drew Reynolds
 */
public class StringTimer implements Combiner<String, Integer, String> {
	
	/**
	 * Muliplies the operand1 operand 2 times.
	 * @param operand1 Word to be repeated
	 * @param operand2 Number of times to repeat the word
	 * @return operand1 repeated operand2 number of times
	 */
	public String combine(String operand1, Integer operand2){
		// return a string as a repetition of the original string operand1
		// the number of repeats is specified by integer operand2
		// e.g. combine("hat",3) returns "hathathat"
		// return empty string if operand2 is 0 or negative
		
		// O(NL) where N is the value of operand2 and L is the length of operand1
		// Hint: remember the big-O of copying a string...
		String output = "";
		for (int i = 0; i < operand2; i++) {
			output = output + operand1;
		}
		return output;
	}
	
	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	/**
	 * main method.
	 * @param args cmd arguments
	 */
	public static void main(String[] args){
		StringTimer st = new StringTimer();
		if (st.combine("a",1).equals("a") && st.combine("ab",3).equals("ababab")
			&& st.combine("abc",-1).equals("")) {
			System.out.println("Yay 1");
		}

	}
}