package edu.odu.cs.cs361.animations;



import static edu.odu.cs.AlgAE.Server.LocalServer.activate;
import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;
import edu.odu.cs.cs361.animations.LList.iterator;

public class CS361DLAnimation extends LocalJavaAnimation {

	public CS361DLAnimation() {
		super("Doubly Linked List Operations");
	}

	@Override
	public String about() {
		return "Demonstration of Doubly Linked List Algorithms,\n" +
		"prepared for CS 361, Advanced Data Structures\n" +
		"and Algorithms, Old Dominion University\n" +
		"Summer 2014";
	}


	private LList list;
	private LList.iterator iter;
	
	
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {
			
			
			@Override
			public void selected() {
				
				list = new LList();
				list.setUp();
				iter = list.Qbegin();
				globalVar("list", list);
				globalVar("iter", iter);
	        	
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
		
		register ("clear", new MenuFunction() {
			@Override
			public void selected() {
				list.clear ();
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
				traverse(list);
			}
		});

		register ("move iter (search)", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to search for:", ".+");
				iter.current = list.findQ (value);
				if (iter.current != null) {
					out.println("Found it!");
				}
			}
		});

		register ("iter = list.begin()", new MenuFunction() {
			@Override
			public void selected() {
				iter.current = list.head.next;
			}
		});

		register ("iter = list.end()", new MenuFunction() {
			@Override
			public void selected() {
				iter.current = list.tail;
			}
		});

		register ("iter++", new MenuFunction() {
			@Override
			public void selected() {
				try {
					iter.increment();
				} catch (Exception e) {
					// ignore
				}
			}
		});

		register ("iter--", new MenuFunction() {
			@Override
			public void selected() {
				try {
					iter.decrement();
				} catch (Exception e) {
					// ignore
				}
			}
		});

		register ("insert(iter, ...)", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to insert:", ".+");
				try {
					list.insert(iter, value);
				} catch (Exception e) {
					// ignore
				}
			}
		});

		register ("erase(iter)", new MenuFunction() {
			@Override
			public void selected() {
				try {
					list.erase(iter);
				} catch (Exception e) {
					// ignore
				}
			}
		});

	}
		

	void traverse(LList alist) //!void traverse(const list<string>& alist)
	{
		ActivationRecord arec = activate(getClass());//!
	    arec.refParam("alist",alist).breakHere("starting traversal");//!
	    iterator pos = alist.begin();//!    list<string>::const_iterator pos = alist.begin();
	    arec.var("pos", pos).breakHere("Got the starting position");//!
	    while (pos.IsNotEqual(alist.end()))//!    while (pos != alist.end())
		{
	        out.println(pos.get());//!        cout << pos->data << endl;
	        arec.breakHere("Ready to step forward");//!
	        pos.increment();//!        ++pos;
		}
	    arec.breakHere("Traversal completed");//!
	}

	
	public static void main (String[] args) {
		CS361DLAnimation demo = new CS361DLAnimation();
		demo.runAsMain();
	}

}
