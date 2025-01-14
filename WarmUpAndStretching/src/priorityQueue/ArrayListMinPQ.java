package priorityQueue;
import java.util.ArrayList;

/**
 * An implementation of the Priority Queue interface using an array list.
 * 
 * @author Matt Boutell and <<TODO: William Thesken>>>. Created Mar 29, 2014.
 * 
 * @param <T>
 *            Generic type of PQ elements
 */
public class ArrayListMinPQ<T extends Comparable<T>> {
	// Requirement: You must use this instance variable without changes.
	private ArrayList<T> items;

	public ArrayListMinPQ() {
		// TODO: implement
		items = new ArrayList<T>();
	}

	public T findMin() {
		// This is also known as peekMin
		// TODO: implement
		if(this.isEmpty())
		{
			return null;
		}
		
		return items.get(items.size()-1);
		
	}

	public T deleteMin() {
		// TODO: implement
		T minimum = this.findMin();
		items.remove(minimum);
		return minimum;
	}

	public void insert(T item) {
		// TODO: implement
		
		for(int i = items.size()-1; i >= 0; i--)
		{
			if(i==0&&items.get(0).compareTo(item)<0)
			{
				items.add(0, item);
				return;
			}
			
			if(i!=0&&items.get(i-1).compareTo(item)>0&&items.get(i).compareTo(item)<0)
			{
				items.add(i, item);
				return;
			}
			
		}
		
		items.add(item);
		
	}

	public int size() {
		// TODO: implement
		if(items.isEmpty()) return 0;
		return items.size();
	}

	public boolean isEmpty() {
		// TODO: implement
		return items.isEmpty();
	}

	public void clear() {
		// TODO: implement
		items.clear();
	}
}
