package structures;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.Timeout;

public class BinarySearchTreeTest {

	private BSTInterface<Integer> emptyTree;
	private BSTInterface<String> oneNodeTree;
	private BSTInterface<String> nodeTree;
	private BSTInterface<String> nodeTree2;
	private BSTInterface<String> nodeTree3;
	private BSTInterface<String> nodeTree4;
	private static final String FOO = "foo";

	@Rule
    public Timeout timeout = new Timeout(1000L, TimeUnit.SECONDS);
	
	@Before
	public void before() {
		emptyTree = new BinarySearchTree<Integer>();
		oneNodeTree = new BinarySearchTree<String>();
		nodeTree = new BinarySearchTree<String>();
		nodeTree2 = new BinarySearchTree<String>();
		nodeTree3 = new BinarySearchTree<String>();
		nodeTree4 = new BinarySearchTree<String>();
		
		oneNodeTree.add(FOO);
		
		nodeTree.add("a");
		nodeTree.add("b");
		nodeTree.add("c");
		nodeTree.add("d");
		
		nodeTree2.add("g");
		nodeTree2.add("c");
		nodeTree2.add("k");
		nodeTree2.add("a");
		nodeTree2.add("e");
		nodeTree2.add("o");
		nodeTree2.add("h");
		
		nodeTree3.add("k");
		nodeTree3.add("g");
		nodeTree3.add("c");
		nodeTree3.add("a");
		nodeTree3.add("e");
		nodeTree3.add("o");
		nodeTree3.add("h");
		
		nodeTree4.add("g");
		nodeTree4.add("c");
		nodeTree4.add("k");
		nodeTree4.add("a");
		nodeTree4.add("e");
		nodeTree4.add("o");
		nodeTree4.add("i");
	}
	
	@Test
	public void testEmpty() {
		assertTrue(emptyTree.isEmpty());
	}

	@Test
	public void testNotEmpty() {
		assertFalse(oneNodeTree.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, emptyTree.size());
		assertEquals(1, oneNodeTree.size());
		assertEquals(7, nodeTree2.size());
	}
	
	@Test
	public void testContains() {
		assertTrue(oneNodeTree.contains(FOO));
		assertTrue(nodeTree2.contains("o"));
		assertFalse(nodeTree2.contains(FOO));
	}
	
	@Test
	public void testRemove() {
		assertFalse(emptyTree.remove(0));
	}
	
	@Test
	public void testGet() {
		assertEquals(FOO, oneNodeTree.get(FOO));
		assertEquals("h", nodeTree2.get("h"));
	}
	
	@Test
	public void testAdd() {
		emptyTree.add(1);
		assertFalse(emptyTree.isEmpty());
		assertEquals(1, emptyTree.size());
	}
	
	@Test
	public void testGetMinimum() {
		assertEquals(null, emptyTree.getMinimum());
		assertEquals("a", nodeTree2.getMinimum());
	}

	@Test
	public void testGetMaximum() {
		assertEquals(FOO, oneNodeTree.getMaximum());
		assertEquals("o", nodeTree2.getMaximum());
	}

	@Test
	public void testHeight() {
		assertEquals(-1, emptyTree.height());
		assertEquals(0, oneNodeTree.height());
		assertEquals(3, nodeTree.height());
		assertEquals(2, nodeTree2.height());
	}
	
	@Test
	public void testPreorderIterator() {
		Iterator<String> i = oneNodeTree.preorderIterator();
		while (i.hasNext()) {
			assertEquals(FOO, i.next());			
		}
	}

	@Test
	public void testInorderIterator() {
		Iterator<String> i = oneNodeTree.inorderIterator();
		while (i.hasNext()) {
			assertEquals(FOO, i.next());			
		}
	}

	@Test
	public void testPostorderIterator() {
		Iterator<Integer> i = emptyTree.postorderIterator();
		assertFalse(i.hasNext());
	}
	
	@Test
	public void testEquals() {
		BSTInterface<String> tree = new BinarySearchTree<String>();
		assertFalse(oneNodeTree.equals(tree));
		tree.add(new String("foo"));
		assertTrue(oneNodeTree.equals(tree));
		
		assertFalse(nodeTree2.equals(nodeTree4));
	}
	
	@Test
	public void testSameValues() {
		BSTInterface<Integer> tree = new BinarySearchTree<Integer>();
		assertTrue(emptyTree.sameValues(tree));
		
		emptyTree.add(1);
		emptyTree.add(2);
		
		tree.add(2);
		tree.add(1);
		
		assertTrue(emptyTree.sameValues(tree));
		
		assertTrue(nodeTree2.sameValues(nodeTree3));
	}
	
	@Test 
	public void testIsBalanced() {
		assertTrue(emptyTree.isBalanced());
		emptyTree.add(1);
		assertTrue(emptyTree.isBalanced());
		emptyTree.add(2);
		assertTrue(emptyTree.isBalanced());
		emptyTree.add(3);
		assertFalse(emptyTree.isBalanced());
		
		assertTrue(nodeTree2.isBalanced());
		assertTrue(nodeTree4.isBalanced());
	}
	
	@Test 
	public void testBalance() {
		emptyTree.add(1);
		emptyTree.add(2);
		emptyTree.add(3);
		assertFalse(emptyTree.isBalanced());
		emptyTree.balance();
		assertTrue(emptyTree.isBalanced());
	}
}