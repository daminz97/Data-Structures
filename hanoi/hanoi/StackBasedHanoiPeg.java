package hanoi;

import structures.LinkedStack;
import structures.StackInterface;


/**
 * A {@link StackBasedHanoiPeg} is an implementation of {@link HanoiPeg}.
 * 
 * @author jcollard
 */
public class StackBasedHanoiPeg implements HanoiPeg {
	//StackInterface<HanoiRing> stack;
	StackInterface<HanoiRing> stack;

	/**
	 * Creates a new {@link StackBasedHanoiPeg} that has no rings.
	 */
	public StackBasedHanoiPeg() {
		this.stack = new LinkedStack<HanoiRing>();
	}

	@Override
	public void addRing(HanoiRing ring) throws IllegalHanoiMoveException {
		if (ring == null)
			throw new IllegalHanoiMoveException("Input is null");
		else{
			if(stack.isEmpty())
				stack.push(ring);
			else{
				if(ring.getSize() >= getTopRing().getSize())
					throw new IllegalHanoiMoveException("Illegal!");
				else{
					stack.push(ring);
				}
			}
		}
	}

	@Override
	public HanoiRing remove() throws IllegalHanoiMoveException {
		if (stack.isEmpty())
			throw new IllegalHanoiMoveException("Illegal");
		return stack.pop();
	}

	@Override
	public HanoiRing getTopRing() throws IllegalHanoiMoveException {
		if (stack.isEmpty())
			throw new IllegalHanoiMoveException("Illegal");
		return stack.peek();
	}

	@Override
	public boolean hasRings() {
		if(stack.isEmpty())
			return false;
		return true;
	}
}
