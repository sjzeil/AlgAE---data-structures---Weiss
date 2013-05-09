package edu.odu.cs.cs361.animations.graphs;

public interface CppIterator<T> extends Cloneable {

	public boolean notEnd();
	public void increment();

	public boolean equals (Object iter);
	public T at();
	
	public Object clone();
	
}
