package edu.odu.cs.cs361.animations;



import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation;

public class CS361DLAnimation extends LocalJavaAnimation {

	public CS361DLAnimation() {
		super("Doublely Linked List Operations");
	}

	@Override
	public String about() {
		return "Demonstration of Doublely Linked List Algorithms,\n" +
		"prepared for CS 361, Advanced Data Structures\n" +
		"and Algorithms, Old Dominion University\n" +
		"Summer 2014";
	}


	private LList list;
	
	
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {
			
			
			@Override
			public void selected() {
				
				list = new LList();
				list.setUp();
				globalVar("list", list);
	        	
			}
		});
		
		register ("Reset the list", new MenuFunction() {
			@Override
			public void selected() {
				list.setUp();
			}
		});

				
		/*register ("traverse (print)", new MenuFunction() {
			@Override
			public void selected() {
				list.outputOp();
			}
		});*/

		/*register ("search", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to search for:", ".+");
				node p = list.find (value);
				if (p != null) {
					out.println("Found it!");
				}
			}
		});*/
		
		register ("pop_Back", new MenuFunction() {
			@Override
			public void selected() {
				list.pop_back ();
			}
		});


		register ("pop_Front", new MenuFunction() {
			@Override
			public void selected() {
				list.pop_front ();
			}
		});


		
		register ("push_Back", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", ".+");
				list.push_back (value);
			}
		});


		register ("push_Front", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", ".+");
				list.push_front (value);
			}
		});
		
		
		register ("clear", new MenuFunction() {
			@Override
			public void selected() {
				list.clear ();
			}
		});

	}

	
	
	public static void main (String[] args) {
		CS361DLAnimation demo = new CS361DLAnimation();
		demo.runAsMain();
	}

}
