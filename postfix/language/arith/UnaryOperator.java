package language.arith;

import language.Operand;
import language.Operator;


public abstract class UnaryOperator<T> implements Operator<T> {
	protected Operand<T> op0;

	@Override
	public int getNumberOfArguments() {
		// TODO See the comment at the top of this class.
		return 1;
	}

	@Override
	public void setOperand(int i, Operand<T> operand) {
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
