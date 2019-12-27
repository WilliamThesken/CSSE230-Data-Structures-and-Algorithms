package search;

import java.util.Arrays;

public class EfficientSearch {
	
	public static int searchHelper(int[] sortedArray, int searchTerm, int leftBound, int rightBound)
	{
		//used procedure instructions on wikipedia and then created my own recursive version
		
		if(leftBound>rightBound) return -1;
		
		int middleIndex = (leftBound + rightBound) /2;
		
		if(sortedArray[middleIndex]>searchTerm) return searchHelper(sortedArray, searchTerm, leftBound, middleIndex-1);

		if(sortedArray[middleIndex]<searchTerm) return searchHelper(sortedArray, searchTerm, middleIndex+1, rightBound);
		
		return middleIndex;
	}
	
	public static int search(int[] sortedArray, int searchTerm) {
		// TODO You recognize this starting code as a SEQUENTIAL (one at a time, in-order) search. 
		// It runs in O(n) worst-case time.
		// So if there are 1,000,000 items in the array, it will have to make that many comparisons in the worst case.
		// 
		// Since the array is sorted, 
		// replace it with the much-more efficient BINARY search, which runs in O(log n) worst case time. 
		// If there are 1,000,000 items in the array, it will only have to make ~20 comparisons.
		//
		// You can look up binary search algorithm from the CSSE220 materials
		// or here: https://en.wikipedia.org/wiki/Binary_search_algorithm#Procedure
		
		return searchHelper(sortedArray, searchTerm, 0, sortedArray.length-1);
		

	}
}
