package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

/**
 * Abstract class to represent the Graph ADT. It is assumed that every vertex contains some 
 * data of type T, which serves as the identity of that node and provides access to it.
 * 
 * @author Nate Chenette
 *
 * @param <T>
 */
public abstract class Graph<T> {
	
	/**
	 * Returns the number of vertices in the graph.
	 * @return
	 */
	public abstract int size();
	

	/**
	 * Returns the number of edges in the graph.
	 * @return
	 */
	public abstract int numEdges();

	
	/**
	 * Adds a directed edge from the vertex containing "from" to the vertex containing "to". 
	 * @param from
	 * @param to
	 * @return true if the add is successful, false if the edge is already in the graph.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean addEdge(T from, T to);

	
	/**
	 * Determines whether a vertex is in the graph.
	 * @param key
	 * @return true if the graph has a vertex containing key.
	 */
	public abstract boolean hasVertex(T key);
	
	
	/**
	 * Determines whether an edge is in the graph.
	 * @param from
	 * @param to
	 * @return true if the directed edge (from, to) is in the graph, otherwise false.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean hasEdge(T from, T to) throws NoSuchElementException;
	
	
	/**
	 * Removes an edge from the graph.
	 * @param from
	 * @param to
	 * @return true if the remove is successful, false if the edge is not in the graph.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean removeEdge(T from, T to) throws NoSuchElementException;
	
	/**
	 * Computes out-degree of a vertex.
	 * @param key
	 * @return the number of successors of the vertex containing key
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract int outDegree(T key) throws NoSuchElementException;

	
	/**
	 * Computes in-degree of a vertex.
	 * @param key
	 * @return the number of predecessors of the vertex containing key
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract int inDegree(T key) throws NoSuchElementException;
	
	
	/**
	 * Returns the Set of vertex keys in the graph. 
	 * @return
	 */
	public abstract Set<T> keySet();
	
	/**
	 * Returns a Set of keys that are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Set<T> successorSet(T key) throws NoSuchElementException;
	
	/**
	 * Returns a Set of keys that are predecessors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Set<T> predecessorSet(T key) throws NoSuchElementException;
	
	/**
	 * Returns an Iterator that traverses the keys who are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Iterator<T> successorIterator(T key) throws NoSuchElementException;
	
	/**
	 * Returns an Iterator that traverses the keys who are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Iterator<T> predecessorIterator(T key) throws NoSuchElementException;
	
	/**
	 * Finds the strongly-connected component of the provided key.
	 * @param key
	 * @return a set containing all data in the strongly connected component of the vertex
	 * containing key 
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public Set<T> stronglyConnectedComponent(T key)
	{
//		Set<T> stronglu
		
		Set<T> successionSet = this.succesorSetComponent(key);
		Set<T> predecessionSet = this.predecessorSetComponent(key);
		
		Set<T> bindedSet;
		
		if(successionSet.size()<predecessionSet.size())
		{
			bindedSet = setBinder(successionSet, predecessionSet);
		}
		bindedSet = setBinder(predecessionSet, successionSet);
		
		return bindedSet;
		
		
	}
	
	public Set<T> setBinder (Set<T> smaller, Set<T> larger)
	{
		Set<T> bindedSet = new HashSet<T>();
		for(T vertex: smaller)
		{
			if(larger.contains(vertex))
			{
				bindedSet.add(vertex);
			}
		}
		
		return bindedSet;
	}
	
	public Set<T> succesorSetComponent(T key)
	{
		Set<T> validPathOfSuccession = new HashSet<T>();
		Stack<T> succStack = new Stack<T>();
		
		succStack.add(key);
		
		while(!succStack.isEmpty())
		{
			T popped = succStack.pop();

			validPathOfSuccession.add(popped);
			
			Set<T> successors = this.successorSet(popped);
			for(T successor: successors)
			{
				if(!validPathOfSuccession.contains(successor))
				{
					succStack.add(successor);
				}
				
			}
			
		}
		
		return validPathOfSuccession;
		
		
	}
	
	public Set<T> predecessorSetComponent(T key)
	{
		Set<T> validPathOfPredecession = new HashSet<T>();
		Stack<T> predStack = new Stack<T>();
		
		predStack.add(key);
		
		while(!predStack.isEmpty())
		{
			T popped = predStack.pop();

			validPathOfPredecession.add(popped);
			
			Set<T> predecessors = this.predecessorSet(popped);
			for(T predecessor: predecessors)
			{
				
				if(!validPathOfPredecession.contains(predecessor))
				{
					predStack.add(predecessor);

				}
			}
			
		}
		
		return validPathOfPredecession;
		
		
	}
	/**
	 * Searches for the shortest path between start and end points in the graph.
	 * @param start
	 * @param end
	 * @return a list of data, starting with start and ending with end, that gives the path through
	 * the graph, or null if no such path is found.  
	 * @throws NoSuchElementException if either key is not found in the graph
	 */

	
	public List<T> shortestPath(T startLabel, T endLabel)
	{
		if(!this.hasVertex(startLabel)&&!this.hasVertex(endLabel))
		{
			throw new NoSuchElementException();
		}
		
		if(startLabel.equals(endLabel))
		{
			List shortestPath = new ArrayList<T>();
			shortestPath.add(startLabel);
			
			return shortestPath;
		}
		
		Stack<List> stackPaths = new Stack<List>();
				
		List startPath = new ArrayList<T>();
		startPath.add(startLabel);
		
		stackPaths.add(startPath);
		
		
		Set<T> visited = new HashSet<T>();
		while(!stackPaths.isEmpty())
		{
			Stack<List> newPaths = new Stack<List>();
			
			while(!stackPaths.isEmpty())
			{
				List currentPath = stackPaths.pop();
				
				
				Set<T> currentPathSuccessors = this.successorSet((T)currentPath.get(currentPath.size()-1));
				
				if(currentPathSuccessors.contains(endLabel))
				{
					List newSuccess = currentPath;
					newSuccess.add(endLabel);
					return newSuccess;
				
				}
				else
				{
					for(T succ: currentPathSuccessors)
					{
						
						if(!currentPath.contains(succ)&&!visited.contains(succ))
						{
							List newPath = new ArrayList<List>();
							newPath.addAll(currentPath); //originally newPath = currentPath
							newPath.add(succ);
							visited.add(succ);
							
							newPaths.push(newPath);
							
						}
					}
				}
			}
			
			stackPaths = newPaths;
			
			
		}
		return null;
		
		
	}
		
}

