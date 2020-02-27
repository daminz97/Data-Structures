package language.arith;

import language.arith.UnaryOperator;
import language.Operand;
import language.Operator;


/**
 * The {@code NegateOperator} is an operator that performs negation on a single integer
 * @author jcollard
 *
 */
public class NegateOperator extends UnaryOperator<Integer>{

	// TODO Have you taken a look at the PlusOperator yet?
	// You will notice that it extends the abstract class BinaryOperator.
	// You should take a moment and review those classes and how they
	// relate to before trying to
	// implement this one. Although it is not required,
	// it might be a good idea to first write
	// an abstract class called UnaryOperator, paralleling 
	// BinaryOperator, that abstracts out all the bits common
	// across UnaryOperators.	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfArguments() {
		// TODO See the comment at the top of this class.
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operand<Integer> performOperation() {
		// TODO See the comment at the top of this class.
		if (op0 == null)
			throw new IllegalStateException("Could not perform operation prior to operands being set.");
		Integer result = 0 - op0.getValue();
		return new Operand<Integer>(result);
	}

	public String toString(){
		return "!";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setOperand(int i, Operand<Integer> operand) {
		// TODO See the comment at the top of this class.

		// TODO Negation on an integer is simply flipping its sign
		// So the negation of some int value i is -i.
		if(operand == null)
			throw new NullPointerException("Could not set null operand.");
		if (i > 0)
			throw new IllegalArgumentException("Could not reach position "+i);
		if (i == 0){
			if(op0 != null)
				throw new IllegalStateException("Position " + i + " has been previously set.");
			op0 = operand;
		}
	}
}
