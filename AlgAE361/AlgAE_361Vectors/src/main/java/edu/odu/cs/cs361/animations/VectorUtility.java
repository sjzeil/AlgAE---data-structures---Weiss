package edu.odu.cs.cs361.animations;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;
import static edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation.activate;//!


public class VectorUtility {//!

//!	void copy (const vector<int>& a)
	public static void copy(miniVector a)//! 
	{
		ActivationRecord arec = activate(VectorUtility.class);//!
		arec.refParam("a", a).breakHere("invoke copy constructor");//!
		miniVector c = new miniVector(a);//!		vector<int> c = a;
		arec.var("c", c).breakHere("copied");//!
	}

}
