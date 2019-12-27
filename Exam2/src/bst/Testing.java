package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;


public class Testing {

	private static double maxSiblingDiffPoints = 0;

	@Test
	public void testMaxSiblingDiffEmpty() {
		BinarySearchTree t = new BinarySearchTree();
		assertEquals(0, t.maxSiblingDiff());
		maxSiblingDiffPoints += 1;
	}

	@Test
	public void testMaxSiblingDiffRoot() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(20);
		assertEquals(0, t.maxSiblingDiff());
		maxSiblingDiffPoints += 1;
	}

	@Test
	public void testMaxSiblingDiffOneFullNode() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(20);
		t.insert(10);
		t.insert(27);
		assertEquals(17, t.maxSiblingDiff());

		t = new BinarySearchTree();
		t.insert(10);
		t.insert(30);
		t.insert(26);
		t.insert(31);
		assertEquals(5, t.maxSiblingDiff());
		maxSiblingDiffPoints += 1;
	}

	@Test
	public void testMaxSiblingDiffTwoFullNodesTakesLargerDiff() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		t.insert(20);
		t.insert(30);
		t.insert(60);
		t.insert(-50);
		assertEquals(80, t.maxSiblingDiff());
	
		t = new BinarySearchTree();
		t.insert(100);
		t.insert(50);
		t.insert(20);
		t.insert(30);
		t.insert(60);
		t.insert(10);
		maxSiblingDiffPoints += 1;
	}

	@Test
	public void testMaxSiblingDiffFirstTreeInSpecification() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertEquals(5, t.maxSiblingDiff());
		maxSiblingDiffPoints += 6;
	}
	
	@Test
	public void testMaxSiblingDiffSecondTreeInSpecification() {
		int[] treeVals = new int[] {11, 6, 19, 4, 8, 17, 43, 5, 10, 31, 49};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertEquals(26, t.maxSiblingDiff());
		maxSiblingDiffPoints += 6;
	}

	private static double leftSubtreeReplacePoints = 0;

	@Test
	public void testLeftSubtreeReplaceEmpty() {
		BinarySearchTree t = new BinarySearchTree();
		assertFalse(t.leftSubtreeReplace(10));
		assertEquals("", t.toString());
		leftSubtreeReplacePoints += 1;
	}

	@Test
	public void testLeftSubtreeReplaceSmallTree() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(20);
		t.insert(10);
		assertTrue(t.leftSubtreeReplace(20));
		assertEquals("10", t.toString());
		leftSubtreeReplacePoints += 1.5;
	}
	
	@Test
	public void testLeftSubtreeReplaceSmallTreeNotFound() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(20);
		t.insert(10);
		assertFalse(t.leftSubtreeReplace(5));
		assertEquals("1020", t.toString());
		leftSubtreeReplacePoints += 1.5;
	}

	private BinarySearchTree insertValsIntoTree(int[] vals) {
		BinarySearchTree t = new BinarySearchTree();
		for (int val : vals) {
			t.insert(val);
		}
		return t;
	}
	
	@Test
	public void testLeftSubtreeReplaceInSpecification() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertTrue(t.leftSubtreeReplace(2)); // found
		assertEquals("181011131416", t.toString());
		assertFalse(t.leftSubtreeReplace(7)); // not found
		assertEquals("181011131416", t.toString());
		leftSubtreeReplacePoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification2() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		// Same tree, different values to find.
		// These next tests all use this tree.
		assertTrue(t.leftSubtreeReplace(13)); // found
		assertEquals("123456810111416", t.toString());
		assertFalse(t.leftSubtreeReplace(0)); // not found
		assertEquals("123456810111416", t.toString());
		leftSubtreeReplacePoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification3() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertTrue(t.leftSubtreeReplace(8)); // found
		assertEquals("1234561011131416", t.toString());
		assertFalse(t.leftSubtreeReplace(12)); // not found
		assertEquals("1234561011131416", t.toString());
		leftSubtreeReplacePoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification4() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertTrue(t.leftSubtreeReplace(11)); // found
		assertEquals("123456810", t.toString());
		assertFalse(t.leftSubtreeReplace(17)); // not found
		assertEquals("123456810", t.toString());
		leftSubtreeReplacePoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification5() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertTrue(t.leftSubtreeReplace(10)); // found
		assertEquals("1234568", t.toString());
		assertFalse(t.leftSubtreeReplace(18)); // not found
		assertEquals("1234568", t.toString());
		leftSubtreeReplacePoints += 2;
	}

	
	private static double leftSubtreeReplaceIgnoreReturnPoints = 0;

	// These tests ignore boolean return value, to make it easier to score 
	// partial credit for solutions that have incorrect return values.
	@Test
	public void testLeftSubtreeReplaceSmallTreeIgnoreReturn() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(20);
		t.insert(10);
		t.leftSubtreeReplace(20);
		assertEquals("10", t.toString());
		leftSubtreeReplaceIgnoreReturnPoints += 1;
	}
	
	@Test
	public void testLeftSubtreeReplaceSmallTreeNotFoundIgnoreReturn() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(20);
		t.insert(10);
		t.leftSubtreeReplace(5);
		assertEquals("1020", t.toString());
		leftSubtreeReplaceIgnoreReturnPoints += 1;
	}

	@Test
	public void testLeftSubtreeReplaceInSpecificationIgnoreReturn() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.leftSubtreeReplace(2); // found
		assertEquals("181011131416", t.toString());
		t.leftSubtreeReplace(7); // not found
		assertEquals("181011131416", t.toString());
		leftSubtreeReplaceIgnoreReturnPoints += 1.5;
	}

	@Test
	public void testLeftSubtreeReplaceInSpecification2IgnoreReturn() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		// Same tree, different values to find.
		// These next tests all use this tree.
		t.leftSubtreeReplace(13); // found
		assertEquals("123456810111416", t.toString());
		t.leftSubtreeReplace(0); // not found
		assertEquals("123456810111416", t.toString());
		leftSubtreeReplaceIgnoreReturnPoints += 1.5;
	}

	@Test
	public void testLeftSubtreeReplaceInSpecification3IgnoreReturn() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.leftSubtreeReplace(8); // found
		assertEquals("1234561011131416", t.toString());
		t.leftSubtreeReplace(12); // not found
		assertEquals("1234561011131416", t.toString());
		leftSubtreeReplaceIgnoreReturnPoints += 1.5;	
	}

	@Test
	public void testLeftSubtreeReplaceInSpecification4IgnoreReturn() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.leftSubtreeReplace(11); // found
		assertEquals("123456810", t.toString());
		t.leftSubtreeReplace(17); // not found
		assertEquals("123456810", t.toString());
		leftSubtreeReplaceIgnoreReturnPoints += 1.5;	
	}

	@Test
	public void testLeftSubtreeReplaceInSpecification5IgnoreReturn() {
		int[] treeVals = new int[] {10, 8, 11, 2, 14, 1, 6, 13, 16, 4, 3, 5};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.leftSubtreeReplace(10); // found
		assertEquals("1234568", t.toString());
		t.leftSubtreeReplace(18); // not found
		assertEquals("1234568", t.toString());
		leftSubtreeReplaceIgnoreReturnPoints += 2;	
	}

	
	private static int hasNodeInRangePoints = 0;

	@Test
	public void testHasNodeInRangeEmpty() {
		BinarySearchTree t = new BinarySearchTree();
		assertFalse(t.hasNodeInRange(50, 50));
		hasNodeInRangePoints += 1;
	}

	@Test
	public void testHasNodeInRangeRoot() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		assertTrue(t.hasNodeInRange(40, 60));
		assertFalse(t.hasNodeInRange(51, 60));
		assertFalse(t.hasNodeInRange(40, 49));
		hasNodeInRangePoints += 2;
	}

	@Test
	public void testHasNodeInRangeSimple() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		t.insert(20);
		t.insert(30);
		t.insert(60);
		assertTrue(t.hasNodeInRange(45, 55));
		assertTrue(t.hasNodeInRange(15, 35));
		assertTrue(t.hasNodeInRange(40, 70));
		assertFalse(t.hasNodeInRange(23, 25));
		assertFalse(t.hasNodeInRange(31, 44));
		assertFalse(t.hasNodeInRange(65, 100));
		hasNodeInRangePoints += 2;
	}

	@Test
	public void testHasNodeInRangeBoundary() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		t.insert(20);
		t.insert(30);
		t.insert(60);
		assertTrue(t.hasNodeInRange(20, 22));
		assertTrue(t.hasNodeInRange(28, 30));
		assertTrue(t.hasNodeInRange(18, 20));
		assertTrue(t.hasNodeInRange(51, 60));
		assertTrue(t.hasNodeInRange(20,  22));
		assertFalse(t.hasNodeInRange(21, 29));
		assertFalse(t.hasNodeInRange(31, 49));
		assertFalse(t.hasNodeInRange(61, 100));
		assertFalse(t.hasNodeInRange(-10, 19));
		assertFalse(t.hasNodeInRange(51, 59));
		hasNodeInRangePoints += 2;
	}
	
	@Test
	public void testHasNodeInRangeTreeInSpecification() {
		int[] treeVals = new int[] {11, 6, 19, 4, 8, 17, 43, 5, 10, 31, 49};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertTrue(t.hasNodeInRange(20, 35));
		assertTrue(t.hasNodeInRange(4, 5));
		assertTrue(t.hasNodeInRange(8, 9));
		assertTrue(t.hasNodeInRange(11, 15));
		assertTrue(t.hasNodeInRange(40, 50));
		assertFalse(t.hasNodeInRange(7, 7));
		assertFalse(t.hasNodeInRange(12, 16));
		assertFalse(t.hasNodeInRange(18, 18));
		assertFalse(t.hasNodeInRange(20, 30));
		assertFalse(t.hasNodeInRange(32, 42));
		assertFalse(t.hasNodeInRange(44, 48));
		assertFalse(t.hasNodeInRange(50, 100));
		hasNodeInRangePoints += 3;
	}

	@Test
	public void testHasNodeInRangeLarger() {
		int[] treeVals = new int[] {
				500, 200, 800, 100, 300, 900, 50, 
				150, 280, 350, 850, 940, 30, 320, 380, 
				930, 960, 40, 340, 35, 42, 330, 345, 335};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertTrue(t.hasNodeInRange(30, 35));
		assertTrue(t.hasNodeInRange(315, 325));
		assertTrue(t.hasNodeInRange(42, 45));
		assertTrue(t.hasNodeInRange(343, 346));
		assertTrue(t.hasNodeInRange(860, 910));
		assertFalse(t.hasNodeInRange(160, 180));
		assertFalse(t.hasNodeInRange(400, 450));
		assertFalse(t.hasNodeInRange(970, 980));
		assertFalse(t.hasNodeInRange(780, 785));
		assertFalse(t.hasNodeInRange(501, 799));
		assertFalse(t.hasNodeInRange(351, 370));
		assertFalse(t.hasNodeInRange(331, 334));
		hasNodeInRangePoints += 3;
	}

	@AfterClass
	public static void displayPoints() {
		System.out.printf("%4.1f/16.0 maxSiblingDiff  correctness points\n", (double)maxSiblingDiffPoints);

		System.out.printf("%4.1f/14.0 leftSubtreeReplace correctness points\n", leftSubtreeReplacePoints);
		System.out.printf("         (%4.1f/10.0 minimum correctness points if return value incorrect)\n", leftSubtreeReplaceIgnoreReturnPoints);
		System.out.printf("      --- 6 pts for efficiency will be checked by the instructor\n");

		System.out.printf("%4.1f/13.0 hasNodeWithinRange correctness points\n", (double)hasNodeInRangePoints);
		System.out.printf("      --- 7 pts for efficiency will be checked by the instructor\n");
		System.out.printf("--- 4 pts overall for elegance will be checked by the instructor\n");
	
	}
}
