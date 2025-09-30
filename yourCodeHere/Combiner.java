
/**
 * Represents a generic binary operation.
 * @author CS310 GMU
 * @param <A> type of the first operand that the combiner takes
 * @param <B> type of the second operand that the combiner takes
 * @param <C> type of the result that the combiner generates
 */

public interface Combiner<A, B, C> {

	/**
	 * Combine two operands and return the combination result.
	 * @param operand1 first operand, must be of type A
	 * @param operand2 second operand, must be of type B
	 * @return result based on the combination of operand1 and operand2, must be of type C
	 */
	public C combine(A operand1, B operand2);

}