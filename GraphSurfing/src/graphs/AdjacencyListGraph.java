package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class AdjacencyListGraph<T> extends Graph<T> {
	Map<T,Vertex> keyToVertex;
	int edgeCount = 0;

	
	private class Vertex {
		T key;
		List<Vertex> successors;
		List<Vertex> predecessors;
		
		Vertex(T key) {
			this.key = key;
			this.successors = new ArrayList<Vertex>();
			this.predecessors = new ArrayList<Vertex>();
		}
	}
	
	AdjacencyListGraph(Set<T> keys) {
		this.keyToVertex = new HashMap<T,Vertex>();
		for (T key : keys) {
			Vertex v = new Vertex(key);
			this.keyToVertex.put(key, v);
		}
	}
	
	public Map<T, Vertex> keyMapReturn() //returns a map unnecessary did not need to use 
	{
		return this.keyToVertex;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.keyToVertex.size();
	}

	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return this.edgeCount;
	}

	@Override
	public boolean addEdge(T from, T to) {
		// TODO Auto-generated method stub
		if(!this.keyToVertex.containsKey(from)||!this.keyToVertex.containsKey(to))
		{
			throw new NoSuchElementException();
		}
		if(this.hasEdge(from, to))
		{
			return false;
		}
		Vertex fromVertex = this.keyToVertex.get(from);
		Vertex toVertex = this.keyToVertex.get(to);
		
//		if(!fromVertex.successors.contains(toVertex)&&!toVertex.predecessors.contains(fromVertex))
//		{
//			return false;
//		}
		
		this.keyToVertex.get(from).successors.add(toVertex);
		this.keyToVertex.get(to).predecessors.add(fromVertex);
		
		this.edgeCount++;
		return true;
	}

	@Override
	public boolean hasVertex(T key) {
		// TODO Auto-generated method stub
		return this.keyToVertex.containsKey(key);
	}

	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(!this.hasVertex(from)||!this.hasVertex(to))
		{
			throw new NoSuchElementException();
		}
		
		return this.keyToVertex.get(from).successors.contains(this.keyToVertex.get(to));
	}

	@Override
	public boolean removeEdge(T from, T to) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(!this.keyToVertex.containsKey(from)||!this.keyToVertex.containsKey(to))
		{
			throw new NoSuchElementException();
		}
		
		Vertex fromVertex = this.keyToVertex.get(from);
		Vertex toVertex = this.keyToVertex.get(to);
		
		if(!fromVertex.successors.contains(toVertex)&&!toVertex.predecessors.contains(fromVertex))
		{
			return false;
		}
		
		this.keyToVertex.get(from).successors.remove(toVertex);
		this.keyToVertex.get(to).predecessors.remove(fromVertex);
		
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
		
		int outDegree = 0;
		for(Vertex v: this.keyToVertex.get(key).successors)
		{
			outDegree++;
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
		int inDegree = 0;
		for(Vertex v: this.keyToVertex.get(key).predecessors)
		{
			inDegree++;
		}
		
		return inDegree;
	}

	@Override
	public Set<T> keySet() {
		// TODO Auto-generated method stub
		return this.keyToVertex.keySet();
	}

	@Override
	public Set<T> successorSet(T key) {
		// TODO Auto-generated method stub
		if(!hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		
		Set<T> succSet = new HashSet<T>();
		
		for(Vertex v: this.keyToVertex.get(key).successors)
		{
			succSet.add(v.key);
		}
		
		return succSet;
		
	}

	@Override
	public Set<T> predecessorSet(T key) {
		// TODO Auto-generated method stub
		if(!hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		
		Set<T> predSet = new HashSet<T>();
		
		for(Vertex v: this.keyToVertex.get(key).predecessors)
		{
			predSet.add(v.key);
		}
		
		return predSet;
	}

	public class VertexIterator implements Iterator
	{
		Iterator<Vertex> vertIter;
		
		public VertexIterator(List vertList)
		{
			this.vertIter = vertList.iterator();
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return vertIter.hasNext();
			
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return vertIter.next().key;
		}
		
	}
	@Override
	public Iterator<T> successorIterator(T key) {
		// TODO Auto-generated method stub
		if(!this.hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		List succList = this.keyToVertex.get(key).successors;
		Iterator succIter = new VertexIterator(succList);
		
		return succIter;
	}

	@Override
	public Iterator<T> predecessorIterator(T key) {
		// TODO Auto-generated method stub
		if(!this.hasVertex(key))
		{
			throw new NoSuchElementException();
		}
		
		List predList = this.keyToVertex.get(key).predecessors;
		Iterator predIter = new VertexIterator(predList);
		
		return predIter;
	}



}
