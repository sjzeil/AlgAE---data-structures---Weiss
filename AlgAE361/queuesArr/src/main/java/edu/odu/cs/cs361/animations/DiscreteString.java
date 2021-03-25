package edu.odu.cs.cs361.animations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;

/**
 * A wrapper for strings.  Unlike String, this is mutable. More important,
 * there is no hidden sharing.
 *
 * @author zeil
 *
 */

public class DiscreteString implements CanBeRendered<DiscreteString>, Renderer<DiscreteString> {

	    private static Color color = new Color(0, 0, 196);

	    private String value;

	    
	    
	    public DiscreteString() {
	        this.value = "";
	    }

	    public DiscreteString(String value) {
	        this.value = value;
	    }

	    public static void setRenderingColor (Color c)
	    {
	        color = c;
	    }
	    

	    /**
	     * Get the integer value stored in this index
	     */
	    public String get()
	    {
	        return value;
	    }

	    /**
	     * Set the integer value stored in this index
	     */
	    public void set(String v)
	    {
	        value = v;
	    }

	    /**
	     * Set the integer value stored in this index
	     */
	    public void set(DiscreteString v)
	    {
	        value = v.value;
	    }
	    
	    @Override
	    public Renderer<DiscreteString> getRenderer() {
	        return this;
	    }

	    /**
	     * What string will be used as the value of this object?
	     *     
	     * @param obj: object to be drawn
	     * @return a string or null to yield to other renderers
	     */
	    public String getValue(DiscreteString obj)
	    {
	        return "" + value;
	    }
	    
	    /**
	     * What color will be used to draw this object?
	     *     
	     * @param obj: object to be drawn
	     * @return a color or null to yield to other renderers
	     */
	    public Color getColor(DiscreteString obj)
	    {
	        return color;
	    }
	    
	    /**
	     * Get a list of other objects to be drawn inside the
	     * box portraying this one.
	     *     
	     * @param obj: object to be drawn
	     * @return an array of contained objects or null to yield to other renderers
	     */
	    public List<Component> getComponents(DiscreteString obj)
	    {
	        return new LinkedList<Component>();
	    }
	    
	    /**
	     * Get a list of other objects to which we will draw
	     * pointers from this one.
	     *     
	     * @param obj: object to be drawn
	     * @return an array of referenced objects or null to yield to other renderers
	     */
	    public List<Connection> getConnections(DiscreteString obj)
	    {
	        return new LinkedList<Connection>();
	    }
	    


	    /**
	     * Indicates how components will be layed out within the box
	     * representing this object.  A return value of 1 will force all
	     * components to be layes out in a single vertical column. Larger
	     * return values will permit a more horizontal layout.
	     *
	     * @param obj
	     * @return max #components per row or a non-positive value to yield to other renderers
	     */
	            
	    public int getMaxComponentsPerRow(DiscreteString obj)
	    {
	        return 1;
	    }
	    


	    public String toString()
	    {
	        return "" + value;
	    }
	    
	    public int hashCode()
	    {
	        return value.hashCode();
	    }


}
