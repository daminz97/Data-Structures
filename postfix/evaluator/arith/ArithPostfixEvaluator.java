package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;

	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator(){
		this.stack = new LinkedStack<Operand<Integer>>(); //TODO initialize your LinkedStack
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		// TODO use all of the things you've built so far to 
		// implement the algorithm for postfix expression evaluation

		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch(type){ 
			case OPERAND:
				//push the operand in the token into stack
				stack.push(token.getOperand());
				break;
			case OPERATOR:
				//integer to store the number of arguments in the token's operator
				int num = token.getOperator().getNumberOfArguments();
				//store the operator in the token
				Operator<Integer> operator = token.getOperator();
				//store the result of the calculation
				Operand<Integer> result;
				//negateoperator case
				if(num == 1){
					operator.setOperand(0, stack.pop());
					result = operator.performOperation();
					stack.push(result);
				}
				//multi, sub, add, div cases
				if(num == 2){
					operator.setOperand(1,stack.pop());
					operator.setOperand(0,stack.pop());
					result = operator.performOperation();
					stack.push(result);
				}
				break;
			default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}
		}

		//TODO What do we return?
		return stack.pop().getValue();
	}
	

}
