package edu.odu.cs.cs361.animations;

import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!


public class BackTrack implements CanBeRendered<BackTrack>, Renderer<BackTrack> {

	private boolean done;
	private int[] arities;
	private int[] values;

	/**
	 * Create a backtracking state for a problem with
	 * nVariables variables, each of which has the same
	 * number of possible values (arity).
	 * 
	 * @param nVariables
	 * @param arity
	 */
	public BackTrack (int nVariables, int arity)
	{
		arities = new int[nVariables];
		Arrays.fill(arities, arity);
		values = new int[nVariables];
		Arrays.fill(values, 0);
		done = false;
	}

	/**	   Create a backtracking state in which each variable may have
	 * 	   a different number of possible values. The values are obtained
	 * 	   as integers stored in positions arityBegin .. arityEnd as per
	 * 	   the usual conventions for C++ iterators. The number of
	 * 	   variables in the system are inferred from the number of
	 * 	   positions in the given range.
	 */
	public BackTrack (int[] arities)
	{
		this.arities = Arrays.copyOf(arities, arities.length);
		done = false;
		values = new int[arities.length];
		Arrays.fill(values, 0);
	}

	/**
	 * Returns the current value associated with the indicated variable.
	 * 
	 * @param variableNumber
	 * @return
	 */
	public int get (int variableNumber)
	{
		return values[variableNumber];
	}

	/**
	 * Returns the number of variables in the backtracking system.
	 * @return
	 */
	public int numberOfVariables() 
	{
		return values.length;
	}

	/**
	 * Returns the number of potential values that can be assigned
	 * to the indicated variable.
	 */
	public int arity (int variableNumber)
	{
		return arities[variableNumber];
	}

	public boolean more() 
	// Indicates whether additional candidate solutions exist that
	// can be reached by subsequent ++ or prune operaations.
	{
		return !done;
	}

	/**
	 *  Indicates that the combination of values associated with
	 *  variables 0 .. level-1 (inclusive) has been judged unacceptable
	 *  (regardless of the values that could be given to variables
	 *  level..numberOfVariables()-1.  The backtracking state will advance
	 *  to the next solution in which at least one of the values in the
	 *  variables 0..level-1 will have changed.
	 *  
	 * @param level
	 */
	public void prune (int level)
	{
		level = (level > numberOfVariables()) ? numberOfVariables() : level;
		for (int i = level; i < values.length; ++i)
			values[i] = 0;

		// Treat the top level-1 values as a level-1 digit number. Add one
		// to the rightmost "digit". If this digit goes too high, reset it to
		// zero and "carry one to the left".
		int k = level-1;
		boolean carry = true;
		while (k >= 0 && carry) {
			values[k] += 1;
			if (values[k] >= arities[k])
				values[k] = 0;
			else
				carry = false;
			--k;
		}
		done = carry;
	}

	/**
	 *    Indicates that the combination of values associated with
	 *    variables 0 .. nVariables-1 (inclusive) has been judged unacceptable.
	 *    The backtracking state will advance
	 *    to the next solution in which at least one of the values in the
	 *    variables 0..level-1 will have changed.
	 *    
	 * @return
	 */
	public BackTrack advance()
	{
		prune(numberOfVariables());
		return this;
	}

	@Override
	public Renderer<BackTrack> getRenderer() {
		return this;
	}

	@Override
	public Color getColor(BackTrack obj) {
		return null;
	}

	@Override
	public List<Component> getComponents(BackTrack obj) {
		LinkedList<Component> comp = new LinkedList<Component>();
		for (int i = 0; i < values.length; ++i)
			comp.add(new Component(values[i]));
		return comp;
	}

	@Override
	public List<Connection> getConnections(BackTrack obj) {
		return new LinkedList<Connection>();
	}

	@Override
	public int getMaxComponentsPerRow(BackTrack obj) {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public String getValue(BackTrack obj) {
		return "";
	}

}
