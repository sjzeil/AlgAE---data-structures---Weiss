package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
import edu.odu.cs.AlgAE.Server.Utilities.Index;

public class RPNcalculator extends LocalJavaAnimation {

	public class CalculatorRenderer implements Renderer<calculatorEngine> {

		@Override
		public Color getColor(calculatorEngine obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(calculatorEngine engine) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(engine.data, "data"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(calculatorEngine obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(calculatorEngine obj) {
			return 1;
		}

		@Override
		public String getValue(calculatorEngine obj) {
			return "";
		}

	}





	public RPNcalculator() {
		super("Recursive Calculator");
	}

	@Override
	public String about() {
		return "Demonstration of RPN calculator,\n" +
				"prepared for CS 361, Advanced Data Structures and Algorithms\n" +
				"Summer 2011";
	}

	calculatorEngine calc = new calculatorEngine();
	
	@Override
	public void buildMenu() {
		
		
		registerStartingAction(new MenuFunction() {
			
			@Override
			public void selected() {
				getMemoryModel().render(calculatorEngine.class, new CalculatorRenderer());
				globalVar("calc", calc);
			}
		}
		);
		
		register("calculator", new MenuFunction() {

			@Override
			public void selected() {
				calculatorEngine.calculator(calc);
			}
			
		});
		
		
	}
	

	
	
	
	public static void main (String[] args) {
		RPNcalculator demo = new RPNcalculator();
		demo.runAsMain();
	}

}
