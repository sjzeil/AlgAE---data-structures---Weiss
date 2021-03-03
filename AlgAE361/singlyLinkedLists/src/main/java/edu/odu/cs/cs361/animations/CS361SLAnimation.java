package edu.odu.cs.cs361.animations;



import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;


public class CS361SLAnimation extends LocalJavaAnimation {

	public CS361SLAnimation() {
		super("Singly Linked List Operations");
	}

	@Override
	public String about() {
		return "Demonstration of Singley Linked List Algorithms,\n" +
		"prepared for CS 361, Advanced Data Structures\n" +
		"and Algorithms, Old Dominion University\n" +
		"Summer 2011";
	}


	private LListHeader list;
	
	
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {
			
			@Override
			public void selected() {
				list = new LListHeader();
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

				
		register ("traverse (print)", new MenuFunction() {
			@Override
			public void selected() {
				list.outputOp();
			}
		});

		register ("search", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to search for:", ".+");
				node p = list.find (value);
				if (p != null) {
					out.println("Found it!");
				}
			}
		});

		register ("add (in order)", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", ".+");
				list.add (value);
			}
		});


		register ("remove", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to remove:", ".+");
				list.remove (value);
			}
		});

		register ("clear", new MenuFunction() {
			@Override
			public void selected() {
				list.clear ();
			}
		});

		
		register ("add to end", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", ".+");
				list.addToEnd (value);
			}
		});

		register ("add to front", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", ".+");
				list.addToFront (value);
			}
		});

		register ("add before", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", ".+");
				String value2 = promptForInput("Value in front of which to add it:", ".+");
				node p = list.findQ(value2);
				if (p != null)
					list.addBefore (p, value);
			}
		});

		register ("add after", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", ".+");
				String value2 = promptForInput("Value behind which to add it:", ".+");
				node p = list.findQ(value2);
				if(p != null)
					list.addAfter (p, value);
			}
		});

		register ("remove after", new MenuFunction() {
			@Override
			public void selected() {
				String value2 = promptForInput("Value behind which to remove a node:", ".+");
				node p = list.findQ(value2);
				if (p != null)
					list.removeAfter (p);
			}
		});

	}

	

	
	
	public static void main (String[] args) {
		CS361SLAnimation demo = new CS361SLAnimation();
		demo.runAsMain();
	}

}
