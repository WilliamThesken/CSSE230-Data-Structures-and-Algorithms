package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


public class AdjacencyMatrixGraph<T> extends Graph<T> {
	Map<T,Integer> keyToIndex;
	List<T> indexToKey;
	int[][] matrix;
	int edgeCount = 0;
	
	AdjacencyMatrixGraph(Set<T> keys) {
		int size = keys.size();
		this.keyToIndex = new HashMap<T,Integer>();
		this.indexToKey = new ArrayList<T>();
		this.matrix = new int[size][size];
		// need to populate keyToIndex and indexToKey with info from keys
		int index = 0;
		for(T key: keys)
		{
			this.indexToKey.add(key);
			this.keyToIndex.put(key, index);
			index++;
		}
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return matrix.length;
	}

	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return this.edgeCount;
	}

	@Override
	public boolean addEdge(T from, T to) {
		// TODO Auto-generated method stub
		//first[] is row
		if(!this.keyToIndex.containsKey(from)||!this.keyToIndex.containsKey(to))
		{
			throw new NoSuchElementException();
		}
		

		int fromIndex = this.keyToIndex.get(from);
		int toIndex = this.keyToIndex.get(to);
		
		if(this.matrix[fromIndex][toIndex]==1)
		{
			return false;
		}
		
		this.matrix[fromIndex][toIndex] = 1;
		this.edgeCount++;
		
		return true;
	}

	@Override
	public boolean hasVertex(T key) {
		// TODO Auto-generated method stub
		return this.keyToIndex.containsKey(key);
	}

	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(!this.keyToIndex.containsKey(from)||!this.keyToIndex.containsKey(to))
		{
			throw new NoSuchElementException();
		}
		
		return this.matrix[this.keyToIndex.get(from)][this.keyToIndex.get(to)] == 1;
		
	}

	@Override
	public boolean removeEdge(T from, T to) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(!this.keyToIndex.containsKey(from)||!this.keyToIndex.containsKey(to))
		{
			throw new NoSuchElementException();
		}
		
		int fromIndex = this.keyToIndex.get(from);
		int toIndex = this.keyToIndex.get(to);
		
		if(this.matrix[fromIndex][toIndex]==0)
		{
			return false;
		}
		
		this.matrix[fromIndex][toIndex] = 0;
		this.edgeCount--;
		
		return true;
	}

	@Override
	public int outDegree(T key) {
		// TODO Auto-generated method stub
		
		if(!this.hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		int fromIndex = this.keyToIndex.get(key);
		
		int outDegree = 0;
		
		
		for(int toIndex = 0; toIndex < this.matrix.length; toIndex++)
		{
			if(this.matrix[fromIndex][toIndex]==1)
			{
				outDegree++;
			}
		}
		
		return outDegree;
	}

	@Override
	public int inDegree(T key) {
		// TODO Auto-generated method stub
		if(!this.hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		int toIndex = this.keyToIndex.get(key);
		
		int inDegree = 0;
		
		for(int fromIndex = 0; fromIndex < this.matrix.length; fromIndex++)
		{
			if(this.matrix[fromIndex][toIndex]==1)
			{
				inDegree++;
			}
		}
		
		return inDegree;
	}

	@Override
	public Set<T> keySet() {
		// TODO Auto-generated method stub
		return this.keyToIndex.keySet();
	}

	@Override
	public Set<T> successorSet(T key) {
		// TODO Auto-generated method stub
		
		if(!this.hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		Set<T> successorSet = new HashSet<T>();
		
		int fromIndex = this.keyToIndex.get(key);		
		
		for(int toIndex = 0; toIndex < this.matrix.length; toIndex++)
		{
			if(this.matrix[fromIndex][toIndex]==1)
			{
				successorSet.add(this.indexToKey.get(toIndex));
			}
		}
		
		
		return successorSet;
	}

	@Override
	public Set<T> predecessorSet(T key) {
		// TODO Auto-generated method stub
		if(!this.hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		Set<T> predecessorSet = new HashSet<T>();
		
		int toIndex = this.keyToIndex.get(key);		
		
		for(int fromIndex = 0; fromIndex < this.matrix.length; fromIndex++)
		{
			if(this.matrix[fromIndex][toIndex]==1)
			{
				predecessorSet.add(this.indexToKey.get(fromIndex));
			}
		}
		
		
		return predecessorSet;
	}

	public class SuccessorIterator implements Iterator
	{
		
		int[][] edgeArray;
		int count = 0;
		int fromInt;
		List<T> indexToKey;
		
		public SuccessorIterator(List<T> indexToKey, int[][] intakeArray, int fromInt)
		{
			this.edgeArray = intakeArray;
			this.fromInt = fromInt;
			this.indexToKey = indexToKey;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			int current = count;
			while(current<this.edgeArray.length&&this.edgeArray[fromInt][current]==0)
			{
				current++;
			}
			if(current==this.edgeArray.length)
			{
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			
			
			while(this.edgeArray[fromInt][count]!=1&&count<this.edgeArray.length)
			{
				count++;
			}
			count++;
			return this.indexToKey.get(count-1);
		}
		
		
	}
	
	@Override
	public Iterator<T> successorIterator(T key) {
		// TODO Auto-generated method stub
		
		if(!this.hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		int fromIndex = this.keyToIndex.get(key);
		Iterator succIter = new SuccessorIterator(this.indexToKey, this.matrix, fromIndex);
		
		return succIter;
	}

	
	public class PredecessorIterator implements Iterator
	{
		
		int[][] edgeArray;
		int count = 0;
		int toInt;
		List<T> indexToKey;
		
		public PredecessorIterator(List<T> indexToKey, int[][] intakeArray, int toInt)
		{
			this.edgeArray = intakeArray;
			this.toInt = toInt;
			this.indexToKey = indexToKey;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			int current = count;
			while(current<this.edgeArray.length&&this.edgeArray[current][toInt]==0)
			{
				current++;
			}
			if(current==this.edgeArray.length)
			{
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			
			while(this.edgeArray[count][toInt]!=1&&count<this.edgeArray.length)
			{
				count++;
			}
			count++;
			return this.indexToKey.get(count-1);
			
		}
		
		
	}
	
	@Override
	public Iterator<T> predecessorIterator(T key) {
		// TODO Auto-generated method stub
		if(!this.hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		int toInt = this.keyToIndex.get(key);
		
		Iterator predIter = new PredecessorIterator(this.indexToKey,this.matrix,toInt);
		
		return predIter;
	}



}
