package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import structures.Queue;
/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 * 
 * @author liberato
 *
 */
public class LevelOrderIterator extends FileIterator<File> {
	Queue<File> queue = new Queue<File>();
	File[] arr;
	File root;
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		// TODO 1
		if(!rootNode.exists())
			throw new FileNotFoundException("Doesn't exsit");
		queue.enqueue(rootNode);
		//root = rootNode;
	}
	
	@Override
	public boolean hasNext() {
		// TODO 2
		if(queue.isEmpty())
			return false;
		return true;
	}

	@Override
	public File next() throws NoSuchElementException {
		// TODO 3
		File re;
		if(queue.isEmpty())
			throw new NoSuchElementException("Nothing inside");
		re = queue.dequeue();
		if(re.isDirectory()){
			arr = re.listFiles();
			Arrays.sort(arr);
			for(int i=0; i<arr.length; i++){
				queue.enqueue(arr[i]);
			}
		}
	
		return re;
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
