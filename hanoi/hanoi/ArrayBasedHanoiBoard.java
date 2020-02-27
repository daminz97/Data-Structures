package hanoi;

/**
 * A {@link ArrayBasedHanoiBoard} is a simple implementation of
 * {@link HanoiBoard}
 * 
 * @author jcollard
 * 
 */
public class ArrayBasedHanoiBoard implements HanoiBoard {
	/**
	 * Creates a {@link ArrayBasedHanoiBoard} with three empty {@link HanoiPeg}s
	 * and {@code n} rings on peg 0.
	 */
	HanoiPeg[] arr = new HanoiPeg[3];
	StackBasedHanoiPeg peg0 = new StackBasedHanoiPeg();
	StackBasedHanoiPeg peg1 = new StackBasedHanoiPeg();
	StackBasedHanoiPeg peg2 = new StackBasedHanoiPeg();

	public ArrayBasedHanoiBoard(int n) {
		if(n<0)
			throw new IllegalArgumentException("xxx");
		arr[0] = peg0;
		arr[1] = peg1;
		arr[2] = peg2;
		for(int j = n; j > 0; j--)
		{
			HanoiRing ring = new HanoiRing(j);
			peg0.addRing(ring);
		}
	}

	@Override
	public void doMove(HanoiMove move) throws IllegalHanoiMoveException {
		int fromPeg = move.getFromPeg();
		int toPeg = move.getToPeg();
		if (!isLegalMove(move)) {
			throw new IllegalHanoiMoveException(
					"Could not perform illegal move.");
		}else{
			arr[toPeg].addRing(arr[fromPeg].remove());
		}
	}

	@Override
	public boolean isSolved() {
		if(arr[0].hasRings() == false && arr[1].hasRings()== false)
			return true;
		else
			return false;
	}

	@Override
	public boolean isLegalMove(HanoiMove move) {
		int fromPeg = move.getFromPeg();
		int toPeg = move.getToPeg();
		if(fromPeg == toPeg)
			return false;
		if((arr[fromPeg].hasRings() == false))
			return false;
		if(arr[toPeg].hasRings() == false)
			return true;
		if(arr[fromPeg].getTopRing().getSize() > arr[toPeg].getTopRing().getSize())
			return false;
		return true;
	}
}
