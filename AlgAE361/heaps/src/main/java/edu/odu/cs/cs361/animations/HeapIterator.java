/**
 * 
 */
package edu.odu.cs.cs361.animations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;



/**
 * @author zeil
 *
 */
public class HeapIterator implements Renderer<HeapIterator>, CanBeRendered<HeapIterator> {

	private heaps heap;
	int index;
	

	/**
	 * @param value
	 * @param indexesInto
	 */
	public HeapIterator(int value, heaps indexesInto) {
		heap = indexesInto;
		index = value;
	}


	@Override
	public String getValue(HeapIterator obj) {
		return "";
	}
	
	@Override
	public List<Connection> getConnections(HeapIterator iter) {
		LinkedList<Connection> conn = new LinkedList<Connection>();
		if (index >= 0 && index < heap.data.size())
			conn.add(new Connection(heap.data.get(index)));
		if (index >= 0 && index < heap.tree.size())
			conn.add(new Connection(heap.tree.get(index)));
		return conn;
	}


	@Override
	public Color getColor(HeapIterator obj) {
		return null;
	}


	@Override
	public List<Component> getComponents(HeapIterator obj) {
		return new LinkedList<Component>();
	}




	@Override
	public int getMaxComponentsPerRow(HeapIterator obj) {
		return 1;
	}


	@Override
	public Renderer<HeapIterator> getRenderer() {
		return this;
	}
}
